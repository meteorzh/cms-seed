package com.github.wenzhencn.cmsseed.dev.common;

import lombok.Getter;

/**
 * 授权目标类型
 * @author zhen.wen
 *
 */
@Getter
public enum EntryTargetType {
	
	USER(1),
	
	GROUP(2),
	
	ROLE(3),
	
	;
	
	private byte value;
	
	private EntryTargetType(Integer value) {
		this.value = value.byteValue();
	}
	
	/**
	 * 根据value获取枚举类型
	 * @param value
	 * @return
	 */
	public static EntryTargetType fromValue(byte value) {
		if(value == 0) {
			return null;
		}
		for(EntryTargetType type : EntryTargetType.values()) {
			if(type.getValue() == value) {
				return type;
			}
		}
		return null;
	}

}
