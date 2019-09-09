package com.github.wenzhencn.cmsseed.dev.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.wenzhencn.cmsseed.common.BusinessException;
import com.github.wenzhencn.cmsseed.common.CommonResponse;
import com.github.wenzhencn.cmsseed.common.PageParam;
import com.github.wenzhencn.cmsseed.dev.entity.GroupPO;
import com.github.wenzhencn.cmsseed.dev.model.GroupDTO;
import com.github.wenzhencn.cmsseed.dev.service.IGroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户组表 前端控制器
 * </p>
 *
 * @author wenzhen
 * @since 2019-09-09
 */
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
    @PostMapping("/sys/group/save")
    public CommonResponse<Object> save(@Validated @RequestBody GroupDTO group) throws BusinessException {
        log.debug("保存用户组: group={}", group.toString());
        if(group.getId() == null) {
            groupService.create(group.toPO());
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
    @GetMapping("/sys/group/page")
    public CommonResponse<IPage<GroupPO>> page(PageParam page, String name) {
        log.debug("分页查询用户组: page={}", page.toString());
        QueryWrapper<GroupPO> wrapper = new QueryWrapper<GroupPO>().eq("sys", true);
        if(!StringUtils.isEmpty(name)) {
            wrapper.like("name", name);
        }
        IPage<GroupPO> groups = groupService.page(new Page<>(page.getPageNo(), page.getPageSize()), wrapper);
        return CommonResponse.newSuccessResponse(groups);
    }

}

