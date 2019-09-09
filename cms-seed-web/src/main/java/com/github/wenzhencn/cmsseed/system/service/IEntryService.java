package com.github.wenzhencn.cmsseed.system.service;

import com.github.wenzhencn.cmsseed.system.entity.EntryPO;
import com.github.wenzhencn.cmsseed.system.security.EntryTargetType;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wenzhen
 * @since 2019-09-05
 */
public interface IEntryService extends IService<EntryPO> {
	
	/**
	 * 清除某个目标被授予的权限
	 * @param targetType
	 * @param targetId
	 */
	void clearEntrysForTarget(EntryTargetType targetType, Long targetId);

}
