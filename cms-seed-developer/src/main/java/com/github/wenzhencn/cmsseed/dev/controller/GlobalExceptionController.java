package com.github.wenzhencn.cmsseed.dev.controller;

import com.github.wenzhencn.cmsseed.common.BusinessException;
import com.github.wenzhencn.cmsseed.common.CommonErrorCode;
import com.github.wenzhencn.cmsseed.common.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理
 * @author zhen.wen
 *
 */
@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionController {
	
	/**
	 * 参数验证异常处理
	 * @param request
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value = { MethodArgumentNotValidException.class })
	public CommonResponse<Object> argumentExceptionHandler(HttpServletRequest request, Exception e) {
		return CommonResponse.newResponse(CommonErrorCode.PARAMETER_INVALID, "参数验证失败");
	}
	
	/**
	 * BusinessException 处理
	 * @param request
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value = BusinessException.class)
	public CommonResponse<Object> businessExceptionHandler(HttpServletRequest request, BusinessException e) {
		return CommonResponse.newResponse(e.getCode(), e.getMessage());
	}
	
	/**
	 * 所有错误处理
	 * @param request
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value = Exception.class)
	public CommonResponse<Object> exceptionHandler(HttpServletRequest request, Exception e) {
		log.error(e.getMessage());
		return CommonResponse.newFailedResponse(e.getMessage());
	}

}
