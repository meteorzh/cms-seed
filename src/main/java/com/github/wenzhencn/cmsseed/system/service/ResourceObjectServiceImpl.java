package com.github.wenzhencn.cmsseed.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.wenzhencn.cmsseed.system.mapper.ResourceObjectMapper;
import com.github.wenzhencn.cmsseed.system.model.ResourceObject;

/**
 * 
 * @author zhen.wen
 *
 */
@Service
public class ResourceObjectServiceImpl implements ResourceObjectService {
	
	@Autowired
	private ResourceObjectMapper resourceObjectMapper;

	@Override
	public ResourceObject queryByRsrcAndObj(String rsrcCode, Object rsrcObjId) {
		return resourceObjectMapper.selectByRsrcAndObj(rsrcCode, rsrcObjId);
	}

}
