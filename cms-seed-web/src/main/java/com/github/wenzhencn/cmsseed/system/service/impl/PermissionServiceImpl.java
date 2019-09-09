package com.github.wenzhencn.cmsseed.system.service.impl;

import com.github.wenzhencn.cmsseed.system.entity.PermissionPO;
import com.github.wenzhencn.cmsseed.system.mapper.PermissionMapper;
import com.github.wenzhencn.cmsseed.system.service.IPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author wenzhen
 * @since 2019-09-07
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, PermissionPO> implements IPermissionService {

}
