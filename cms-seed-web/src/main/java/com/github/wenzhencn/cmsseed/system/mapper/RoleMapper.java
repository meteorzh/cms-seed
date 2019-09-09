package com.github.wenzhencn.cmsseed.system.mapper;

import com.github.wenzhencn.cmsseed.system.entity.RolePO;

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
public interface RoleMapper extends BaseMapper<RolePO> {
	
	/**
	 * 清除任意角色和某个用户组的关系
	 * @param groupId
	 * @return
	 */
	int clearRoleRelsForGroup(@Param("groupId") Long groupId);

}
