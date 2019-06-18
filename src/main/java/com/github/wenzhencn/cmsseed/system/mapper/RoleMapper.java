package com.github.wenzhencn.cmsseed.system.mapper;

import java.util.Collection;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.github.wenzhencn.cmsseed.system.entity.RoleDO;

/**
 * 
 * @author zhen.wen
 *
 */
@Mapper
public interface RoleMapper extends BaseMapper<RoleDO> {

	/**
	 * 查询多个用户组的角色
	 * @param groupIds
	 * @return
	 */
	List<RoleDO> selectByGroups(@Param("groupIds") Collection<Integer> groupIds);
	
	/**
	 * 查询用户的角色
	 * @param userId
	 * @return
	 */
	List<RoleDO> selectByUser(@Param("userId") Long userId);
	
	/**
	 * 查询用户通过组获取的角色
	 * @param userId
	 * @return
	 */
	List<RoleDO> selectByUserFromGroups(@Param("userId") Long userId);

}