package com.github.wenzhencn.cmsseed.dev.service;

import com.github.wenzhencn.cmsseed.dev.entity.UserPO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author wenzhen
 * @since 2019-09-09
 */
public interface IUserService extends IService<UserPO> {

    /**
     * 查询某个角色关联的用户数量
     * @param roleId
     * @return
     */
    int countUserByRole(Long roleId);

}
