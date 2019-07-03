package com.github.wenzhencn.cmsseed.system.security;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

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
		Assert.isInstanceOf(EntryUserDetails.class, authentication, "EntryPermissionEvaluator need EntryUserDetails type authentication token");
		Assert.isInstanceOf(String.class, targetDomainObject, "targetDomainObject must be a string in this PermissionEvaluator");
		
		return checkPermission((EntryUserDetails) authentication, (String) targetDomainObject, null, permission);
	}

	@Override
	public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType,
			Object permission) {
		Assert.isInstanceOf(EntryUserDetails.class, authentication, "EntryPermissionEvaluator need EntryUserDetails type authentication token");
		Assert.hasLength(targetType, "Resource type is necessary");
		
		return checkPermission((EntryUserDetails) authentication, targetType, targetId, permission);
	}
	
	private boolean checkPermission(EntryUserDetails user, String srcType, Serializable srcId, Object permission) {
		boolean checkRsrc = false, isOwn = false;
		ResourceObject rsrcObj = null;
		if(srcId != null) {
			rsrcObj = resourceObjectService.queryByRsrcAndObj(srcType, srcId);
			if(rsrcObj == null) {
				log.warn("Didn't find resource type={}, id={}", srcType, srcId);
				return false;
			}
			checkRsrc = true;
			isOwn = rsrcObj.getOwnerId().equals(user.getUserId());
		}
		Set<Permission> checks = resolveCheckingPermission(resolvePermission(permission), checkRsrc, isOwn);
		Set<String> checkStrs = resolveToPermissionStrings(srcType, checks, checkRsrc, checkRsrc ? rsrcObj.getResourceObjectId() : null);
		
		return CollectionUtils.containsAny(user.getEntrys(), checkStrs);
	}
	
	private Set<String> resolveToPermissionStrings(String srcType, Set<Permission> perms, boolean checkRsrc, Long srcId) {
		return perms.stream().map(perm -> {
			StringBuilder strb = new StringBuilder();
			strb.append(srcType).append(":").append(perm.getCode());
			if(perm.getMask() > 1 && perm.getMask() < 10) {
				if(checkRsrc) {
					strb.append(":").append(srcId);
				}
			}
			return strb.toString();
		}).collect(Collectors.toSet());
	}
	
	/**
	 * 分析出需要检测的权限
	 * @param cur
	 * @param checkRsrc
	 * @param isOwn
	 * @return
	 */
	private Set<Permission> resolveCheckingPermission(Permission cur, boolean checkRsrc, boolean isOwn) {
		Set<Permission> perms = new HashSet<>();
		perms.add(Permission.ADMIN);
		if(cur.getMask() < 10) {
			perms.add(cur);
		}
		
		// 如果检查实例
		if(checkRsrc && isOwn) {
			perms.add(Permission.ADMIN_OWN);
			if(cur.getMask() > 21) {
				perms.add(resolveByOwnType(cur));
				perms.add(cur);
			}
		}
		return perms;
	}
	
	/**
	 * 生成权限对象
	 * @param permission
	 * @return
	 */
	private Permission resolvePermission(Object permission) {
		if(permission instanceof Integer) {
			return Permission.fromMask(((Integer) permission).byteValue());
		} else if(permission instanceof String) {
			return Permission.fromCode((String) permission);
		}
		
		throw new IllegalArgumentException("Unsupported permission type [" + permission.getClass().getName() + "]");
	}
	
	/**
	 * 将 Own 类型的权限映射为资源级类型
	 * @param perm
	 * @return
	 */
	private Permission resolveByOwnType(Permission perm) {
		switch (perm) {
		case READ_OWN:
			return Permission.READ;
		case WRITE_OWN:
			return Permission.WRITE;
		case DELETE_OWN:
			return Permission.DELETE;
		default:
			throw new IllegalArgumentException("Cannot convert to own permission [" + perm.getClass().getCanonicalName() + "]");
		}
	}

}
