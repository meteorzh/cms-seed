package com.github.wenzhencn.cmsseed.system.model;

import lombok.Data;

/**
 * 
 * @author wenzh
 *
 */
@Data
public class PermissionDTO {
	
	/**
     * 主键
     */
    private Long id;

    /**
     * 权限编码
     */
    private String code;

    /**
     * 权限名
     */
    private String label;

    /**
     * 权限说明
     */
    private String description;

}
