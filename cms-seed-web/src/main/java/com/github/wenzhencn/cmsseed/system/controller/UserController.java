package com.github.wenzhencn.cmsseed.system.controller;


import com.github.wenzhencn.cmsseed.common.BusinessException;
import com.github.wenzhencn.cmsseed.common.CommonResponse;
import com.github.wenzhencn.cmsseed.system.service.IGroupService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 *  用户服务前端控制器
 * </p>
 *
 * @author wenzhen
 * @since 2019-09-08
 */
@Slf4j
@RestController
public class UserController {

    @Autowired
    private IGroupService groupService;

    /**
     * 将用户加入到多个用户组中
     * @param userId
     * @param groupIds
     * @return
     * @throws BusinessException
     */
    @PostMapping("/sys/user/addtogroups")
    public CommonResponse<Object> addToGroups(@RequestParam("userId") @NotNull Long userId, @RequestParam("groupIds") @NotEmpty List<Long> groupIds) throws BusinessException {
        log.debug("向用户组添加多个用户: groupId={}, users={}");
        groupService.addUserToGroups(groupIds, userId);
        return CommonResponse.newSuccessResponse();
    }

}

