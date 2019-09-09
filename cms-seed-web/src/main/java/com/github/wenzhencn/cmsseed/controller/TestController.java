package com.github.wenzhencn.cmsseed.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.wenzhencn.cmsseed.common.CommonResponse;

/**
 * 
 * @author zhen.wen
 *
 */
@RestController
public class TestController {
	
	@GetMapping("/api/test/test")
	@PreAuthorize("authenticated and hasPermission('TEST', 'A')")
	public CommonResponse<String> test() {
		return CommonResponse.newSuccessResponse("好的");
	}

}
