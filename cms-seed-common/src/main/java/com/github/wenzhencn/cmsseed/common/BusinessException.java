package com.github.wenzhencn.cmsseed.common;

import lombok.Getter;

/**
 * 
 * @author zhen.wen
 *
 */
@Getter
public class BusinessException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String code;
	
	private BusinessException(String code, String message) {
		super(message);
		this.code = code;
	}
	
	public static BusinessException newInstance(String code, String message) {
		return new BusinessException(code, message);
	}

}
