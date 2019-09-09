package com.github.wenzhencn.cmsseed.utils.tree.factory;

import java.util.Collection;
import java.util.Map;

import com.github.wenzhencn.cmsseed.utils.TPCollectionUtils;
import com.github.wenzhencn.cmsseed.utils.tree.Nodeable;
import com.github.wenzhencn.cmsseed.utils.tree.exception.LyridsTreeException;
import com.github.wenzhencn.cmsseed.utils.tree.node.MarkableNode;

/**
 * 抽象标记树工厂<br />
 * 生成节点类型为{@link MarkableNode}
 * @author JeenWeen
 *
 * @param <K>
 * @param <M>
 */
public abstract class AbstractMarkableTreeFactory<K, N extends MarkableNode<K, N>, D extends Nodeable<K>> extends AbstractTreeFactory<K, N, D> {
	
	private Map<K, D> markMap;

	protected AbstractMarkableTreeFactory(Collection<D> datas, Collection<D> markData) throws LyridsTreeException {
		super(datas);
		markMap = TPCollectionUtils.mapByField(markData, D::getKey);
	}
	
	@Override
	protected N genNode(D data) {
		K key = data.getKey();
		N node = genMarkableNode(data, markMap.containsKey(key));
		return node;
	}
	
	/**
	 * 生成可标记节点
	 * @param data
	 * @param mark
	 * @return
	 */
	protected abstract N genMarkableNode(D data, boolean mark);
	
}
