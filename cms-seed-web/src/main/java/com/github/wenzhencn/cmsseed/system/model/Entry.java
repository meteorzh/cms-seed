package com.github.wenzhencn.cmsseed.system.model;

import com.github.wenzhencn.cmsseed.system.security.EntryLevel;
import com.github.wenzhencn.cmsseed.system.security.EntryTargetType;
import com.github.wenzhencn.cmsseed.system.security.Permission;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author zhen.wen
 *
 */
@Getter
@Setter
public class Entry {
	
	/**
     *   主键
     */
    private Long id;

    /**
     *   资源ID
     */
    private Integer resourceId;
    
    /**
     *   资源Code
     */
    private String resourceCode;

    /**
     *   资源实例ID
     */
    private Long resourceObjectId;

    /**
     *   权限码
     */
    private Permission mask;

    /**
     *   权限级别
     */
    private EntryLevel level;

    /**
     *   目标类型
     */
    private EntryTargetType targetType;

    /**
     *   目标ID
     */
    private Long targetId;
    
    public String entryString() {
    	return resourceCode + ":" + mask.getCode() + (level == EntryLevel.INSTANCE ? (":" + String.valueOf(resourceObjectId)) : "");
    }

}
