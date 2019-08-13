package com.github.wenzhencn.cmsseed.system.service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.wenzhencn.cmsseed.common.BusinessException;
import com.github.wenzhencn.cmsseed.common.CommonErrorCode;
import com.github.wenzhencn.cmsseed.model.PageParam;
import com.github.wenzhencn.cmsseed.system.entity.GroupDO;
import com.github.wenzhencn.cmsseed.system.entity.RoleDO;
import com.github.wenzhencn.cmsseed.system.mapper.RoleMapper;
import com.github.wenzhencn.cmsseed.system.model.RoleDTO;
import com.github.wenzhencn.cmsseed.system.model.UserRoleRel;
import com.github.wenzhencn.cmsseed.system.security.EntryTargetType;

/**
 * 
 * @author zhen.wen
 *
 */
@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleMapper roleMapper;
	
	@Autowired
	private EntryService entryService;
	
	@Autowired
	private GroupService groupService;
	
	@Override
	public void save(String scope, RoleDTO role) throws BusinessException {
		if(role.getId() == null) {
			// 新增
			RoleDO exist = roleMapper.selectByCode(role.getCode());
			if(exist != null) {
				throw BusinessException.newInstance(CommonErrorCode.FAILED, "角色[" + role.getCode() + "]已存在");
			}
			roleMapper.insert(role.toDO());
		} else {
			// 更新
			RoleDO exist = roleMapper.select(role.getId());
			if(exist == null) {
				throw BusinessException.newInstance(CommonErrorCode.FAILED, "角色不存在");
			} else if(!exist.getCode().equals(role.getCode())) {
				// 不能修改code
				throw BusinessException.newInstance(CommonErrorCode.FAILED, "角色Code不能修改");
			} else {
				RoleDO update = new RoleDO();
				update.setId(role.getId());
				update.setName(role.getName());
				roleMapper.updateSelective(update);
			}
		}
	}
	
	@Override
	public void delete(String scope, Long roleId) throws BusinessException {
		RoleDO role = roleMapper.select(roleId);
		if(roleMapper.countRoleGroupRels(roleId) > 0) {
			throw BusinessException.newInstance(CommonErrorCode.FAILED, "需要删除的角色和用户组存在关联关系");
		}
		if(roleMapper.countRoleUserRels(roleId) > 0) {
			throw BusinessException.newInstance(CommonErrorCode.FAILED, "需要删除的角色和用户存在关联关系");
		}
		if(entryService.countByTarget(EntryTargetType.ROLE, roleId) > 0) {
			throw BusinessException.newInstance(CommonErrorCode.FAILED, "需要删除的角色拥有权限");
		}
		roleMapper.delete(roleId);
	}
	
	@Override
	public List<RoleDO> query(String scope, String name) {
		return roleMapper.selectByName(scope, name);
	}
	
	@Override
	public RoleDO query(Long id) {
		return roleMapper.select(id);
	}
	
	@Override
	public PageInfo<RoleDO> queryPage(PageParam param, String scope, String name) {
		return PageHelper.startPage(param.getPageNo(), param.getPageSize()).doSelectPageInfo(() -> roleMapper.selectByName(scope, name));
	}
	
	@Override
	public List<RoleDO> queryScopeRoles(String scope) {
		return roleMapper.selectScopeRoles(scope);
	}

	@Override
	public List<RoleDO> queryByUserAll(Long userId) {
		List<RoleDO> fu = queryByUser(userId);
		List<RoleDO> fg = queryByUserFromGroups(userId);
		
		Map<Long, RoleDO> map = fu.stream().collect(Collectors.toMap(RoleDO::getId, Function.identity()));
		fg.forEach(role -> {
			if(!map.containsKey(role.getId())) {
				map.put(role.getId(), role);
				fu.add(role);
			}
		});
		
		return fu;
	}

	@Override
	public List<RoleDO> queryByUser(Long userId) {
		return roleMapper.selectByUser(userId);
	}

	@Override
	public List<RoleDO> queryByUserFromGroups(Long userId) {
		return roleMapper.selectByUserFromGroups(userId);
	}
	
	@Override
	public List<RoleDO> queryByIds(Collection<Long> ids) {
		if(CollectionUtils.isEmpty(ids)) {
			return Collections.emptyList();
		}
		return roleMapper.selectByIds(ids);
	}
	
	@Override
	public List<UserRoleRel> queryRolesByUsers(String scope, Collection<Long> userIds) {
		if(CollectionUtils.isEmpty(userIds)) {
			return Collections.emptyList();
		}
		return roleMapper.selectScopeUserRoleRels(scope, userIds);
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void relateRoleToUser(String scope, Long roleId, Long userId) throws BusinessException {
		RoleDO role = roleMapper.select(roleId);
		if(role == null) {
			throw BusinessException.newInstance(CommonErrorCode.FAILED, "角色不存在");
		}
		if(roleMapper.countRoleToUserRels(roleId, userId) == 0) {
			roleMapper.relateRoleToUser(roleId, userId);
		}
	}
	
	@Override
	public void unrelateRoleToUser(String scope, Long roleId, Long userId) throws BusinessException {
		RoleDO role = roleMapper.select(roleId);
		if(role == null) {
			throw BusinessException.newInstance(CommonErrorCode.FAILED, "角色不存在");
		}
		roleMapper.unrelateRoleToUser(roleId, userId);
	}
	
	@Override
	public void unrelateScopeRolesOfUser(String scope, Long userId) {
		if(StringUtils.isEmpty(scope) ||  userId == null) {
			return;
		}
		roleMapper.unrelateScopeRolesOfUser(scope, userId);
	}
	
	@Override
	public void relateRoleToGroup(String scope, Long roleId, Long groupId) throws BusinessException {
		RoleDO role = roleMapper.select(roleId);
		if(role == null) {
			throw BusinessException.newInstance(CommonErrorCode.FAILED, "角色不存在");
		}
		GroupDO group = groupService.query(groupId);
		if(group == null) {
			throw BusinessException.newInstance(CommonErrorCode.FAILED, "用户组不存在");
		}
		if(roleMapper.countRoleToGroupRels(roleId, groupId) == 0) {
			roleMapper.relateRoleToGroup(roleId, groupId);
		}
	}
	
	@Override
	public void unrelateRoleToGroup(String scope, Long roleId, Long groupId) throws BusinessException {
		RoleDO role = roleMapper.select(roleId);
		if(role == null) {
			throw BusinessException.newInstance(CommonErrorCode.FAILED, "角色不存在");
		}
		GroupDO group = groupService.query(groupId);
		if(group == null) {
			throw BusinessException.newInstance(CommonErrorCode.FAILED, "用户组不存在");
		}
		roleMapper.unrelateRoleToGroup(roleId, groupId);
	}

}
