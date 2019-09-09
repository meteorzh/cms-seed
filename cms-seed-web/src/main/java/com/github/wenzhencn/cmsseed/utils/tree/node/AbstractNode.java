package com.github.wenzhencn.cmsseed.utils.tree.node;

import java.util.ArrayList;
import java.util.Collection;

import com.github.wenzhencn.cmsseed.utils.tree.Nodeable;

/**
 * 抽象节点（具有节点的基本要素）
 * @author JeenWeen
 *
 * @param <K> 唯一标识类型
 * @param <N> 运行时节点类型
 */
public abstract class AbstractNode<K, N extends AbstractNode<K, N>> implements Node<K, N> {
	
	/**
	 * 唯一标识
	 */
	private K key;
	
	/**
	 * 子节点
	 */
	private Collection<N> children;
	
	public AbstractNode() { }
	
	public AbstractNode(Nodeable<K> data) {
		this.key = data.getKey();
	}
	
	@Override
	public void addChild(N node) {
		if(this.children == null) {
			this.children = new ArrayList<>();
		}
		this.children.add(node);
	}
	
	@Override
	public K getKey() {
		return key;
	}
	
	@Override
	public void setKey(K key) {
		this.key = key;
	}

	public Collection<N> getChildren() {
		return children;
	}

	public void setChildren(Collection<N> children) {
		this.children = children;
	}
	
}
