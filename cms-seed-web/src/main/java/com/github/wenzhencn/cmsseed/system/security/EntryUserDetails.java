package com.github.wenzhencn.cmsseed.system.security;

import java.util.Set;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * 具有 Entry 的用户信息
 * @author zhen.wen
 *
 */
public interface EntryUserDetails extends UserDetails {
	
	/**
	 * 获取用户ID
	 * @return
	 */
	Long getUserId();
	
	/**
	 * 获取用户Entry信息
	 * @return
	 */
	public Set<String> getEntrys();

}
