package com.github.wenzhencn.cmsseed.system.entity;

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
    private Byte mask;

    /**
     *   权限级别
     */
    private Byte level;

    /**
     *   目标类型
     */
    private Byte targetType;

    /**
     *   目标ID
     */
    private Long targetId;

}