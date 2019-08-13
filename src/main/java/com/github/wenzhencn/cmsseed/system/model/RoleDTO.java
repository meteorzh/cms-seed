package com.github.wenzhencn.cmsseed.system.model;

import javax.validation.constraints.NotEmpty;

import com.github.wenzhencn.cmsseed.system.entity.RoleDO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * @author zhen.wen
 *
 */
@Getter
@Setter
@ToString
public class RoleDTO {
	
	/**
     *   主键
     */
    private Long id;

    /**
     *   角色码
     */
    @NotEmpty
    private String code;

    /**
     *   角色名
     */
    @NotEmpty
    private String name;
    
    public RoleDO toDO() {
    	RoleDO role = new RoleDO();
    	role.setId(id);
    	role.setCode(code);
    	role.setName(name);
    	return role;
    }

}
