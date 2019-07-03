package com.github.wenzhencn.cmsseed.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.wenzhencn.cmsseed.system.entity.ResourceDO;
import com.github.wenzhencn.cmsseed.system.mapper.ResourceMapper;

/**
 * 
 * @author zhen.wen
 *
 */
@Service
public class ResourceServiceImpl implements ResourceService {
	
	@Autowired
	private ResourceMapper resourceMapper;

	@Override
	public ResourceDO queryByCode(String code) {
		return resourceMapper.selectByCode(code);
	}

}
