package com.github.wenzhencn.cmsseed.utils.tree.factory;

import java.util.Collection;

import com.github.wenzhencn.cmsseed.utils.tree.Tree;
import com.github.wenzhencn.cmsseed.utils.tree.node.Node;

/**
 * 树工厂接口
 * @author JeenWeen
 *
 * @param <K> 唯一标识类型
 * @param <N> 节点类型
 */
public interface TreeFactory<K, N extends Node<K, N>> {
	
	/**
	 * 生产树<br />
	 * 用于生成树的数据源中可能有多个数据可以作为根节点
	 * @return
	 */
	public Collection<Tree<K, N>> produce();
	
	/**
	 * 生成所有根节点
	 * @return
	 */
	public Collection<N> produceRoots();

}
