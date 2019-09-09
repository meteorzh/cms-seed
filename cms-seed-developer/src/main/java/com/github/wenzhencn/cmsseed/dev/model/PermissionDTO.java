package com.github.wenzhencn.cmsseed.dev.model;

import com.github.wenzhencn.cmsseed.dev.entity.PermissionPO;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
    @NotEmpty
    private String code;

    /**
     * 权限名
     */
    @NotEmpty
    private String label;

    /**
     * 权限说明
     */
    private String description;

    /**
     * 权限分组
     */
    @NotNull
    private Integer category;

    public PermissionPO toPO() {
        PermissionPO pms = new PermissionPO();
        pms.setId(id);
        pms.setCode(code);
        pms.setLabel(label);
        pms.setDescription(description);
        pms.setCategory(category);
        return pms;
    }

}
