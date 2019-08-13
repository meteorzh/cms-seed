package com.github.wenzhencn.cmsseed.system.model;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.github.wenzhencn.cmsseed.system.entity.GroupDO;
import com.github.wenzhencn.cmsseed.system.entity.RoleDO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupDTO {
	
	/**
     *   主键
     */
    private Long id;

    /**
     *   组名
     */
    @NotEmpty
    private String name;

    /**
     *   组说明
     */
    private String description;
    
    /**
     * 所属域
     */
    private String scope;
    
    // 返回数据
    
    /**
     * 用户组关联的角色
     */
    private List<RoleDO> roles;
    
    public GroupDO toDO() {
    	GroupDO group = new GroupDO();
    	group.setId(id);
    	group.setName(name);
    	group.setDescription(description);
    	return group;
    }

}
