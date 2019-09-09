package com.github.wenzhencn.cmsseed.dev.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.wenzhencn.cmsseed.common.BusinessException;
import com.github.wenzhencn.cmsseed.common.CommonErrorCode;
import com.github.wenzhencn.cmsseed.dev.entity.PermissionPO;
import com.github.wenzhencn.cmsseed.dev.mapper.PermissionMapper;
import com.github.wenzhencn.cmsseed.dev.service.IEntryService;
import com.github.wenzhencn.cmsseed.dev.service.IPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author wenzhen
 * @since 2019-09-09
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, PermissionPO> implements IPermissionService {

    @Autowired
    private IEntryService entryService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void create(PermissionPO permission) throws BusinessException {
        PermissionPO exist = getOne(new QueryWrapper<PermissionPO>().eq("code", permission.getCode()));
        if (exist != null) {
            throw BusinessException.newInstance(CommonErrorCode.FAILED, String.format("权限[%s]已存在", permission.getCode()));
        }
        save(permission);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(PermissionPO permission) throws BusinessException {
        PermissionPO exist = getById(permission.getId());
        if (exist == null) {
            throw BusinessException.newInstance(CommonErrorCode.FAILED, String.format("权限[%d]不存在，不能更新", permission.getId()));
        }
        if (!exist.getCode().equals(permission.getCode())) {
            // 修改了code
            PermissionPO had = getOne(new QueryWrapper<PermissionPO>().eq("code", permission.getCode()));
            if (had != null) {
                throw BusinessException.newInstance(CommonErrorCode.FAILED, String.format("CODE为[%s]的权限已存在，不能修改", permission.getCode()));
            }
        }
        updateById(permission);
    }

    @Override
    public void delete(Long id) throws BusinessException {
        PermissionPO exist = getById(id);
        if (exist == null) {
            throw BusinessException.newInstance(CommonErrorCode.FAILED, String.format("权限[%d]不存在", id));
        }

        // 判断关联
        if (entryService.countEntryByPermission(id) > 0) {
            throw BusinessException.newInstance(CommonErrorCode.FAILED, String.format("权限已被授予其他对象（用户/组/角色），不能删除", id));
        }
        removeById(id);
    }

}
