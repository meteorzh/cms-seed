package com.github.wenzhencn.cmsseed.common;

/**
 * 错误码
 * @author zhen.wen
 *
 */
public interface ErrorCode {
	
	/**
	 * 字典条目不存在
	 */
	String DICT_NOT_FOUND = "DC0001";
	
	/**
	 * 字典值已存在
	 */
	String DICT_EXIST = "DC0002";
	
	/**
	 * 组已存在
	 */
	String GROUP_EXIST = "GP0001";
	
	/**
	 * 组不存在
	 */
	String GROUP_NOT_FOUND = "GP0002";

	/**
	 * 用户不存在
	 */
	String USER_NOT_FOUND = "US0001";
	
}
