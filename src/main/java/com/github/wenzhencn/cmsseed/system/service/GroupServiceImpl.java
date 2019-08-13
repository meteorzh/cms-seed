package com.github.wenzhencn.cmsseed.system.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.wenzhencn.cmsseed.common.BusinessException;
import com.github.wenzhencn.cmsseed.common.CommonErrorCode;
import com.github.wenzhencn.cmsseed.model.PageParam;
import com.github.wenzhencn.cmsseed.system.entity.GroupDO;
import com.github.wenzhencn.cmsseed.system.mapper.GroupMapper;
import com.github.wenzhencn.cmsseed.system.model.GroupDTO;
import com.github.wenzhencn.cmsseed.system.security.EntryTargetType;

/**
 * 
 * @author zhen.wen
 *
 */
@Service
public class GroupServiceImpl implements GroupService {
	
	private static Long ALL_USER_GROUP_ID = 1L;
	
	@Autowired
	private GroupMapper groupMapper;
	
	@Autowired
	private EntryService entryService;
	
	@Override
	public void save(String scope, GroupDO group) throws BusinessException {
		if(group.getId() == null) {
			GroupDO exist = groupMapper.selectUniqueByName(group.getName());
			// 新增
			if(exist != null) {
				throw BusinessException.newInstance(CommonErrorCode.FAILED, "用户组[" + group.getName() + "]已存在");
			}
			groupMapper.insert(group);
		} else {
			GroupDO exist = groupMapper.select(group.getId());
			if(exist == null) {
				throw BusinessException.newInstance(CommonErrorCode.FAILED, "用户组不存在");
			} else if(!exist.getName().equals(group.getName())) {
				// 不能修改组名
				throw BusinessException.newInstance(CommonErrorCode.FAILED, "用户组名不能修改");
			} else {
				GroupDO update = new GroupDO();
				update.setId(group.getId());
				update.setDescription(group.getDescription());
				groupMapper.updateSelective(update);
			}
		}
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delete(String scope, Long id) throws BusinessException {
		GroupDO group = groupMapper.select(id);
		if(group == null) {
			throw BusinessException.newInstance(CommonErrorCode.FAILED, "用户组不存在");
		} else if(groupMapper.countGroupUsers(id) > 0) {
			throw BusinessException.newInstance(CommonErrorCode.FAILED, "不能删除有用户的组");
		} else if(groupMapper.countGroupRoles(id) > 0) {
			throw BusinessException.newInstance(CommonErrorCode.FAILED, "不能删除有角色的组");
		} else if(entryService.countByTarget(EntryTargetType.GROUP, id) > 0) {
			throw BusinessException.newInstance(CommonErrorCode.FAILED, "不能删除有权限关联的组");
		}
		// 删除组
		groupMapper.delete(id);
	}
	
	@Override
	public GroupDO query(Long id) {
		return groupMapper.select(id);
	}

	@Override
	public List<GroupDO> query(String scope, String name) {
		return groupMapper.selectByName(scope, name);
	}
	
	@Override
	public List<GroupDO> queryByIds(Collection<Long> ids) {
		if(CollectionUtils.isEmpty(ids)) {
			return Collections.emptyList();
		}
		return groupMapper.selectByIds(ids);
	}
	
	@Override
	public PageInfo<GroupDTO> queryPage(PageParam param, String scope, String name) {
		Page<GroupDO> page = PageHelper.startPage(param.getPageNo(), param.getPageSize()).doSelectPage(() -> groupMapper.selectByName(scope, name));
		Page<GroupDTO> dtoPage = new Page<>(page.getPageNum(), page.getPageSize(), page.isCount());
		if(!CollectionUtils.isEmpty(page)) {
			List<Long> ids = page.stream().map(GroupDO::getId).collect(Collectors.toList());
			List<GroupDTO> dtos = groupMapper.selectDTOByIds(ids);
			dtoPage.addAll(dtos);
		}
		dtoPage.setStartRow(page.getStartRow());
		dtoPage.setEndRow(page.getEndRow());
		dtoPage.setTotal(page.getTotal());
		dtoPage.setPages(page.getPages());
		dtoPage.setPageSizeZero(page.getPageSizeZero());
		dtoPage.setCountColumn(page.getCountColumn());
		dtoPage.setOrderBy(page.getOrderBy());
		dtoPage.setOrderByOnly(page.isOrderByOnly());
		return new PageInfo<>(dtoPage);
	}
	
	@Override
	public int initRelationForNewUsers(Set<Long> userIds) {
		if(CollectionUtils.isEmpty(userIds)) {
			return 0;
		}
		return groupMapper.relateUsersToGroup(userIds, ALL_USER_GROUP_ID);
	}
	
	@Override
	public void addUserToGroup(String scope, Long groupId, Long userId) throws BusinessException {
		GroupDO group = groupMapper.select(groupId);
		if(group == null) {
			throw BusinessException.newInstance(CommonErrorCode.FAILED, "用户组不存在");
		} else {
			groupMapper.relateUsersToGroup(Arrays.asList(userId), groupId);
		}
	}
	
	@Override
	public void delUserFromGroup(String scope, Long groupId, Long userId) throws BusinessException {
		GroupDO group = groupMapper.select(groupId);
		if(group == null) {
			throw BusinessException.newInstance(CommonErrorCode.FAILED, "用户组不存在");
		} else {
			groupMapper.unrelateUserFromGroup(userId, groupId);
		}
	}

}
