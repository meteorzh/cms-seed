package com.github.wenzhencn.cmsseed.system.model;

import com.github.wenzhencn.cmsseed.system.entity.RolePO;
import lombok.Data;

/**
 * 
 * @author wenzh
 *
 */
@Data
public class RoleDTO {
	
	/**
     * 主键
     */
    private Long id;

    /**
     * 角色码
     */
    private String code;

    /**
     * 角色名
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 是否是系统保留角色
     */
    private Boolean sys;

    public RolePO toPO() {
        RolePO role = new RolePO();
        role.setId(id);
        role.setCode(code);
        role.setName(name);
        role.setDescription(description);
        role.setSys(sys);
        return role;
    }

}
