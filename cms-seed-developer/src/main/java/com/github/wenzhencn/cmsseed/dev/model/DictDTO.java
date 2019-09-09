package com.github.wenzhencn.cmsseed.dev.model;

import com.github.wenzhencn.cmsseed.dev.entity.DictPO;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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

	/**
	 * 顺序
	 */
	private Integer order;
	
	public DictPO toPO() {
		DictPO po = new DictPO();
		po.setId(id);
		po.setLabel(label);
		po.setValue(value);
		po.setType(type);
		po.setOrder(order);
		return po;
	}

}
