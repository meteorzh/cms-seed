package com.github.wenzhencn.cmsseed.system.service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.wenzhencn.cmsseed.system.entity.RoleDO;
import com.github.wenzhencn.cmsseed.system.mapper.RoleMapper;

/**
 * 
 * @author zhen.wen
 *
 */
@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleMapper roleMapper;

	@Override
	public List<RoleDO> queryByUserAll(Long userId) {
		List<RoleDO> fu = queryByUser(userId);
		List<RoleDO> fg = queryByUserFromGroups(userId);
		
		Map<Long, RoleDO> map = fu.stream().collect(Collectors.toMap(RoleDO::getId, Function.identity()));
		fg.forEach(role -> {
			if(!map.containsKey(role.getId())) {
				map.put(role.getId(), role);
				fu.add(role);
			}
		});
		
		return fu;
	}

	@Override
	public List<RoleDO> queryByUser(Long userId) {
		return roleMapper.selectByUser(userId);
	}

	@Override
	public List<RoleDO> queryByUserFromGroups(Long userId) {
		return roleMapper.selectByUserFromGroups(userId);
	}

}
