package com.github.wenzhencn.cmsseed.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.github.wenzhencn.cmsseed.common.CommonErrorCode;
import com.github.wenzhencn.cmsseed.common.CommonResponse;
import com.github.wenzhencn.cmsseed.utils.WebUtils;

/**
 * 
 * @author zhen.wen
 *
 */
public class RestAuthenticationFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		CommonResponse<Object> resp = CommonResponse.newResponse(CommonErrorCode.AT_AUTHC_FAILED, exception.getMessage());
		WebUtils.responseJson(response, resp);
	}

}
