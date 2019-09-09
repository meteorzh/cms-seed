package com.github.wenzhencn.cmsseed.dev.model;

import com.github.wenzhencn.cmsseed.dev.entity.GroupPO;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

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

    /**
     * 是否是系统保留用户组
     */
    private Boolean sys;
    
    public GroupPO toPO() {
    	GroupPO po = new GroupPO();
    	po.setId(id);
    	po.setName(name);
    	po.setDescription(description);
    	po.setSys(sys);
    	return po;
    }

}
