package com.github.wenzhencn.cmsseed.utils.tree.factory;

import java.util.Collection;

import com.github.wenzhencn.cmsseed.utils.tree.Nodeable;
import com.github.wenzhencn.cmsseed.utils.tree.exception.LyridsTreeException;
import com.github.wenzhencn.cmsseed.utils.tree.node.DefaultMarkableNode;

/**
 * 默认标记树工厂<br />
 * 生成节点类型{@link DefaultMarkableNode}
 * @author JeenWeen
 *
 * @param <K>
 */
public class MarkableTreeFactory<K, D extends Nodeable<K>> extends AbstractMarkableTreeFactory<K, DefaultMarkableNode<K, D>, D> {

	private MarkableTreeFactory(Collection<D> datas, Collection<D> markData)
			throws LyridsTreeException {
		super(datas, markData);
	}

	@Override
	protected DefaultMarkableNode<K, D> genMarkableNode(D data, boolean mark) {
		return new DefaultMarkableNode<>(data, mark);
	}
	
	public static <K, D extends Nodeable<K>> TreeFactory<K, DefaultMarkableNode<K, D>> newInstance(Collection<D> datas, Collection<D> markData) throws LyridsTreeException {
		return new MarkableTreeFactory<>(datas, markData);
	}

}
