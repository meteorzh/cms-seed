package com.github.wenzhencn.cmsseed.system.mapper;

import java.util.Collection;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.github.wenzhencn.cmsseed.system.entity.GroupDO;
import com.github.wenzhencn.cmsseed.system.model.GroupDTO;

/**
 * 
 * @author zhen.wen
 *
 */
@Mapper
public interface GroupMapper extends BaseMapper<GroupDO> {
	
	/**
	 * 根据组名查询用户组
	 * @param scope
	 * @param name
	 * @return
	 */
	GroupDO selectUniqueByName(@Param("name") String name);

	/**
	 * 根据组名查询
	 * @param name
	 * @return
	 */
	List<GroupDO> selectByName(@Param("scope") String scope, @Param("name") String name);
	
	/**
	 * 根据多个主键查询用户组
	 * @param ids
	 * @return
	 */
	List<GroupDO> selectByIds(@Param("ids") Collection<Long> ids);
	
	/**
	 * 根据主键查询用户组详细信息
	 * @param ids
	 * @return
	 */
	List<GroupDTO> selectDTOByIds(@Param("ids") Collection<Long> ids);
	
	/**
	 * 将用户添加到用户组
	 * @param userId
	 * @param groupId
	 * @return
	 */
	int relateUsersToGroup(@Param("userIds") Collection<Long> userIds, @Param("groupId") Long groupId);
	
	/**
	 * 将用户从用户组中移除
	 * @param userId
	 * @param groupId
	 * @return
	 */
	int unrelateUserFromGroup(@Param("userId") Long userId, @Param("groupId") Long groupId);
	
	/**
	 * 删除用户组中所有用戶
	 * @param groupId
	 * @return
	 */
	int unrelateGroupUsers(@Param("groupId") Long groupId);
	
	/**
	 * 删除用户组的所有角色
	 * @param groupId
	 * @return
	 */
	int unrelateGroupRoles(@Param("groupId") Long groupId);
	
	/**
	 * 用户组中的用户数
	 * @param groupId
	 * @return
	 */
	int countGroupUsers(@Param("groupId") Long groupId);
	
	/**
	 * 用户组中的角色数
	 * @param groupId
	 * @return
	 */
	int countGroupRoles(@Param("groupId") Long groupId);

}