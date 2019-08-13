package com.github.wenzhencn.cmsseed.system.mapper;

import java.util.Collection;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.github.wenzhencn.cmsseed.system.entity.EntryDO;
import com.github.wenzhencn.cmsseed.system.model.Entry;
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
	List<Entry> selectByTarget(@Param("targetType") EntryTargetType targetType, @Param("targetId") Long targetId);
	
	/**
	 * 查询多个相同类型target权限
	 * @param targetType
	 * @param targetIds
	 * @return
	 */
	List<Entry> selectByTargets(@Param("targetType") EntryTargetType targetType, @Param("targetIds") Collection<Long> targetIds);
	
	/**
	 * 查询用户通过组获得的权限
	 * @param userId
	 * @return
	 */
	List<Entry> selectByUserFromGroups(@Param("userId") Long userId);
	
	/**
	 * 删除某个资源的权限配置
	 * @param resourceId
	 * @param resourceObjectId
	 * @return
	 */
	int deleteByResource(@Param("resourceId") Integer resourceId, @Param("resourceObjectId") Long resourceObjectId);
	
	/**
	 * 删除某个目标的权限
	 * @param targetType
	 * @param targetId
	 * @return
	 */
	int deleteByTarget(@Param("targetType") byte targetType, @Param("targetId") Long targetId);
	
	/**
	 * 查询权限目标具有的权限数量
	 * @param targetType
	 * @param targetId
	 * @return
	 */
	int countByTarget(@Param("targetType") byte targetType, @Param("targetId") Long targetId);
	
}