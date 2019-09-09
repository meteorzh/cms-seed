package com.github.wenzhencn.cmsseed.system.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.wenzhencn.cmsseed.common.CommonResponse;
import com.github.wenzhencn.cmsseed.common.PageParam;
import com.github.wenzhencn.cmsseed.system.entity.PermissionPO;
import com.github.wenzhencn.cmsseed.system.model.PermissionDTO;
import com.github.wenzhencn.cmsseed.system.service.IPermissionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 权限表 前端控制器
 * </p>
 *
 * @author wenzhen
 * @since 2019-09-07
 */
@Api(tags = "权限接口")
@Slf4j
@RestController
public class PermissionController {
	
	@Autowired
	private IPermissionService permissionService;
	
	@ApiOperation("保存权限")
	@PostMapping("/sys/pms/save")
	public CommonResponse<Object> save(@Validated @RequestBody PermissionDTO pms) {
		log.debug("保存权限: permission={}", pms.toString());
		return CommonResponse.newSuccessResponse();
	}
	
	@ApiOperation("删除权限")
	@DeleteMapping("/sys/pms/del/{id}")
	public CommonResponse<Object> delete(@PathVariable("id") Long id) {
		log.debug("删除权限: id={}", id);
		return CommonResponse.newSuccessResponse();
	}
	
	@ApiOperation("分页查询权限")
	@GetMapping("/sys/pms/page")
	public CommonResponse<IPage<PermissionPO>> page(PageParam page) {
		log.debug("分页查询权限: page={}", page.toString());
		return CommonResponse.newSuccessResponse();
	}

}

