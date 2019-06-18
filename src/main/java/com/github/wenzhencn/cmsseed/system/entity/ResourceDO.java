package com.github.wenzhencn.cmsseed.system.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 */
@Getter
@Setter
public class ResourceDO {
    /**
     *   主键
     */
    private Integer id;

    /**
     *   资源编码
     */
    private String code;

    /**
     *   资源名
     */
    private String name;

}