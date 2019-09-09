package com.github.wenzhencn.cmsseed.system.model;

import javax.validation.constraints.NotEmpty;

import com.github.wenzhencn.cmsseed.system.entity.GroupPO;

import lombok.Data;

/**
 * 
 * @author wenzh
 *
 */
@Data
public class GroupDTO {
	
	/**
     * 主键
     */
    private Long id;

    /**
     * 组名
     */
    @NotEmpty
    private String name;

    /**
     * 组说明
     */
    private String description;
    
    public GroupPO toPO() {
    	GroupPO po = new GroupPO();
    	po.setId(id);
    	po.setName(name);
    	po.setDescription(description);
    	return po;
    }

}
