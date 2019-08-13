package com.github.wenzhencn.cmsseed.system.service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.github.wenzhencn.cmsseed.system.entity.EntryDO;
import com.github.wenzhencn.cmsseed.system.entity.RoleDO;
import com.github.wenzhencn.cmsseed.system.mapper.EntryMapper;
import com.github.wenzhencn.cmsseed.system.model.Entry;
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
	public void add(EntryDO entry) {
		entryMapper.insert(entry);
	}
	
	@Override
	public void delete(Long entryId) {
		entryMapper.delete(entryId);
	}
	
	@Override
	public void deleteByResource(Integer resourceId, Long resourceObjectId) {
		entryMapper.deleteByResource(resourceId, resourceObjectId);
	}
	
	@Override
	public void deleteByTarget(EntryTargetType targetType, Long targetId) {
		entryMapper.deleteByTarget(targetType.getValue(), targetId);
	}

	@Override
	public List<Entry> queryByUserAll(Long userId, List<RoleDO> roles) {
		List<Entry> fu = queryByUser(userId);
		List<Entry> fg = queryByUserFromGroups(userId);
		
		Set<Long> roleIds = roles.stream().map(RoleDO::getId).collect(Collectors.toSet());
		List<Entry> frs = queryByRoles(roleIds);
		Map<Long, Entry> map = fu.stream().collect(Collectors.toMap(Entry::getId, Function.identity()));
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
	public List<Entry> queryByUser(Long userId) {
		return entryMapper.selectByTarget(EntryTargetType.USER, userId);
	}

	@Override
	public List<Entry> queryByUserFromGroups(Long userId) {
		return entryMapper.selectByUserFromGroups(userId);
	}

	@Override
	public List<Entry> queryByRoles(Set<Long> roleIds) {
		if(CollectionUtils.isEmpty(roleIds)) {
			return Collections.emptyList();
		}
		return entryMapper.selectByTargets(EntryTargetType.ROLE, roleIds);
	}
	
	@Override
	public List<EntryDO> queryList(EntryDO query) {
		return entryMapper.selectList(query);
	}
	
	@Override
	public EntryDO query(Long entryId) {
		return entryMapper.select(entryId);
	}
	
	@Override
	public int countByTarget(EntryTargetType targetType, Long targetId) {
		return entryMapper.countByTarget(targetType.getValue(), targetId);
	}

}
