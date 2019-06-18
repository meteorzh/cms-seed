package com.github.wenzhencn.cmsseed.utils.tree.node;

import com.github.wenzhencn.cmsseed.utils.tree.Nodeable;

/**
 * 简单节点，具有节点的基本要素并且能够携带数据
 * @author JeenWeen
 *
 * @param <K> 唯一标识类型
 * @param <D> 运行时数据类型
 */
public class SimpleNode<K, D extends Nodeable<K>> extends AbstractDataNode<K, SimpleNode<K, D>, D> {
	
	public SimpleNode() { }

	public SimpleNode(D value) {
		super(value);
	}

}
