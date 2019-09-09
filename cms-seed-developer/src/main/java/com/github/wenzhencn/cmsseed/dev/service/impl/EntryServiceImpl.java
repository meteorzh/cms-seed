package com.github.wenzhencn.cmsseed.dev.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.wenzhencn.cmsseed.dev.common.EntryTargetType;
import com.github.wenzhencn.cmsseed.dev.entity.EntryPO;
import com.github.wenzhencn.cmsseed.dev.mapper.EntryMapper;
import com.github.wenzhencn.cmsseed.dev.service.IEntryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 授权记录表 服务实现类
 * </p>
 *
 * @author wenzhen
 * @since 2019-09-09
 */
@Service
public class EntryServiceImpl extends ServiceImpl<EntryMapper, EntryPO> implements IEntryService {

    @Override
    public int countEntryByTarget(EntryTargetType targetType, Long targetId) {
        return count(new QueryWrapper<EntryPO>().eq("target_type", targetType).eq("target_id", targetId));
    }

    @Override
    public int countEntryByPermission(Long permissionId) {
        return count(new QueryWrapper<EntryPO>().eq("permission_id", permissionId));
    }

}
