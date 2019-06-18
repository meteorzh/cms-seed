package com.github.wenzhencn.cmsseed.system.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.github.wenzhencn.cmsseed.system.entity.ResourceObjectDO;
import com.github.wenzhencn.cmsseed.system.model.ResourceObject;

/**
 * 
 * @author zhen.wen
 *
 */
@Mapper
public interface ResourceObjectMapper extends BaseMapper<ResourceObjectDO> {

	/**
	 * 根据资源编码和实例ID查询实例信息
	 * @param rsrcCode
	 * @param rsrcObjId
	 * @return
	 */
	ResourceObject selectByRsrcAndObj(@Param("rsrcCode") String rsrcCode, @Param("rsrcObjId") Object rsrcObjId);

}