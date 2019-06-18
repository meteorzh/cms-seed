package com.github.wenzhencn.cmsseed.utils.tree.node;

import com.github.wenzhencn.cmsseed.utils.tree.Nodeable;

/**
 * 可标记的节点
 * @author JeenWeen
 *
 * @param <K> 唯一标识类型
 */
public class DefaultMarkableNode<K, D extends Nodeable<K>> extends AbstractDataNode<K, DefaultMarkableNode<K, D>, D> implements MarkableNode<K, DefaultMarkableNode<K, D>> {
	
	/**
	 * 标记（默认未被标记）
	 */
	private boolean mark = false;
	
	public DefaultMarkableNode() { }
	
	public DefaultMarkableNode(D data, boolean mark) {
		super(data);
		this.mark = mark;
	}

	public boolean isMark() {
		return mark;
	}

	public void setMark(boolean mark) {
		this.mark = mark;
	}

}
