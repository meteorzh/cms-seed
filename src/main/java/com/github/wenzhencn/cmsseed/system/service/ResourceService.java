package com.github.wenzhencn.cmsseed.system.service;

import com.github.wenzhencn.cmsseed.system.entity.ResourceDO;

/**
 * 
 * @author zhen.wen
 *
 */
public interface ResourceService {
	
	/**
	 * 根据资源code查询资源
	 * @param code
	 * @return
	 */
	ResourceDO queryByCode(String code);

}
