package com.github.wenzhencn.cmsseed.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.wenzhencn.cmsseed.system.entity.UserPO;
import com.github.wenzhencn.cmsseed.system.mapper.UserMapper;
import com.github.wenzhencn.cmsseed.system.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collection;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wenzhen
 * @since 2019-09-08
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserPO> implements IUserService {

    @Override
    public int countByUserIds(Collection<Long> userIds) {
        if (CollectionUtils.isEmpty(userIds)) {
            return 0;
        }
        return count(new QueryWrapper<UserPO>().in("id", userIds));
    }
}
