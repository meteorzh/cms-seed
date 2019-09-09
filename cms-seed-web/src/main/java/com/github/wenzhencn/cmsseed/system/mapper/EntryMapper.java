package com.github.wenzhencn.cmsseed.system.mapper;

import com.github.wenzhencn.cmsseed.system.entity.EntryPO;
import com.github.wenzhencn.cmsseed.system.security.EntryTargetType;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wenzhen
 * @since 2019-09-05
 */
@Mapper
public interface EntryMapper extends BaseMapper<EntryPO> {
	
	/**
	 * 清除某个目标被授予的权限
	 * @param targetType
	 * @param targetId
	 * @return
	 */
	int clearEntrysForTarget(@Param("targetType") EntryTargetType targetType, @Param("targetId") Long targetId);

}
