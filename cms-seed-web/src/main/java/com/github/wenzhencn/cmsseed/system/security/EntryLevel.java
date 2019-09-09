package com.github.wenzhencn.cmsseed.system.security;

import lombok.Getter;

/**
 * 授权（权限）级别
 * @author zhen.wen
 *
 */
@Getter
public enum EntryLevel {
	
	/**
	 * 资源级
	 */
	RESOURCE(1),
	
	/**
	 * 实例级
	 */
	INSTANCE(2),
	
	;
	
	private byte level;
	
	private EntryLevel(Integer level) {
		this.level = level.byteValue();
	}
	
	/**
	 * 根据value获取枚举类型
	 * @param level
	 * @return
	 */
	public static EntryLevel fromValue(byte level) {
		if(level == 0) {
			return null;
		}
		for(EntryLevel e : EntryLevel.values()) {
			if(e.getLevel() == level) {
				return e;
			}
		}
		return null;
	}

}
