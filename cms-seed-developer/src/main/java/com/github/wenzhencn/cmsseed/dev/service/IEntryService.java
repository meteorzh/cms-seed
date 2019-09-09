package com.github.wenzhencn.cmsseed.dev.service;

import com.github.wenzhencn.cmsseed.dev.common.EntryTargetType;
import com.github.wenzhencn.cmsseed.dev.entity.EntryPO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 授权记录表 服务类
 * </p>
 *
 * @author wenzhen
 * @since 2019-09-09
 */
public interface IEntryService extends IService<EntryPO> {

    /**
     * 查询目标被授予的权限数量
     * @param targetType
     * @param targetId
     * @return
     */
    int countEntryByTarget(EntryTargetType targetType, Long targetId);

    /**
     * 查询某个权限被授予目标的记录数量
     * @param permissionId
     * @return
     */
    int countEntryByPermission(Long permissionId);

}
