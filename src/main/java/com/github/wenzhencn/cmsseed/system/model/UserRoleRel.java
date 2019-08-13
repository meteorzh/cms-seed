package com.github.wenzhencn.cmsseed.system.model;

import com.github.wenzhencn.cmsseed.system.entity.RoleDO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRoleRel {
	
	/**
	 * 用户ID
	 */
	private Long userId;
	
	/**
	 * 角色
	 */
	private RoleDO role;

}
