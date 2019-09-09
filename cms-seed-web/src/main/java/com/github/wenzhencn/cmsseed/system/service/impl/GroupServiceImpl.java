package com.github.wenzhencn.cmsseed.system.service.impl;

import com.github.wenzhencn.cmsseed.common.CommonErrorCode;
import com.github.wenzhencn.cmsseed.system.entity.UserPO;
import com.github.wenzhencn.cmsseed.system.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.wenzhencn.cmsseed.common.BusinessException;
import com.github.wenzhencn.cmsseed.common.ErrorCode;
import com.github.wenzhencn.cmsseed.system.entity.GroupPO;
import com.github.wenzhencn.cmsseed.system.mapper.GroupMapper;
import com.github.wenzhencn.cmsseed.system.security.EntryTargetType;
import com.github.wenzhencn.cmsseed.system.service.IEntryService;
import com.github.wenzhencn.cmsseed.system.service.IGroupService;
import com.github.wenzhencn.cmsseed.system.service.IRoleService;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wenzhen
 * @since 2019-09-05
 */
@Service
public class GroupServiceImpl extends ServiceImpl<GroupMapper, GroupPO> implements IGroupService {
	
	@Autowired
	private IRoleService roleService;
	
	@Autowired
	private IEntryService entryService;

	@Autowired
	private IUserService userService;

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void add(GroupPO group) throws BusinessException {
		GroupPO exist = getOne(new QueryWrapper<GroupPO>().eq("name", group.getName()));
		if(exist != null) {
			throw BusinessException.newInstance(ErrorCode.GROUP_EXIST, String.format("用户组[%s]已存在", group.getName()));
		}
		group.setSys(false);
		save(group);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void update(GroupPO group) throws BusinessException {
		GroupPO exist = getById(group.getId());
		if(exist == null) {
			throw BusinessException.newInstance(ErrorCode.GROUP_NOT_FOUND, String.format("用户组[%d]不存在", group.getId()));
		} else if (exist.getSys()) {
			throw BusinessException.newInstance(ErrorCode.GROUP_NOT_FOUND, "不能修改系统保留用户组");
		}
		if(!exist.getName().equals(group.getName())) {
			if(getOne(new QueryWrapper<GroupPO>().eq("name", group.getName())) != null) {
				throw BusinessException.newInstance(ErrorCode.GROUP_EXIST, String.format("用户组[%s]已存在, 不能将组名修改为[%s]", group.getName(), group.getName()));
			}
		}
		updateById(group);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void delete(Long id) throws BusinessException {
		GroupPO exist = getById(id);
		if(exist == null) {
			throw BusinessException.newInstance(ErrorCode.GROUP_NOT_FOUND, String.format("用户组[%d]不存在", id));
		} else if (exist.getSys()) {
			throw BusinessException.newInstance(ErrorCode.GROUP_NOT_FOUND, "不能删除系统保留用户组");
		}
		
		// 删除所有相关关联
		// 删除和用户关联
		clearUsersFromGroup(id);
		// 删除和角色关联
		roleService.clearRoleRelsForGroup(id);
		// 删除被授予的权限
		entryService.clearEntrysForTarget(EntryTargetType.GROUP, id);
		
		// 删除本体
		removeById(id);
	}

	@Override
	public void clearUsersFromGroup(Long groupId) {
		baseMapper.clearUsersFromGroup(groupId);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void addUsersToGroup(Long groupId, List<Long> userIds) throws BusinessException {
		GroupPO group = getById(groupId);
		if(group == null) {
			throw BusinessException.newInstance(ErrorCode.GROUP_NOT_FOUND, String.format("用户组[%s]不存在", groupId));
		}
		if (userIds.size() != userService.countByUserIds(userIds)) {
			throw BusinessException.newInstance(CommonErrorCode.FAILED, "存在不正确的用户ID");
		}

		// 判断是否有用户已在用户组中
		List<Long> hadUids = baseMapper.countGroupUsersRels(groupId, userIds);
		if (hadUids.size() > 0) {
			throw BusinessException.newInstance(CommonErrorCode.FAILED, String.format("用户[%s]已在用户组中", StringUtils.join(hadUids, ",")));
		}
		baseMapper.addUsersToGroup(groupId, userIds);
	}

	@Override
	public void addUserToGroups(List<Long> groupIds, Long userId) throws BusinessException {
		UserPO user = userService.getById(userId);
		if (user == null) {
			throw BusinessException.newInstance(ErrorCode.USER_NOT_FOUND, String.format("用户[%d]不存在", userId));
		}
		if (groupIds.size() != countByGroupIds(groupIds)) {
			throw BusinessException.newInstance(CommonErrorCode.FAILED, "存在不正确的用户组ID");
		}

		// 判断用户是否已在某个组中，避免重复添加
		List<Long> hadGids = baseMapper.countUserGroupsRels(userId, groupIds);
		if (hadGids.size() > 0) {
			throw BusinessException.newInstance(CommonErrorCode.FAILED, String.format("用户已在用户组[%s]中", StringUtils.join(hadGids, ",")));
		}
		baseMapper.addUserToGroups(groupIds, userId);
	}

	@Override
	public int countByGroupIds(Collection<Long> groupIds) {
		if (CollectionUtils.isEmpty(groupIds)) {
			return 0;
		}
		return count(new QueryWrapper<GroupPO>().in("id", groupIds));
	}

}
