package com.github.wenzhencn.cmsseed.system.service;

import com.github.wenzhencn.cmsseed.system.entity.RolePO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wenzhen
 * @since 2019-09-05
 */
public interface IRoleService extends IService<RolePO> {

	/**
	 * 创建角色
	 * @param role
	 */
	void add(RolePO role);

	/**
	 * 修改角色
	 * @param role
	 */
	void update(RolePO role);
	
	/**
	 * 清除任意角色和某个用户组的关系
	 * @param groupId
	 */
	void clearRoleRelsForGroup(Long groupId);

}
