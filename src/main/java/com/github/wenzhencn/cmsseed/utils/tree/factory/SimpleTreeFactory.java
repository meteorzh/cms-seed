package com.github.wenzhencn.cmsseed.utils.tree.factory;

import java.util.Collection;

import com.github.wenzhencn.cmsseed.utils.tree.Nodeable;
import com.github.wenzhencn.cmsseed.utils.tree.exception.LyridsTreeException;
import com.github.wenzhencn.cmsseed.utils.tree.node.SimpleNode;

/**
 * 默认树工厂<br />
 * 生成节点类型为{@link SimpleNode}
 * @author wenzhen
 *
 * @param <K> 唯一标识类型
 * @param <D> 运行时数据类型
 */
public class SimpleTreeFactory<K, D extends Nodeable<K>> extends AbstractTreeFactory<K, SimpleNode<K, D>, D> {
	
	private SimpleTreeFactory(Collection<D> source) throws LyridsTreeException {
		super(source);
	}

	@Override
	protected SimpleNode<K, D> genNode(D v) {
		SimpleNode<K, D> node = new SimpleNode<>((D) v);
		return node;
	}
	
	public static <K, D extends Nodeable<K>> TreeFactory<K, SimpleNode<K, D>> newInstance(Collection<D> source) throws LyridsTreeException {
		SimpleTreeFactory<K, D> dtf = new SimpleTreeFactory<>(source);
		return dtf;
	}

}
