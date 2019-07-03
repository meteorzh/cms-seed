package com.github.wenzhencn.cmsseed.system.entity;

import com.github.wenzhencn.cmsseed.system.security.EntryLevel;
import com.github.wenzhencn.cmsseed.system.security.EntryTargetType;
import com.github.wenzhencn.cmsseed.system.security.Permission;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 */
@Getter
@Setter
public class EntryDO {
    /**
     *   主键
     */
    private Long id;

    /**
     *   资源ID
     */
    private Integer resourceId;

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

}