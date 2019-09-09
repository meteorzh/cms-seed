package com.github.wenzhencn.cmsseed.system.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.github.wenzhencn.cmsseed.system.entity.DictPO;

import lombok.Data;

/**
 * 
 * @author wenzh
 *
 */
@Data
public class DictDTO {
	
	/**
	 * 主键
	 */
	private Integer id;
	
	/**
     * 字典条目名
     */
	@NotEmpty
    private String label;

    /**
     * 字典条目值
     */
	@NotNull
    private Integer value;

    /**
     * 字典条目分类
     */
	@NotEmpty
    private String type;
	
	public DictPO toPO() {
		DictPO po = new DictPO();
		po.setId(id);
		po.setLabel(label);
		po.setValue(value);
		po.setType(type);
		return po;
	}

}
