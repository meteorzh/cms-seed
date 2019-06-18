package com.github.wenzhencn.cmsseed.utils.tree.factory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.github.wenzhencn.cmsseed.utils.TPCollectionUtils;
import com.github.wenzhencn.cmsseed.utils.tree.Nodeable;
import com.github.wenzhencn.cmsseed.utils.tree.Tree;
import com.github.wenzhencn.cmsseed.utils.tree.exception.LyridsTreeException;
import com.github.wenzhencn.cmsseed.utils.tree.node.DoublyNode;
import com.github.wenzhencn.cmsseed.utils.tree.node.Node;

/**
 * 树抽象工厂
 * @author wenzhen
 *
 * @param <K>
 * @param <V>
 */
public abstract class AbstractTreeFactory<K, N extends Node<K, N>, D extends Nodeable<K>> implements TreeFactory<K, N> {

	/**
	 * 生产树的数据源
	 */
	private Collection<D> datas;
	
	/**
	 * 生成的节点集合
	 */
	private Collection<N> nodes;
	
	public AbstractTreeFactory(Collection<D> datas) throws LyridsTreeException {
		//需要保证datas数据中的唯一标识是唯一的
		if(!TPCollectionUtils.isFieldUnique(datas, Nodeable::getKey)) {
			throw LyridsTreeException.newInstance("Nodeable data's key field must be unique in collection");
		}
		setSource(datas);
		setNodes(new ArrayList<N>());
	}
	
	@Override
	public List<Tree<K, N>> produce() {
		try {
			if(datas == null) {
				throw LyridsTreeException.newInstance("Necessary parameter must not be null");
			}
			sourceToNodes();
			return genTrees();
		} catch(LyridsTreeException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Collection<N> produceRoots() {
		Collection<N> roots = new ArrayList<>();
		List<Tree<K, N>> trees = produce();
		for(Tree<K, N> tree : trees) {
			roots.add(tree.getRoot());
		}
		return roots;
	}
	
	protected void sourceToNodes() throws LyridsTreeException {
		for(D v : datas) {
			if(v == null) {
				throw LyridsTreeException.newInstance("Source data must not be null or contains null");
			}
			if(v.getKey() == null) {
				throw LyridsTreeException.newInstance("Source data has null key");
			}
			N node = genNode(v);
			nodes.add(node);
		}
	}
	
	/**
	 * 生成树节点
	 * @param v
	 * @return
	 */
	protected abstract N genNode(D v);
	
	protected List<Tree<K, N>> genTrees() throws LyridsTreeException {
		Map<K, N> nodeMap = TPCollectionUtils.mapByField(nodes, Node::getKey);
		Collection<Nodeable<K>> tempDatas = new ArrayList<>(datas);
		assemble(tempDatas, nodeMap);
		return genTrees(tempDatas, nodeMap);
	}
	
	/**
	 * 组装<br />
	 * 遍历源数据Map，在其中找到源数据的上级数据，将它的node对象作为上级数据的node对象的子node<br />
	 * 递归执行，指导源数据的Map中没有更改为止，则余下的node全部为根节点
	 * @param source    源数据的一个集合
	 * @param nodeMap   节点Map
	 * @throws LyridsTreeException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void assemble(Collection<Nodeable<K>> tempDatas, Map<K, N> nodeMap) throws LyridsTreeException {
		int count = 0;
		
		Iterator<Nodeable<K>> ite = tempDatas.iterator();
		while(ite.hasNext()) {
			Nodeable<K> tempData = ite.next();
			K pKey = tempData.getParentKey();
			if(pKey == null || !nodeMap.containsKey(pKey)) {
				continue;
			}
			N node = nodeMap.get(tempData.getKey());
			N pNode = nodeMap.get(pKey);
			pNode.addChild(node);
			if(node instanceof DoublyNode) {
				((DoublyNode) node).setParent((DoublyNode) pNode);
			}
			ite.remove();
			++count;
		}
		if(count == 0) {
			return;
		}
		assemble(tempDatas, nodeMap);
	}
	
	protected List<Tree<K, N>> genTrees(Collection<Nodeable<K>> tempDatas, Map<K, N> nodeMap) {
		List<Tree<K, N>> res = new ArrayList<>();
		for(Nodeable<K> rootData : tempDatas) {
			res.add(new Tree<>(nodeMap.get(rootData.getKey())));
		}
		return res;
	}
	
	
	

	public Collection<D> getSource() {
		return datas;
	}

	public void setSource(Collection<D> source) {
		this.datas = source;
	}

	public Collection<N> getNodes() {
		return nodes;
	}

	protected void setNodes(Collection<N> nodes) {
		this.nodes = nodes;
	}

}
