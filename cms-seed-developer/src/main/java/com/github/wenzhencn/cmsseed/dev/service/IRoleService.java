package com.github.wenzhencn.cmsseed.dev.service;

import com.github.wenzhencn.cmsseed.common.BusinessException;
import com.github.wenzhencn.cmsseed.dev.entity.RolePO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author wenzhen
 * @since 2019-09-09
 */
public interface IRoleService extends IService<RolePO> {

    /**
     * 创建角色
     * @param role
     */
    void create(RolePO role) throws BusinessException;

    /**
     * 修改角色
     * @param role
     */
    void update(RolePO role) throws BusinessException;

    /**
     * 删除角色
     * @param id
     */
    void delete(Long id) throws BusinessException;

    /**
     * 查询某个用户组关联的角色的数量
     * @param groupId
     * @return
     */
    int countRoleByGroup(Long groupId);

}
