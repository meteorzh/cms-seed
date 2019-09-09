package com.github.wenzhencn.cmsseed.dev.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.wenzhencn.cmsseed.common.BusinessException;
import com.github.wenzhencn.cmsseed.common.CommonErrorCode;
import com.github.wenzhencn.cmsseed.dev.common.EntryTargetType;
import com.github.wenzhencn.cmsseed.dev.entity.GroupPO;
import com.github.wenzhencn.cmsseed.dev.mapper.GroupMapper;
import com.github.wenzhencn.cmsseed.dev.service.IEntryService;
import com.github.wenzhencn.cmsseed.dev.service.IGroupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.wenzhencn.cmsseed.dev.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 用户组表 服务实现类
 * </p>
 *
 * @author wenzhen
 * @since 2019-09-09
 */
@Service
public class GroupServiceImpl extends ServiceImpl<GroupMapper, GroupPO> implements IGroupService {

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IEntryService entryService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void create(GroupPO group) throws BusinessException {
        GroupPO exist = getOne(new QueryWrapper<GroupPO>().eq("name", group.getName()));
        if(exist != null) {
            throw BusinessException.newInstance(CommonErrorCode.FAILED, String.format("用户组[%s]已存在", group.getName()));
        }
        group.setSys(true);
        save(group);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(GroupPO group) throws BusinessException {
        GroupPO exist = getById(group.getId());
        if(exist == null) {
            throw BusinessException.newInstance(CommonErrorCode.FAILED, String.format("用户组[%d]不存在", group.getId()));
        } else if (!exist.getSys()) {
            throw BusinessException.newInstance(CommonErrorCode.FAILED, "开发工具不能修改非系统保留的用户组");
        }
        if(!exist.getName().equals(group.getName())) {
            if(getOne(new QueryWrapper<GroupPO>().eq("name", group.getName())) != null) {
                throw BusinessException.newInstance(CommonErrorCode.FAILED, String.format("用户组[%s]已存在, 不能将组名修改为[%s]", group.getName(), group.getName()));
            }
        }
        group.setSys(exist.getSys());
        updateById(group);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(Long id) throws BusinessException {
        GroupPO exist = getById(id);
        if(exist == null) {
            throw BusinessException.newInstance(CommonErrorCode.FAILED, String.format("用户组[%d]不存在", id));
        } else if (!exist.getSys()) {
            throw BusinessException.newInstance(CommonErrorCode.FAILED, "开发工具不能删除非系统保留的用户组");
        }

        // 验证关联关系
        if (countUserByGroup(id) > 0) {
            throw BusinessException.newInstance(CommonErrorCode.FAILED, String.format("用户组[%d]里有用户，不能删除", id));
        } else if (roleService.countRoleByGroup(id) > 0) {
            throw BusinessException.newInstance(CommonErrorCode.FAILED, String.format("用户组[%d]和角色存在关联，不能删除", id));
        } else if (entryService.countEntryByTarget(EntryTargetType.GROUP, id) > 0) {
            throw BusinessException.newInstance(CommonErrorCode.FAILED, String.format("用户组[%d]和权限存在关联，不能删除", id));
        } else {
            removeById(id);
        }
    }

    @Override
    public int countUserByGroup(Long groupId) {
        return baseMapper.countUserByGroup(groupId);
    }

    @Override
    public int countGroupByRole(Long roleId) {
        return baseMapper.countGroupByRole(roleId);
    }

}
