package com.github.wenzhencn.cmsseed.system.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 */
@Getter
@Setter
public class RoleDO {
	
    /**
     *   主键
     */
    private Long id;

    /**
     *   角色码
     */
    private String code;

    /**
     *   角色名
     */
    private String name;

}