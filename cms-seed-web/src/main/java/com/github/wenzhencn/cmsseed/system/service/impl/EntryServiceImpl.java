package com.github.wenzhencn.cmsseed.system.service.impl;

import com.github.wenzhencn.cmsseed.system.entity.EntryPO;
import com.github.wenzhencn.cmsseed.system.mapper.EntryMapper;
import com.github.wenzhencn.cmsseed.system.security.EntryTargetType;
import com.github.wenzhencn.cmsseed.system.service.IEntryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wenzhen
 * @since 2019-09-05
 */
@Service
public class EntryServiceImpl extends ServiceImpl<EntryMapper, EntryPO> implements IEntryService {

	@Override
	public void clearEntrysForTarget(EntryTargetType targetType, Long targetId) {
		baseMapper.clearEntrysForTarget(targetType, targetId);
	}

}
