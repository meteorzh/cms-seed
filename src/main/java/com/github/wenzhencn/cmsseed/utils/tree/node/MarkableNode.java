package com.github.wenzhencn.cmsseed.utils.tree.node;

/**
 * 可标记节点接口（标记功能节点）
 * @author JeenWeen
 *
 * @param <K> 唯一标识类型
 * @param <N> 运行时节点类型
 */
public interface MarkableNode<K, N extends MarkableNode<K, N>> extends Node<K, N> {
	
	/**
	 * 是否被标记
	 * @return
	 */
	public boolean isMark();

	/**
	 * 设置是否被标记
	 * @param mark
	 */
	public void setMark(boolean mark);

}
