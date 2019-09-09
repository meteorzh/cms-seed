package com.github.wenzhencn.cmsseed.dev.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.wenzhencn.cmsseed.common.BusinessException;
import com.github.wenzhencn.cmsseed.common.CommonResponse;
import com.github.wenzhencn.cmsseed.common.PageParam;
import com.github.wenzhencn.cmsseed.dev.entity.RolePO;
import com.github.wenzhencn.cmsseed.dev.model.RoleDTO;
import com.github.wenzhencn.cmsseed.dev.service.IRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author wenzhen
 * @since 2019-09-09
 */
@Slf4j
@RestController
public class RoleController {

    @Autowired
    private IRoleService roleService;

    /**
     * 保存角色
     * @param role
     * @return
     */
    @PostMapping("/sys/role/save")
    public CommonResponse<Object> save(@Validated @RequestBody RoleDTO role) throws BusinessException {
        log.debug("保存角色: role={}", role.toString());
        if (role.getId() == null) {
            roleService.create(role.toPO());
        } else {
            roleService.update(role.toPO());
        }
        return CommonResponse.newSuccessResponse();
    }

    /**
     * 删除角色
     * @param id
     * @return
     */
    @DeleteMapping("/sys/role/del/{id}")
    public CommonResponse<Object> delete(@PathVariable("id") Long id) throws BusinessException {
        log.debug("删除角色: id={}", id);
        roleService.delete(id);
        return CommonResponse.newSuccessResponse();
    }

    /**
     * 分页查询角色
     * @param page
     * @return
     */
    @GetMapping("/sys/role/page")
    public CommonResponse<IPage<RolePO>> page(PageParam page, String key) {
        log.debug("分页查询角色: page={}, key={}", page.toString(), key);
        QueryWrapper<RolePO> wrapper = new QueryWrapper<RolePO>().eq("sys", true);
        if (!StringUtils.isEmpty(key)) {
            wrapper.nested(q -> q.like("code", key).or().like("name", key));
        }
        IPage<RolePO> roles = roleService.page(new Page<>(page.getPageNo(), page.getPageSize()), wrapper);
        return CommonResponse.newSuccessResponse(roles);
    }

}

