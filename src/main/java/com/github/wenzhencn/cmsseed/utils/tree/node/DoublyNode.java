package com.github.wenzhencn.cmsseed.utils.tree.node;

/**
 * 双向节点接口（拥有对上级节点的引用）
 * @author JeenWeen
 *
 * @param <K> 唯一标识类型
 * @param <N> 运行时节点类型
 */
public interface DoublyNode<K, N extends DoublyNode<K, N>> extends Node<K, N> {
	
	/**
	 * 获取上级节点
	 * @return
	 */
	public N getParent();
	
	/**
	 * 设置上级节点
	 * @param parent
	 */
	public void setParent(N parent);

}
