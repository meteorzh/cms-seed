package com.github.wenzhencn.cmsseed.system.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.wenzhencn.cmsseed.common.BusinessException;
import com.github.wenzhencn.cmsseed.common.CommonErrorCode;
import com.github.wenzhencn.cmsseed.common.CommonResponse;
import com.github.wenzhencn.cmsseed.common.ErrorCode;
import com.github.wenzhencn.cmsseed.common.PageParam;
import com.github.wenzhencn.cmsseed.system.entity.DictPO;
import com.github.wenzhencn.cmsseed.system.model.DictDTO;
import com.github.wenzhencn.cmsseed.system.service.IDictService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 字典表 前端控制器
 * </p>
 *
 * @author wenzhen
 * @since 2019-09-05
 */
@RestController
@Slf4j
@Api(tags = "数据字典接口")
public class DictController {
	
	@Autowired
	private IDictService dictService;
	
	/**
	 * 保存字典信息
	 * @param dict
	 * @return
	 * @throws BusinessException 
	 */
	@ApiOperation("保存字典信息")
	@PreAuthorize("hasRole('DEVELOPER')")
	@PostMapping("/sys/dict/save")
	public CommonResponse<Object> save(@Validated @RequestBody DictDTO dict) throws BusinessException {
		log.debug("保存字典条目: {}", dict.toString());
		dictService.customSaveOrUpdate(dict.toPO());
		
		return CommonResponse.newSuccessResponse();
	}
	
	/**
	 * 删除字典信息
	 * @param id
	 * @return
	 */
	@ApiOperation("删除字典信息")
	@PreAuthorize("hasRole('DEVELOPER')")
	@DeleteMapping("/sys/dict/del/{id}")
	public CommonResponse<Object> delete(@PathVariable("id") Integer id) {
		log.debug("删除字典条目: id={}", id);
		if(dictService.getById(id) == null) {
			return CommonResponse.newResponse(ErrorCode.DICT_NOT_FOUND, "字典条目不存在");
		} else if(!dictService.removeById(id)) {
			return CommonResponse.newResponse(CommonErrorCode.FAILED, "字典条目删除失败");
		} else {
			return CommonResponse.newSuccessResponse();
		}
	}
	
	/**
	 * 分页查询字典信息
	 * @param label
	 * @param type
	 * @return
	 */
	@ApiOperation("分页查询字典信息")
	@GetMapping("/sys/dict/page")
	public CommonResponse<IPage<DictPO>> page(PageParam page, String label, String type) {
		log.debug("分页查询字典条目: page={}, label={}, type={}", page.toString(), label, type);
		QueryWrapper<DictPO> wrapper = new QueryWrapper<DictPO>();
		if(!StringUtils.isEmpty(label)) {
			wrapper.like("label", label);
		}
		if(!StringUtils.isEmpty(type)) {
			wrapper.eq("type", type);
		}
		IPage<DictPO> dicts = dictService.page(new Page<>(page.getPageNo(), page.getPageSize()), wrapper);
		return CommonResponse.newSuccessResponse(dicts);
	}
	
	/**
	 * 查询所有字典类型
	 * @return
	 */
	@ApiOperation("查询所有字典类型")
	@GetMapping("/sys/dict/types")
	public CommonResponse<List<Object>> queryTypes() {
		log.debug("查询所有字典类型");
		List<Object> types = dictService.listObjs(new QueryWrapper<DictPO>().select("type").groupBy("type").orderByAsc("type"));
		return CommonResponse.newSuccessResponse(types);
	}

}

