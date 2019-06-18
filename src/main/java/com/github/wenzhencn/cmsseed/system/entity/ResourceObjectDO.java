package com.github.wenzhencn.cmsseed.system.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 */
@Getter
@Setter
public class ResourceObjectDO {
	
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
     *   所有者ID
     */
    private Long ownerId;

}