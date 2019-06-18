package com.github.wenzhencn.cmsseed.system.service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.wenzhencn.cmsseed.system.entity.EntryDO;
import com.github.wenzhencn.cmsseed.system.entity.RoleDO;
import com.github.wenzhencn.cmsseed.system.mapper.EntryMapper;
import com.github.wenzhencn.cmsseed.system.security.EntryTargetType;

/**
 * 
 * @author zhen.wen
 *
 */
@Service
public class EntryServiceImpl implements EntryService {
	
	@Autowired
	private EntryMapper entryMapper;

	@Override
	public List<EntryDO> queryByUserAll(Long userId, List<RoleDO> roles) {
		List<EntryDO> fu = queryByUser(userId);
		List<EntryDO> fg = queryByUserFromGroups(userId);
		
		Set<Long> roleIds = roles.stream().map(RoleDO::getId).collect(Collectors.toSet());
		List<EntryDO> frs = queryByRoles(roleIds);
		Map<Long, EntryDO> map = fu.stream().collect(Collectors.toMap(EntryDO::getId, Function.identity()));
		fg.forEach(e -> {
			if(!map.containsKey(e.getId())) {
				map.put(e.getId(), e);
				fu.add(e);
			}
		});
		frs.forEach(e -> {
			if(!map.containsKey(e.getId())) {
				map.put(e.getId(), e);
				fu.add(e);
			}
		});
		return fu;
	}

	@Override
	public List<EntryDO> queryByUser(Long userId) {
		return entryMapper.selectByTarget(EntryTargetType.USER, userId);
	}

	@Override
	public List<EntryDO> queryByUserFromGroups(Long userId) {
		return entryMapper.selectByUserFromGroups(userId);
	}

	@Override
	public List<EntryDO> queryByRoles(Set<Long> roleIds) {
		return entryMapper.selectByTargets(EntryTargetType.ROLE, roleIds);
	}

}
