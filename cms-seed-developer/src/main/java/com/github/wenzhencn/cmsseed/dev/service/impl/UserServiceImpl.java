package com.github.wenzhencn.cmsseed.dev.service.impl;

import com.github.wenzhencn.cmsseed.dev.entity.UserPO;
import com.github.wenzhencn.cmsseed.dev.mapper.UserMapper;
import com.github.wenzhencn.cmsseed.dev.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author wenzhen
 * @since 2019-09-09
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserPO> implements IUserService {

    @Override
    public int countUserByRole(Long roleId) {
        return baseMapper.countUserByRole(roleId);
    }
}
