package com.github.wenzhencn.cmsseed.system.service;

import java.util.Collection;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.github.wenzhencn.cmsseed.common.BusinessException;
import com.github.wenzhencn.cmsseed.model.PageParam;
import com.github.wenzhencn.cmsseed.system.entity.RoleDO;
import com.github.wenzhencn.cmsseed.system.model.RoleDTO;
import com.github.wenzhencn.cmsseed.system.model.UserRoleRel;

/**
 * 
 * @author zhen.wen
 *
 */
public interface RoleService {
	
	/**
	 * 保存角色
	 * @param role
	 * @throws BusinessException 
	 */
	void save(String scope, RoleDTO role) throws BusinessException;
	
	/**
	 * 删除角色
	 * @param roleId
	 * @throws BusinessException 
	 */
	void delete(String scope, Long roleId) throws BusinessException;
	
	/**
	 * 根据角色名查询域内角色
	 * @param name
	 * @return
	 */
	List<RoleDO> query(String scope, String name);
	
	/**
	 * 通过主键查询角色
	 * @param id
	 * @return
	 */
	RoleDO query(Long id);
	
	/**
	 * 分页查询角色
	 * @param param
	 * @param scope
	 * @param name
	 * @return
	 */
	PageInfo<RoleDO> queryPage(PageParam param, String scope, String name);
	
	/**
	 * 查询域内角色
	 * @param scope
	 * @param roleId
	 * @return
	 */
	List<RoleDO> queryScopeRoles(String scope);
	
	/**
	 * 查询用户所有角色<br />
	 * 包括所在用户组的角色
	 * @param userId
	 * @return
	 */
	List<RoleDO> queryByUserAll(Long userId);
	
	/**
	 * 查询用户关联的角色
	 * @param userId
	 * @return
	 */
	List<RoleDO> queryByUser(Long userId);
	
	/**
	 * 查询用户通过组获取的角色
	 * @param userId
	 * @return
	 */
	List<RoleDO> queryByUserFromGroups(Long userId);
	
	/**
	 * 根据多个主键查询角色
	 * @param ids
	 * @return
	 */
	List<RoleDO> queryByIds(Collection<Long> ids);
	
	/**
	 * 根据域和用户ID查询用户的角色关系
	 * @param addOnCode
	 * @param userIds
	 * @return
	 */
	List<UserRoleRel> queryRolesByUsers(String scope, Collection<Long> userIds);
	
	/**
	 * 将角色关联给用户
	 * @param roleId
	 * @param userId
	 * @throws BusinessException 
	 */
	void relateRoleToUser(String scope, Long roleId, Long userId) throws BusinessException;
	
	/**
	 * 取消角色和用户关系
	 * @param scope
	 * @param roleId
	 * @param userId
	 * @throws BusinessException 
	 */
	void unrelateRoleToUser(String scope, Long roleId, Long userId) throws BusinessException;
	
	/**
	 * 取消用户在某个域的所有角色
	 * @param scope
	 * @param userId
	 */
	void unrelateScopeRolesOfUser(String scope, Long userId);
	
	/**
	 * 将角色关联给用户组
	 * @param roleId
	 * @param userId
	 * @throws BusinessException 
	 */
	void relateRoleToGroup(String scope, Long roleId, Long groupId) throws BusinessException;
	
	/**
	 * 取消角色和用户组关系
	 * @param scope
	 * @param roleId
	 * @param userId
	 * @throws BusinessException 
	 */
	void unrelateRoleToGroup(String scope, Long roleId, Long groupId) throws BusinessException;

}
