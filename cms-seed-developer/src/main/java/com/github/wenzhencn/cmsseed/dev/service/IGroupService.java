package com.github.wenzhencn.cmsseed.dev.service;

import com.github.wenzhencn.cmsseed.common.BusinessException;
import com.github.wenzhencn.cmsseed.dev.entity.GroupPO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 用户组表 服务类
 * </p>
 *
 * @author wenzhen
 * @since 2019-09-09
 */
public interface IGroupService extends IService<GroupPO> {

    /**
     * 创建用户组
     * @param group
     * @throws BusinessException
     */
    void create(GroupPO group) throws BusinessException;

    /**
     * 修改用户组
     * @param group
     * @throws BusinessException
     */
    void update(GroupPO group) throws BusinessException;

    /**
     * 删除用户组
     * @param id
     * @throws BusinessException
     */
    void delete(Long id) throws BusinessException;

    /**
     * 查询用户组中的用户数量
     * @param groupId
     * @return
     */
    int countUserByGroup(Long groupId);

    /**
     * 查询某个角色关联的用户组的数量
     * @param roleId
     * @return
     */
    int countGroupByRole(Long roleId);

}
