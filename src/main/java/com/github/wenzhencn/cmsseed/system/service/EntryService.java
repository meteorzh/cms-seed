package com.github.wenzhencn.cmsseed.system.service;

import java.util.List;
import java.util.Set;

import com.github.wenzhencn.cmsseed.system.entity.EntryDO;
import com.github.wenzhencn.cmsseed.system.entity.RoleDO;
import com.github.wenzhencn.cmsseed.system.model.Entry;
import com.github.wenzhencn.cmsseed.system.security.EntryTargetType;

/**
 * 
 * @author zhen.wen
 *
 */
public interface EntryService {
	
	/**
	 * 创建Entry
	 * @param entry
	 */
	void add(EntryDO entry);
	
	/**
	 * 删除Entry
	 * @param entryId
	 */
	void delete(Long entryId);
	
	/**
	 * 根据资源删除其关联的权限
	 * @param resourceId
	 * @param resourceObjectId
	 */
	void deleteByResource(Integer resourceId, Long resourceObjectId);
	
	/**
	 * 删除权限Target的权限
	 * @param targetType
	 * @param targetId
	 */
	void deleteByTarget(EntryTargetType targetType, Long targetId);
	
	/**
	 * 查询用户所有权限
	 * @param userId
	 * @return
	 */
	List<Entry> queryByUserAll(Long userId, List<RoleDO> roles);
	
	/**
	 * 查询用户的权限
	 * @param userId
	 * @return
	 */
	List<Entry> queryByUser(Long userId);
	
	/**
	 * 查询用户通过组获得的权限
	 * @param userId
	 * @return
	 */
	List<Entry> queryByUserFromGroups(Long userId);
	
	/**
	 * 查询角色的权限
	 * @param roleIds
	 * @return
	 */
	List<Entry> queryByRoles(Set<Long> roleIds);
	
	/**
	 * 查询Entry列表
	 * @param query
	 * @return
	 */
	List<EntryDO> queryList(EntryDO query);
	
	/**
	 * 根据ID查询权限配置
	 * @param entryId
	 * @return
	 */
	EntryDO query(Long entryId);
	
	/**
	 * 查询权限目标具有的权限数量
	 * @param targetType
	 * @param targetId
	 * @return
	 */
	int countByTarget(EntryTargetType targetType, Long targetId);
	
}
