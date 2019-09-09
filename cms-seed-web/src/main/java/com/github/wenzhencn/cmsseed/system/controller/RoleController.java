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
import com.github.wenzhencn.cmsseed.system.entity.RolePO;
import com.github.wenzhencn.cmsseed.system.model.RoleDTO;
import com.github.wenzhencn.cmsseed.system.service.IRoleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 *  角色接口
 * </p>
 *
 * @author wenzhen
 * @since 2019-09-05
 */
@Api(tags = "角色接口")
@Slf4j
@RestController
public class RoleController {
	
	@Autowired
	private IRoleService roleService;
	
	@ApiOperation("保存角色")
	@PostMapping("/sys/role/save")
	public CommonResponse<Object> save(@Validated @RequestBody RoleDTO role) {
		log.debug("保存角色: role={}", role.toString());
		return CommonResponse.newSuccessResponse();
	}
	
	@ApiOperation("删除角色")
	@DeleteMapping("/sys/role/del/{id}")
	public CommonResponse<Object> delete(@PathVariable("id") Long id) {
		log.debug("删除角色: id={}", id);
		return CommonResponse.newSuccessResponse();
	}
	
	@ApiOperation("分页查询角色")
	@GetMapping("/sys/role/page")
	public CommonResponse<IPage<RolePO>> page(PageParam page) {
		log.debug("分页查询角色: page={}", page.toString());
		return CommonResponse.newSuccessResponse();
	}

}

