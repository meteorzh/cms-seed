package com.github.wenzhencn.cmsseed.system.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.wenzhencn.cmsseed.common.BusinessException;
import com.github.wenzhencn.cmsseed.common.CommonResponse;
import com.github.wenzhencn.cmsseed.common.PageParam;
import com.github.wenzhencn.cmsseed.system.entity.GroupPO;
import com.github.wenzhencn.cmsseed.system.model.GroupDTO;
import com.github.wenzhencn.cmsseed.system.service.IGroupService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wenzhen
 * @since 2019-09-05
 */
@Api(tags = "用户组接口")
@Slf4j
@RestController
public class GroupController {
	
	@Autowired
	private IGroupService groupService;
	
	/**
	 * 保存用户组
	 * @param group
	 * @return
	 * @throws BusinessException
	 */
	@ApiOperation("保存用户组")
	@PostMapping("/sys/group/save")
	public CommonResponse<Object> save(@Validated @RequestBody GroupDTO group) throws BusinessException {
		log.debug("保存用户组: group={}", group.toString());
		if(group.getId() == null) {
			groupService.add(group.toPO());
		} else {
			groupService.update(group.toPO());
		}
		return CommonResponse.newSuccessResponse();
	}
	
	/**
	 * 删除用户组
	 * @param id
	 * @return
	 * @throws BusinessException
	 */
	@ApiOperation("删除用户组")
	@DeleteMapping("/sys/group/del/{id}")
	public CommonResponse<Object> delete(@PathVariable("id") Long id) throws BusinessException {
		log.debug("删除用户组: id={}", id);
		groupService.delete(id);
		return CommonResponse.newSuccessResponse();
	}
	
	/**
	 * 分页查询用户组
	 * @param page
	 * @param name
	 * @return
	 */
	@ApiOperation("分页查询用户组")
	@GetMapping("/sys/group/page")
	public CommonResponse<IPage<GroupPO>> page(PageParam page, String name) {
		log.debug("分页查询用户组: page={}", page.toString());
		QueryWrapper<GroupPO> wrapper = new QueryWrapper<>();
		if(!StringUtils.isEmpty(name)) {
			wrapper.like("name", name);
		}
		IPage<GroupPO> groups = groupService.page(new Page<>(page.getPageNo(), page.getPageSize()), wrapper);
		return CommonResponse.newSuccessResponse(groups);
	}

	/**
	 * 将多个用户加入到用户组中
	 * @param groupId
	 * @param userIds
	 * @return
	 * @throws BusinessException
	 */
	@ApiOperation("向用户组添加多个用户")
	@PostMapping("/sys/group/addusers")
	public CommonResponse<Object> addUsers(@RequestParam("groupId") @NotNull Long groupId, @RequestParam("userIds") @NotEmpty List<Long> userIds) throws BusinessException {
		log.debug("向用户组添加多个用户: groupId={}, users={}");
		groupService.addUsersToGroup(groupId, userIds);
		return CommonResponse.newSuccessResponse();
	}

}

