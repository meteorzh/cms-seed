package com.github.wenzhencn.cmsseed.utils.tree;

import com.github.wenzhencn.cmsseed.utils.tree.node.Node;

/**
 * 树
 * @author wenzhen
 *
 * @param <K>
 * @param <N>
 */
public class Tree<K, N extends Node<K, N>> {
	
	/**
	 * 根节点
	 */
	private N root;
	
	public Tree() {}
	
	public Tree(N root) {
		this.root = root;
	}

	public N getRoot() {
		return root;
	}

	public void setRoot(N root) {
		this.root = root;
	}

}
