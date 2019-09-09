package com.github.wenzhencn.cmsseed.system.service;

import com.github.wenzhencn.cmsseed.system.security.SecurityUser;

/**
 * 
 * @author zhen.wen
 *
 */
public interface SystemService {
	
	/**
	 * 根据用户ID查询安全用户对象
	 * @param userId
	 * @return
	 */
	SecurityUser querySecurityUser(Long userId, String username);

}
