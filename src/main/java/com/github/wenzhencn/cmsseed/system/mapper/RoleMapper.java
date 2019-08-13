package com.github.wenzhencn.cmsseed.system.mapper;

import java.util.Collection;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.github.wenzhencn.cmsseed.system.entity.RoleDO;
import com.github.wenzhencn.cmsseed.system.model.UserRoleRel;

/**
 * 
 * @author zhen.wen
 *
 */
@Mapper
public interface RoleMapper extends BaseMapper<RoleDO> {
	
	/**
	 * 根据角色名查询域内角色
	 * @param name
	 * @return
	 */
	List<RoleDO> selectByName(@Param("scope") String scope, @Param("name") String name);
	
	/**
	 * 根据编码查询域内角色
	 * @param scope
	 * @param code
	 * @return
	 */
	RoleDO selectByCode(@Param("code") String code);

	/**
	 * 查询多个用户组的角色
	 * @param groupIds
	 * @return
	 */
	List<RoleDO> selectByGroups(@Param("groupIds") Collection<Integer> groupIds);
	
	/**
	 * 查询用户的角色
	 * @param userId
	 * @return
	 */
	List<RoleDO> selectByUser(@Param("userId") Long userId);
	
	/**
	 * 查询用户通过组获取的角色
	 * @param userId
	 * @return
	 */
	List<RoleDO> selectByUserFromGroups(@Param("userId") Long userId);
	
	/**
	 * 根据多个主键查询角色
	 * @param ids
	 * @return
	 */
	List<RoleDO> selectByIds(@Param("ids") Collection<Long> ids);
	
	/**
	 * 根据域和用户ID列表查询用户和角色关系
	 * @param scope
	 * @param userIds
	 * @return
	 */
	List<UserRoleRel> selectScopeUserRoleRels(@Param("scope") String scope, @Param("userIds") Collection<Long> userIds);
	
	/**
	 * 根据域查询角色
	 * @param scope
	 * @param roleId
	 * @return
	 */
	List<RoleDO> selectScopeRoles(@Param("scope") String scope);
	
	/**
	 * 某角色和某用户的关联关系计数
	 * @param roleId
	 * @param userId
	 * @return
	 */
	int countRoleToUserRels(@Param("roleId") Long roleId, @Param("userId") Long userId);
	
	/**
	 * 某角色和用户组关系计数
	 * @param roleId
	 * @return
	 */
	int countRoleGroupRels(@Param("roleId") Long roleId);
	
	/**
	 * 某角色和用户关系计数
	 * @param roleId
	 * @return
	 */
	int countRoleUserRels(@Param("roleId") Long roleId);
	
	/**
	 * 将角色关联给用户
	 * @param roleId
	 * @param userId
	 * @return
	 */
	int relateRoleToUser(@Param("roleId") Long roleId, @Param("userId") Long userId);
	
	/**
	 * 取消角色和用户关系
	 * @param roleId
	 * @param userId
	 * @return
	 */
	int unrelateRoleToUser(@Param("roleId") Long roleId, @Param("userId") Long userId);
	
	/**
	 * 取消某用户的某个域所有角色
	 * @param scope
	 * @param userId
	 * @return
	 */
	int unrelateScopeRolesOfUser(@Param("scope") String scope, @Param("userId") Long userId);
	
	/**
	 * 某角色和某用户组的关联关系计数
	 * @param roleId
	 * @param userId
	 * @return
	 */
	int countRoleToGroupRels(@Param("roleId") Long roleId, @Param("groupId") Long groupId);
	
	/**
	 * 将角色关联给用户组
	 * @param roleId
	 * @param groupId
	 * @return
	 */
	int relateRoleToGroup(@Param("roleId") Long roleId, @Param("groupId") Long groupId);
	
	/**
	 * 取消角色和用户组关系
	 * @param roleId
	 * @param groupId
	 * @return
	 */
	int unrelateRoleToGroup(@Param("roleId") Long roleId, @Param("groupId") Long groupId);

}