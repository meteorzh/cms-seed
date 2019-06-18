package com.github.wenzhencn.cmsseed.utils.tree.factory;

import java.util.Collection;

import com.github.wenzhencn.cmsseed.utils.tree.Nodeable;
import com.github.wenzhencn.cmsseed.utils.tree.exception.LyridsTreeException;
import com.github.wenzhencn.cmsseed.utils.tree.node.DefaultDoublyNode;

/**
 * 默认双向树工厂<br />
 * 生成节点类型为{@link DefaultDoublyNode}
 * @author JeenWeen
 *
 * @param <K>
 */
public class DoublyTreeFactory<K, D extends Nodeable<K>> extends AbstractTreeFactory<K, DefaultDoublyNode<K, D>, D> {

	private DoublyTreeFactory(Collection<D> datas) throws LyridsTreeException {
		super(datas);
	}

	@Override
	protected DefaultDoublyNode<K, D> genNode(D v) {
		return new DefaultDoublyNode<>(v);
	}
	
	public static <K, D extends Nodeable<K>> TreeFactory<K, DefaultDoublyNode<K, D>> newInstance(Collection<D> source) throws LyridsTreeException {
		DoublyTreeFactory<K, D> dtf = new DoublyTreeFactory<>(source);
		return dtf;
	}

}
