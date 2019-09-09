package com.github.wenzhencn.cmsseed.system.service.impl;

import com.github.wenzhencn.cmsseed.system.entity.RolePO;
import com.github.wenzhencn.cmsseed.system.mapper.RoleMapper;
import com.github.wenzhencn.cmsseed.system.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wenzhen
 * @since 2019-09-05
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, RolePO> implements IRoleService {

	@Override
	public void add(RolePO role) {

	}

	@Override
	public void update(RolePO role) {

	}

	@Override
	public void clearRoleRelsForGroup(Long groupId) {
		baseMapper.clearRoleRelsForGroup(groupId);
	}

}
