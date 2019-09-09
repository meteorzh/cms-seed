package com.github.wenzhencn.cmsseed.system.mapper;

import com.github.wenzhencn.cmsseed.system.entity.GroupPO;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wenzhen
 * @since 2019-09-05
 */
@Mapper
public interface GroupMapper extends BaseMapper<GroupPO> {
	
	/**
	 * 清除用户组中的所有用户
	 * @param groupId
	 * @return
	 */
	int clearUsersFromGroup(@Param("groupId") Long groupId);

	/**
	 * 查询用户列表中，已在某用户组的用户数量
	 * @param groupId
	 * @param userIds
	 * @return
	 */
	List<Long> countGroupUsersRels(@Param("groupId") Long groupId, @Param("userIds") Collection<Long> userIds);

	/**
	 * 查询用户组列表中，某用户在其中的用户组数量
	 * @param userId
	 * @param groupIds
	 * @return
	 */
	List<Long> countUserGroupsRels(@Param("userId") Long userId, @Param("groupIds") Collection<Long> groupIds);

	/**
	 * 将用户列表添加到用户组
	 * @param groupId
	 * @param userIds
	 * @return
	 */
	int addUsersToGroup(@Param("groupId") Long groupId, @Param("userIds") Collection<Long> userIds);

	/**
	 * 将某用户添加到多个用户组中
	 * @param groupIds
	 * @param userId
	 * @return
	 */
	int addUserToGroups(@Param("groupIds") Collection<Long> groupIds, @Param("userId") Long userId);

}
