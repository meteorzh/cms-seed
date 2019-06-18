package com.github.wenzhencn.cmsseed.utils.tree.node;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.wenzhencn.cmsseed.utils.tree.Nodeable;

/**
 * 默认双向节点
 * @author JeenWeen
 *
 * @param <K> 唯一标识类型
 */
public class DefaultDoublyNode<K, D extends Nodeable<K>> extends AbstractDataNode<K, DefaultDoublyNode<K, D>, D> implements DoublyNode<K, DefaultDoublyNode<K, D>> {
	
	/**
	 * 上级节点
	 */
	@JsonIgnore
	private DefaultDoublyNode<K, D> parent;
	
	public DefaultDoublyNode() { }
	
	public DefaultDoublyNode(D data) {
		super(data);
	}

	@Override
	public DefaultDoublyNode<K, D> getParent() {
		return this.parent;
	}

	@Override
	public void setParent(DefaultDoublyNode<K, D> parent) {
		this.parent = (DefaultDoublyNode<K, D>) parent;
	}

}
