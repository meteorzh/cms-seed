package com.github.wenzhencn.cmsseed.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author zhen.wen
 *
 */
@Getter
@Setter
public class PageParam {
	
	/**
	 * 页码
	 */
	private Integer pageNo;
	
	/**
	 * 页长
	 */
	private Integer pageSize = 10;

}
