package com.github.wenzhencn.cmsseed.system.service;

import com.github.wenzhencn.cmsseed.system.model.ResourceObject;

/**
 * 
 * @author zhen.wen
 *
 */
public interface ResourceObjectService {
	
	/**
	 * 根据资源编码和实例ID查询实例信息
	 * @param rsrcCode
	 * @param rsrcObjId
	 * @return
	 */
	ResourceObject queryByRsrcAndObj(String rsrcCode, Object rsrcObjId);

}
