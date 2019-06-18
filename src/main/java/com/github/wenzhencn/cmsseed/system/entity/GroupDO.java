package com.github.wenzhencn.cmsseed.system.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 */
@Getter
@Setter
public class GroupDO {
    /**
     *   主键
     */
    private Long id;

    /**
     *   组名
     */
    private String name;

    /**
     *   组说明
     */
    private String description;

}