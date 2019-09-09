package com.github.wenzhencn.cmsseed.dev.service;

import com.github.wenzhencn.cmsseed.common.BusinessException;
import com.github.wenzhencn.cmsseed.dev.entity.PermissionPO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.wenzhencn.cmsseed.dev.entity.RolePO;

/**
 * <p>
 * 权限表 服务类
 * </p>
 *
 * @author wenzhen
 * @since 2019-09-09
 */
public interface IPermissionService extends IService<PermissionPO> {

    /**
     * 创建权限
     * @param permission
     */
    void create(PermissionPO permission) throws BusinessException;

    /**
     * 修改权限
     * @param permission
     */
    void update(PermissionPO permission) throws BusinessException;

    /**
     * 删除权限
     * @param id
     */
    void delete(Long id) throws BusinessException;

}
