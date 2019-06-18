package com.github.wenzhencn.cmsseed.system.security;

import java.io.Serializable;

import org.apache.commons.lang3.NotImplementedException;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.util.Assert;

import com.github.wenzhencn.cmsseed.system.model.ResourceObject;
import com.github.wenzhencn.cmsseed.system.service.ResourceObjectService;

import lombok.extern.slf4j.Slf4j;

/**
 * 自定义Entry权限Evaluator
 * @author zhen.wen
 *
 */
@Slf4j
public class EntryPermissionEvaluator implements PermissionEvaluator {
	
	private ResourceObjectService resourceObjectService;

	@Override
	public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
		throw new NotImplementedException("don't use this in this version");
//		Assert.isInstanceOf(EntryUserDetails.class, authentication, "EntryPermissionEvaluator need EntryUserDetails type authentication token");
//		if(targetDomainObject == null) {
//			return false;
//		}
//		return false;
	}

	@Override
	public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType,
			Object permission) {
		Assert.isInstanceOf(EntryUserDetails.class, authentication, "EntryPermissionEvaluator need EntryUserDetails type authentication token");
		Assert.hasLength(targetType, "Resource type is necessary");
		
		return false;
	}
	
	private boolean checkPermission(Authentication authentication, String srcType, Serializable srcId, Object permission) {
		if(srcId != null) {
			ResourceObject rsrcObj = resourceObjectService.queryByRsrcAndObj(srcType, srcId);
		}
		
		return false;
	}
	
	private Permission resolvePermission(Object permission) {
		if(permission instanceof Integer) {
			return Permission.fromValue(((Integer) permission).byteValue());
		} else if(permission instanceof String) {
			return Permission.fromCode((String) permission);
		}
		
		throw new IllegalArgumentException("Unsupported permission type [" + permission.getClass().getName() + "]");
	}

}
