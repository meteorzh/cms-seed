package com.github.wenzhencn.cmsseed.utils.tree.node;

/**
 * 节点接口
 * @author JeenWeen
 *
 * @param <K> 唯一标识类型
 * @param <N> 具体运行时节点类型
 */
public interface Node<K, N extends Node<K, N>> {
	
	/**
	 * 获取唯一标识
	 * @return
	 */
	public K getKey();
	
	/**
	 * 设置唯一标识
	 * @param key
	 */
	public void setKey(K key);
	
	/**
	 * 为节点添加子节点
	 * @param node
	 */
	public void addChild(N node);

}
