package com.github.wenzhencn.cmsseed.system.security;

import org.springframework.util.StringUtils;

import lombok.Getter;

/**
 * 权限枚举
 * @author zhen.wen
 *
 */
@Getter
public enum Permission {
	
	ADMIN("A", 1),// 1
	
	READ("R", 2),// 2
	
	WRITE("W", 3),// 2
	
	CREATE("C", 4),// 2
	
	DELETE("D", 5),// 2
	
	ADMIN_OWN("AO", 21),// 2
	
	READ_OWN("RO", 22),// 2
	
	WRITE_OWN("WO", 23),// 2
	
	DELETE_OWN("DO", 24),// 2
	
	;
	
	private String code;
	
	private byte mask;
	
	private Permission(String code, Integer mask) {
		this.code = code;
		this.mask = mask.byteValue();
	}
	
	/**
	 * 根据value获取枚举类型
	 * @param mask
	 * @return
	 */
	public static Permission fromMask(byte mask) {
		if(mask == 0) {
			return null;
		}
		for(Permission e : Permission.values()) {
			if(e.getMask() == mask) {
				return e;
			}
		}
		return null;
	}
	
	/**
	 * 根据 code 获取枚举类型
	 * @param mask
	 * @return
	 */
	public static Permission fromCode(String code) {
		if(StringUtils.isEmpty(code)) {
			return null;
		}
		for(Permission e : Permission.values()) {
			if(code.equals(e.getCode())) {
				return e;
			}
		}
		return null;
	}
	
	/**
	 * 判断是否是Own类型权限（特殊的实例级权限）
	 * @param perm
	 * @return
	 */
	public static boolean isOwnPermission(Permission perm) {
		return perm.getMask() > 20;
	}

}
