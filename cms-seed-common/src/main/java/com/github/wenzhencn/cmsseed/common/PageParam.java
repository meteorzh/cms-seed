package com.github.wenzhencn.cmsseed.common;

import lombok.Data;

/**
 * 
 * @author zhen.wen
 *
 */
@Data
public class PageParam {
	
	/**
	 * 页码
	 */
	private Integer pageNo = 1;
	
	/**
	 * 页长
	 */
	private Integer pageSize = 10;

}
