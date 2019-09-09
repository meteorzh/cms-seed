package com.github.wenzhencn.cmsseed.system.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.github.wenzhencn.cmsseed.system.model.Entry;
import com.github.wenzhencn.cmsseed.system.security.SecurityUser;

/**
 * 
 * @author zhen.wen
 *
 */
@Service
public class SystemServiceImpl implements SystemService {
	
	@Autowired
	private IRoleService roleService;
	
	@Autowired
	private IEntryService entryService;

	@Override
	public SecurityUser querySecurityUser(Long userId, String username) {
		// 查询角色
//		List<RoleDO> roles = roleService.queryByUserAll(userId);
//		Set<GrantedAuthority> gas = roles.stream().map(role -> {
//			return new SimpleGrantedAuthority(role.getCode());
//		}).collect(Collectors.toSet());
//		// 查询权限
//		List<Entry> entrys = entryService.queryByUserAll(userId, roles);
//		Set<String> entryStrs = entrys.stream().map(entry -> { return entry.entryString(); }).collect(Collectors.toSet());
//		SecurityUser user = new SecurityUser(userId, username, "", gas, entryStrs);
//		return user;
		return null;
	}

}
