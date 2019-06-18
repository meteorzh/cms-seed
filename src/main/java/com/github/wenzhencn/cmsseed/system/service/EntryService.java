package com.github.wenzhencn.cmsseed.system.service;

import java.util.List;
import java.util.Set;

import com.github.wenzhencn.cmsseed.system.entity.EntryDO;
import com.github.wenzhencn.cmsseed.system.entity.RoleDO;

/**
 * 
 * @author zhen.wen
 *
 */
public interface EntryService {
	
	/**
	 * 查询用户所有权限
	 * @param userId
	 * @return
	 */
	List<EntryDO> queryByUserAll(Long userId, List<RoleDO> roles);
	
	/**
	 * 查询用户的权限
	 * @param userId
	 * @return
	 */
	List<EntryDO> queryByUser(Long userId);
	
	/**
	 * 查询用户通过组获得的权限
	 * @param userId
	 * @return
	 */
	List<EntryDO> queryByUserFromGroups(Long userId);
	
	/**
	 * 查询角色的权限
	 * @param roleIds
	 * @return
	 */
	List<EntryDO> queryByRoles(Set<Long> roleIds);
	
}
