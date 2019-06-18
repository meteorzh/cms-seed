package com.github.wenzhencn.cmsseed.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.github.wenzhencn.cmsseed.model.CommonResponse;
import com.github.wenzhencn.cmsseed.utils.WebUtils;

/**
 * 
 * @author zhen.wen
 *
 */
public class RestLogoutSuccessHandler implements LogoutSuccessHandler {

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		CommonResponse<Object> resp = CommonResponse.newSuccessResponse();
		WebUtils.responseJson(response, resp);
	}

}
