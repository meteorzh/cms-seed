package com.github.wenzhencn.cmsseed.system.service;

import com.github.wenzhencn.cmsseed.common.BusinessException;
import com.github.wenzhencn.cmsseed.system.entity.GroupPO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wenzhen
 * @since 2019-09-05
 */
public interface IGroupService extends IService<GroupPO> {
	
	/**
	 * 创建用户组
	 * @param group
	 * @throws BusinessException
	 */
	void add(GroupPO group) throws BusinessException;
	
	/**
	 * 修改用户组
	 * @param group
	 * @throws BusinessException
	 */
	void update(GroupPO group) throws BusinessException;
	
	/**
	 * 删除用户组
	 * @param id
	 * @throws BusinessException
	 */
	void delete(Long id) throws BusinessException;
	
	/**
	 * 清除用户组中的所有用户
	 * @param groupId
	 */
	void clearUsersFromGroup(Long groupId);

	/**
	 * 向用户组添加多个用户
	 * @param groupId
	 * @param userIds
	 */
	void addUsersToGroup(Long groupId, List<Long> userIds) throws BusinessException;

	/**
	 * 将用户添加到多个用户组
	 * @param groupIds
	 * @param userId
	 */
	void addUserToGroups(List<Long> groupIds, Long userId) throws BusinessException;

	/**
	 * 查询用户组ID列表中合法用户组的数量
	 * @param groupIds
	 * @return
	 */
	int countByGroupIds(Collection<Long> groupIds);
	
}
