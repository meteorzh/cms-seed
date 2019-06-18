package com.github.wenzhencn.cmsseed.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.github.wenzhencn.cmsseed.common.CommonErrorCode;
import com.github.wenzhencn.cmsseed.model.CommonResponse;
import com.github.wenzhencn.cmsseed.utils.WebUtils;

/**
 * RestAccessDeniedHandler
 * @author zhen.wen
 *
 */
public class RestAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		CommonResponse<Object> resp = CommonResponse.newResponse(CommonErrorCode.AT_ACCESS_DENIED, accessDeniedException.getMessage());
		WebUtils.responseJson(response, resp);
	}

}
