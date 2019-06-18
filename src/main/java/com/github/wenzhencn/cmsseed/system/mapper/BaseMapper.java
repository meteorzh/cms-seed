package com.github.wenzhencn.cmsseed.system.mapper;

import java.util.Collection;
import java.util.List;

/**
 * MyBatis基础接口
 * @author wenzhen
 *
 * @param <T> 实体类型
 */
public interface BaseMapper<T> {
	
	/**
	 * 新增
	 * @param t
	 * @return
	 */
	int insert(T t);
	
	/**
	 * 批量新增
	 * @param ts
	 * @return
	 */
	int multiInsert(Collection<T> ts);
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	int delete(Object id);
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	int multiDelete(Collection<Object> ids);
	
	/**
	 * 更新
	 * @param t
	 * @return
	 */
	int update(T t);
	
	/**
	 * 批量更新
	 * @param ts
	 * @return
	 */
	int multiUpdate(Collection<T> ts);
	
	/**
	 * 选择更新
	 * @param t
	 * @return
	 */
	int updateSelective(T t);
	
	/**
	 * 批量选择更新
	 * @param ts
	 * @return
	 */
	int multiUpdateSelective(Collection<T> ts);
	
	/**
	 * 根据查询条件计数
	 * @param t
	 * @return
	 */
	int count(T t);
	
	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 */
	T select(Object id);
	
	/**
	 * 查询列表
	 * @param t
	 * @return
	 */
	List<T> selectList(T t);



}
