package com.github.wenzhencn.cmsseed.utils.tree;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 可作为节点的数据
 * @author JeenWeen
 *
 * @param <K> 唯一标识的类型
 */
public interface Nodeable<K> {
	
	/**
	 * 获取节点唯一标识
	 * @return
	 */
	@JsonIgnore
	public K getKey();
	
	/**
	 * 获取上级唯一标识
	 * @return
	 */
	@JsonIgnore
	public K getParentKey();

}
