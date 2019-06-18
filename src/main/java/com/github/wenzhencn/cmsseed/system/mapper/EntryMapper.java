package com.github.wenzhencn.cmsseed.system.mapper;

import java.util.Collection;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.github.wenzhencn.cmsseed.system.entity.EntryDO;
import com.github.wenzhencn.cmsseed.system.security.EntryTargetType;

/**
 * 
 * @author zhen.wen
 *
 */
@Mapper
public interface EntryMapper extends BaseMapper<EntryDO> {

	/**
	 * 查询target的权限
	 * @param userId
	 * @return
	 */
	List<EntryDO> selectByTarget(@Param("targetType") EntryTargetType targetType, @Param("targetId") Long targetId);
	
	/**
	 * 查询多个相同类型target权限
	 * @param targetType
	 * @param targetIds
	 * @return
	 */
	List<EntryDO> selectByTargets(@Param("targetType") EntryTargetType targetType, @Param("targetIds") Collection<Long> targetIds);
	
	/**
	 * 查询用户通过组获得的权限
	 * @param userId
	 * @return
	 */
	List<EntryDO> selectByUserFromGroups(@Param("userId") Long userId);
	
}