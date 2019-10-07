package com.github.wenzhencn.cmsseed.dev.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.wenzhencn.cmsseed.common.BusinessException;
import com.github.wenzhencn.cmsseed.common.CommonResponse;
import com.github.wenzhencn.cmsseed.common.PageParam;
import com.github.wenzhencn.cmsseed.dev.entity.PermissionPO;
import com.github.wenzhencn.cmsseed.dev.model.PermissionDTO;
import com.github.wenzhencn.cmsseed.dev.service.IPermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 权限表 前端控制器
 * </p>
 *
 * @author wenzhen
 * @since 2019-09-09
 */
@Slf4j
@RestController
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;

    /**
     * 保存权限
     * @param pms
     * @return
     * @throws BusinessException
     */
    @PostMapping("/sys/pms/save")
    public CommonResponse<Object> save(@Validated @RequestBody PermissionDTO pms) throws BusinessException {
        log.debug("保存权限: permission={}", pms.toString());
        if (pms.getId() == null) {
            permissionService.create(pms.toPO());
        } else {
            permissionService.update(pms.toPO());
        }
        return CommonResponse.newSuccessResponse();
    }

    /**
     * 删除权限
     * @param id
     * @return
     * @throws BusinessException
     */
    @DeleteMapping("/sys/pms/del/{id}")
    public CommonResponse<Object> delete(@PathVariable("id") Long id) throws BusinessException {
        log.debug("删除权限: id={}", id);
        permissionService.delete(id);
        return CommonResponse.newSuccessResponse();
    }

    /**
     * 分页查询权限
     * @param page
     * @param key
     * @param category
     * @return
     */
    @GetMapping("/sys/pms/page")
    public CommonResponse<IPage<PermissionPO>> page(PageParam page, String key, Integer category) {
        log.debug("分页查询权限: page={}, key={}", page.toString(), key);
        QueryWrapper<PermissionPO> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(key)) {
            wrapper.nested(q -> q.like("code", key).or().like("label", key));
        }
        if (category != null) {
            wrapper.eq("category", category);
        }
        IPage<PermissionPO> permissions = permissionService.page(new Page<>(page.getPageNo(), page.getPageSize()), wrapper);
        return CommonResponse.newSuccessResponse(permissions);
    }

}

