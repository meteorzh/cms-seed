package com.github.wenzhencn.cmsseed.utils.tree.node;

import com.github.wenzhencn.cmsseed.utils.tree.Nodeable;

/**
 * 抽象数据节点（可以携带数据的节点）
 * @author wenzhen
 *
 * @param <K> 唯一标识类型
 * @param <N> 运行时节点类型
 * @param <D> 运行时数据类型
 */
public abstract class AbstractDataNode<K, N extends AbstractDataNode<K, N, D>, D extends Nodeable<K>> extends AbstractNode<K, N> {
	
	private D data;
	
	public AbstractDataNode() { }
	
	public AbstractDataNode(D value) {
		super(value);
		this.data = value;
	}

	public D getData() {
		return data;
	}

	public void setData(D data) {
		this.data = data;
	}
	
}
