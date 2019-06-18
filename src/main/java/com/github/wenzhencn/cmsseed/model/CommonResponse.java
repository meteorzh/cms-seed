package com.github.wenzhencn.cmsseed.model;

import com.github.wenzhencn.cmsseed.common.CommonErrorCode;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 通用返回封装
 * @author zhen.wen
 *
 * @param <T>
 */
@Getter
@Setter
@NoArgsConstructor
public class CommonResponse<T> {
	
	/**
	 * 消息码
	 */
	private String code;
	
	/**
	 * 消息
	 */
	private String message;
	
	/**
	 * 数据
	 */
	private T data;
	
	private CommonResponse(String code, String message) {
		this.code = code;
		this.message = message;
	}

    /**
     * 根据一个异常获取一个回复对象
     * @param code
     * @param message
     * @param <T>
     * @return
     */
	public static <T> CommonResponse<T> newResponse(String code, String message) {
		return new CommonResponse<>(code, message);
	}

    /**
     * 在一个已有的回复对象基础上获取一个新的回复对象<br />
     * 丢弃{@linkplain CommonResponse#data}字段
     * @param rsp
     * @param <T>
     * @return
     */
	public static <T> CommonResponse<T> newResponse(CommonResponse<?> rsp) {
		CommonResponse<T> res = new CommonResponse<>();
		res.setCode(rsp.getCode());
		res.setMessage(rsp.getMessage());
		return res;
	}
	
	/**
	 * 获取一个空数据成功的回复对象
	 * @return
	 */
	public static <T> CommonResponse<T> newSuccessResponse() {
		return new CommonResponse<>(CommonErrorCode.SUCCESS, null);
	}
	
	/**
	 * 根据数据获取一个成功的回复对象
	 * @param data
	 * @return
	 */
	public static <T> CommonResponse<T> newSuccessResponse(T data) {
		CommonResponse<T> resp = newSuccessResponse();
		resp.setData(data);
		return resp;
	}
	
	/**
	 * 获取一个默认的失败回复对象
	 * @return
	 */
	public static <T> CommonResponse<T> newFailedResponse() {
		return new CommonResponse<>(CommonErrorCode.FAILED, null);
	}
	
	/**
	 * 获取一个自定义消息，默认错误码的失败回复对象
	 * @param message
	 * @return
	 */
	public static <T> CommonResponse<T> newFailedResponse(String message) {
		CommonResponse<T> resp = newFailedResponse();
		resp.setMessage(message);
		return resp;
	}
	
	/**
	 * 是否成功<br />
	 * 判定响应状态
	 * @return
	 */
	public boolean success() {
		return CommonErrorCode.SUCCESS.equals(getCode());
	}
	
	/**
	 * 响应是否成功并返回数据<br />
	 * 响应成功但是没有数据返回也判定为失败<br />
	 * 如果仅需要判断响应状态请使用{@link #success()}
	 * @return
	 */
	public boolean successWithData() {
		return success() && data != null;
	}
	
}
