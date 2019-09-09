package com.github.wenzhencn.cmsseed.dev.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.wenzhencn.cmsseed.common.BusinessException;
import com.github.wenzhencn.cmsseed.common.CommonErrorCode;
import com.github.wenzhencn.cmsseed.dev.common.EntryTargetType;
import com.github.wenzhencn.cmsseed.dev.entity.RolePO;
import com.github.wenzhencn.cmsseed.dev.mapper.RoleMapper;
import com.github.wenzhencn.cmsseed.dev.service.IEntryService;
import com.github.wenzhencn.cmsseed.dev.service.IGroupService;
import com.github.wenzhencn.cmsseed.dev.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.wenzhencn.cmsseed.dev.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author wenzhen
 * @since 2019-09-09
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, RolePO> implements IRoleService {

    @Autowired
    private IUserService userService;

    @Autowired
    private IGroupService groupService;

    @Autowired
    private IEntryService entryService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void create(RolePO role) throws BusinessException {
        RolePO exist = getOne(new QueryWrapper<RolePO>().eq("code", role.getCode()));
        if (exist != null) {
            throw BusinessException.newInstance(CommonErrorCode.FAILED, String.format("角色[%s]已存在", role.getCode()));
        }
        role.setSys(true);
        save(role);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(RolePO role) throws BusinessException {
        RolePO exist = getById(role.getId());
        if (exist == null) {
            throw BusinessException.newInstance(CommonErrorCode.FAILED, String.format("角色[%d]不存在", role.getId()));
        } else if (!exist.getSys()) {
            throw BusinessException.newInstance(CommonErrorCode.FAILED, "开发工具不能修改非系统保留角色");
        }
        if (!exist.getCode().equals(role.getCode())) {
            RolePO had = getOne(new QueryWrapper<RolePO>().eq("code", role.getCode()));
            if (had != null) {
                throw BusinessException.newInstance(CommonErrorCode.FAILED, String.format("角色[%s]已存在", role.getCode()));
            }
        }
        role.setSys(true);
        updateById(role);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(Long id) throws BusinessException {
        RolePO exist = getById(id);
        if (exist == null) {
            throw BusinessException.newInstance(CommonErrorCode.FAILED, String.format("角色[%d]不存在", id));
        } else if (!exist.getSys()) {
            throw BusinessException.newInstance(CommonErrorCode.FAILED, "开发工具不能删除非系统保留角色");
        }

        // 判断关联
        if (userService.countUserByRole(id) > 0) {
            throw BusinessException.newInstance(CommonErrorCode.FAILED, "角色和用户存在关联，不能删除");
        } else if (groupService.countGroupByRole(id) > 0) {
            throw BusinessException.newInstance(CommonErrorCode.FAILED, "角色和用户组存在关联，不能删除");
        } else if (entryService.countEntryByTarget(EntryTargetType.ROLE, id) > 0) {
            throw BusinessException.newInstance(CommonErrorCode.FAILED, "角色被授予了权限，不能删除");
        } else {
            removeById(id);
        }
    }

    @Override
    public int countRoleByGroup(Long groupId) {
        return baseMapper.countRoleByGroup(groupId);
    }

}
