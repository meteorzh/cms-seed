package com.github.wenzhencn.cmsseed.system.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.github.wenzhencn.cmsseed.system.entity.ResourceDO;

/**
 * 
 * @author zhen.wen
 *
 */
@Mapper
public interface ResourceMapper extends BaseMapper<ResourceDO> {

	/**
	 * 根据编码查询资源
	 * @param code
	 * @return
	 */
	ResourceDO selectByCode(@Param("code") String code);

}