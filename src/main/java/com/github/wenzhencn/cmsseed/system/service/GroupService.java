package com.github.wenzhencn.cmsseed.system.service;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.github.pagehelper.PageInfo;
import com.github.wenzhencn.cmsseed.common.BusinessException;
import com.github.wenzhencn.cmsseed.model.PageParam;
import com.github.wenzhencn.cmsseed.system.entity.GroupDO;
import com.github.wenzhencn.cmsseed.system.model.GroupDTO;

/**
 * 
 * @author zhen.wen
 *
 */
public interface GroupService {
	
	/**
	 * 保存用户组
	 * @param scope
	 * @param group
	 * @throws BusinessException
	 */
	void save(String scope, GroupDO group) throws BusinessException;
	
	/**
	 * 删除用户组
	 * @param scope
	 * @param id
	 * @throws BusinessException 
	 */
	void delete(String scope, Long id) throws BusinessException;
	
	/**
	 * 查询用户组
	 * @param id
	 * @return
	 */
	GroupDO query(Long id);
	
	/**
	 * 查询用户组
	 * @param name
	 * @return
	 */
	List<GroupDO> query(String scope, String name);
	
	/**
	 * 根据多个主键查询用户组
	 * @param ids
	 * @return
	 */
	List<GroupDO> queryByIds(Collection<Long> ids);
	
	/**
	 * 分页查询用户组
	 * @param name
	 * @return
	 */
	PageInfo<GroupDTO> queryPage(PageParam param, String scope, String name);
	
	/**
	 * 为新用户初始化组关系
	 * @param userIds
	 * @return
	 */
	int initRelationForNewUsers(Set<Long> userIds);
	
	/**
	 * 将用户添加到用户组
	 * @param scope
	 * @param groupId
	 * @param userId
	 * @throws BusinessException 
	 */
	void addUserToGroup(String scope, Long groupId, Long userId) throws BusinessException;
	
	/**
	 * 从用户组中删除用户
	 * @param scope
	 * @param groupId
	 * @param userId
	 * @throws BusinessException 
	 */
	void delUserFromGroup(String scope, Long groupId, Long userId) throws BusinessException;

}
