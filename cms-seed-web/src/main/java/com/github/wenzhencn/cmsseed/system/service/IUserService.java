package com.github.wenzhencn.cmsseed.system.service;

import com.github.wenzhencn.cmsseed.system.entity.UserPO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Collection;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wenzhen
 * @since 2019-09-08
 */
public interface IUserService extends IService<UserPO> {

    /**
     * 查询用户ID列表中合法用户数量
     * @param userIds
     * @return
     */
    int countByUserIds(Collection<Long> userIds);

}
