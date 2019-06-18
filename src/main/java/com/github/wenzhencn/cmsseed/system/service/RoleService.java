package com.github.wenzhencn.cmsseed.system.service;

import java.util.List;

import com.github.wenzhencn.cmsseed.system.entity.RoleDO;

/**
 * 
 * @author zhen.wen
 *
 */
public interface RoleService {
	
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

}
