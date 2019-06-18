package com.github.wenzhencn.cmsseed.common;

/**
 * 公用错误常量<br />
 * 系统错误码ST<br />
 * 认证错误码AT
 * @author zhen.wen
 *
 */
public interface CommonErrorCode {
	
	//通用相关错误码
	
	/**
	 * 成功
	 */
	public static final String SUCCESS           = "ST0000";
	
	/**
	 * 失败
	 */
	public static final String FAILED            = "ST1111";
	
	/**
	 * 参数错误
	 */
	public static final String PARAMETER_INVALID = "ST0001";
	
	/**
	 * 接口功能未被实现
	 */
	public static final String NOT_IMPLEMENTED   = "ST0002";
	
	//认证相关错误码
	
	/**
	 * 拒绝访问
	 */
	public static final String AT_ACCESS_DENIED  = "AT0001";
	
	/**
	 * 未登录
	 */
	public static final String AT_NOT_AUTHC      = "AT0002";
	
	/**
	 * 未授权
	 */
	public static final String AT_NOT_AUTHO      = "AT0003";
	
	/**
	 * 用户错误
	 */
	public static final String AT_USER_DISABLED  = "AT0004";
	
	/**
	 * 凭证错误
	 */
	public static final String AT_BAD_CREDENTIAL = "AT0005";
	
	/**
	 * 登录失败
	 */
	public static final String AT_AUTHC_FAILED   = "AT0006";
	
}
