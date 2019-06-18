package com.github.wenzhencn.cmsseed.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.github.wenzhencn.cmsseed.common.CommonErrorCode;
import com.github.wenzhencn.cmsseed.model.CommonResponse;
import com.github.wenzhencn.cmsseed.utils.WebUtils;

/**
 * 
 * @author zhen.wen
 *
 */
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		CommonResponse<Object> resp = CommonResponse.newResponse(CommonErrorCode.AT_NOT_AUTHC, authException.getMessage());
		WebUtils.responseJson(response, resp);
	}

}
