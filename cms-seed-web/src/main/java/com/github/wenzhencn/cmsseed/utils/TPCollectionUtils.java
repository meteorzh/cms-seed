package com.github.wenzhencn.cmsseed.utils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.FatalBeanException;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

/**
 * 
 * @author wenzhen
 *
 */
public final class TPCollectionUtils {
	
	private TPCollectionUtils() {}

	/**
	 * List 转Set
	 * @param datas
	 * @param <T>
	 * @return
	 */
	public static <T> Set<T> toSet(Collection<T> datas) {
		if(datas == null) {
			return null;
		}
		Set<T> res = new HashSet<>();
		res.addAll(datas);
		return res;
	}
	
	/**
	 * 获取某个对象集合的某个属性的集合
	 * @param datas
	 * @param fieldTaker
	 * @return
	 */
	public static <T, R> List<R> toFieldList(Collection<T> datas, Function<T, R> fieldTaker) {
		List<R> res = new ArrayList<>();
		if(!CollectionUtils.isEmpty(datas)) {
			for(T data : datas) {
				res.add(fieldTaker.apply(data));
			}
		}
		return res;
	}

	/**
	 * 验证集合数据中的某一个字段是否可以作为集合数据的唯一标识（是否在集合中唯一）
	 * @param datas
	 * @param keyTaker
	 * @param <T>
	 * @param <K>
	 * @return
	 */
	public static <T, K> boolean isFieldUnique(Collection<T> datas, Function<T, K> keyTaker) {
		if(CollectionUtils.isEmpty(datas)) {
			return true;
		}
		if(keyTaker == null) {
			return false;
		}
		Map<K, T> map = new HashMap<>();
		for(T data : datas) {
			K k = keyTaker.apply(data);
			Assert.notNull(k, "the key field must not be null!");
			if(map.containsKey(k)) {
				return false;
			}
			map.put(k, data);
		}
		return true;
	}
	
	/**
	 * 以对象某个属性为Key,将对象存为Map
	 * @param values
	 * @param keyTaker
	 * @return
	 */
	public static <K, V> Map<K, V> mapByField(Collection<V> values, Function<V, K> keyTaker) {
		if(values == null || keyTaker == null) {
			return null;
		}
		Map<K, V> map = new HashMap<>(10);
		for(V v : values) {
			K k = keyTaker.apply(v);
			Assert.notNull(k, "the key field must not be null!");
			map.put(k, v);
		}
		return map;
	}
	
	/**
	 * 将一个Bean,以其属性名为Key转为map(即将bean转为map对象)
	 * @param bean
	 * @return
	 */
	public static Map<String, Object> mapBean(Object bean) {
		Assert.notNull(bean, "bean must not be null");
		
		PropertyDescriptor[] pds = BeanUtils.getPropertyDescriptors(bean.getClass());
		Map<String, Object> map = new HashMap<>(10);
		for(PropertyDescriptor pd : pds) {
			String pname = pd.getName();
			Method readMethod = pd.getReadMethod();
			if(readMethod != null) {
				if(!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
					readMethod.setAccessible(true);
				}
				try {
					map.put(pname, readMethod.invoke(bean));
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					throw new FatalBeanException("不能读取属性:" + pname);
				}
			}
		}
		return map;
	}
	
	
	/**
	 * 通过一个生成方法,将一个对象列表转化为另一个对象列表
	 * @param ks        源对象集合
	 * @param convertor 转换方法
	 */
	public static <K, T> List<T> toList(Collection<K> ks, Function<K, T> convertor) {
		return toList(ks, convertor, null, null);
	}
	
	/**
	 * 
	 * @param ks        源对象集合
	 * @param convertor 转换方法
	 * @param before    转换前处理
	 * @return
	 */
	public static <K, T> List<T> toList(Collection<K> ks, Function<K, T> convertor, Consumer<K> before) {
		return toList(ks, convertor, before, null);
	}
	
	/**
	 * 
	 * @param ks        源对象集合
	 * @param convertor 转换方法
	 * @param after     转换后处理
	 * @return
	 */
	public static <K, T> List<T> toList(Collection<K> ks, Function<K, T> convertor, BiConsumer<K, T> after) {
		return toList(ks, convertor, null, after);
	}
	
	/**
	 * 
	 * @param ks        源对象集合
	 * @param convertor 转换方法
	 * @param before    转换前处理
	 * @param after     转换后处理
	 * @return
	 */
	public static <K, T> List<T> toList(Collection<K> ks, Function<K, T> convertor, Consumer<K> before, BiConsumer<K, T> after) {
		if(ks == null || convertor == null) {
			return null;
		}
		List<T> res = new ArrayList<>();
		if(ks.size() > 0) {
			for(K k : ks) {
				if(before != null) {
					before.accept(k);
				}
				T t = convertor.apply(k);
				if(after != null) {
					after.accept(k, t);
				}
				res.add(t);
			}
		}
		return res;
	}
	
	/**
	 * 遍历处理集合
	 * @param ks     集合列表
	 * @param handle 处理方法
	 */
	public static <K> void iterate(Collection<K> ks, Consumer<K> handle) {
		if(ks == null || handle == null) {
			return;
		}
		if(ks.size() > 0) {
			for(K k : ks) {
				handle.accept(k);
			}
		}
	}
	
	/**
	 * 根据Bean中的某一个字段去重
	 * @param ks
	 * @param fieldTaker
	 */
	public static <K, T> void removeRepeatByField(Collection<K> ks, Function<K, T> fieldTaker) {
		if(ks == null || fieldTaker == null) {
			return;
		}
		if(ks.size() > 0) {
			List<T> fieldList = new ArrayList<>();
			Iterator<K> ite = ks.iterator();
			while(ite.hasNext()) {
				K k = ite.next();
				T t = fieldTaker.apply(k);
				if(fieldList.contains(t)) {
					ite.remove();
				}
				else {
					fieldList.add(t);
				}
			}
		}
	}

    /**
     * 求交集<br />
     * 结果集中的元素全部来自bc
     * @param ac
     * @param bc
     * @param keyTaker
     * @param <O>
     * @param <K>
     * @return
     */
	public static <O, K> List<O> retainAll(Collection<O> ac, Collection<O> bc, Function<O, K> keyTaker) {
		if(ac == null || bc == null || keyTaker == null) {
			return null;
		}
		List<O> result = new ArrayList<>();
		if(!CollectionUtils.isEmpty(ac) && !CollectionUtils.isEmpty(bc)) {
			Map<K, O> map = new HashMap<>(10);
			for(O o : ac) {
				K k = keyTaker.apply(o);
				Assert.notNull(k, "对象的唯一标识不能为null");
				map.put(k, o);
			}
			for(O o : bc) {
				K k = keyTaker.apply(o);
				Assert.notNull(k, "对象的唯一标识不能为null");
				if(map.containsKey(k)) {
					result.add(o);
				}
			}
		}
		return result;
	}
	
	/**
	 * 求差集(ac - bc)<br />
	 * 结果集元素来自ac并且是ac有,bc没有的元素(bc有ac没有的元素会被忽略)
	 * @param ac       集合A
	 * @param bc       集合B
	 * @param keyTaker 获取标识的方法
	 * @return
	 */
	public static <O, K> List<O> subtract(Collection<O> ac, Collection<O> bc, Function<O, K> keyTaker) {
		if(ac == null || bc == null || keyTaker == null) {
			return null;
		}
		List<O> result = new ArrayList<>();
		if(ac.size() > 0) {
			if(bc.size() > 0) {
				Map<K, O> map = new HashMap<>(10);
				for(O o : bc) {
					K k = keyTaker.apply(o);
					Assert.notNull(k, "对象的唯一标识不能为null");
					map.put(k, o);
				}
				for(O o : ac) {
					K k = keyTaker.apply(o);
					Assert.notNull(k, "对象的唯一标识不能为null");
					if(!map.containsKey(k)) {
						result.add(o);
					}
				}
			} else {
				result.addAll(ac);
			}
		}
		return result;
	}
	
	/**
	 * 求并集<br />
	 * 交集部分以ac集合中的元素为准<br />
	 * 集合中重复的部分以先遍历到的为准
	 * @param ac
	 * @param bc
	 * @param keyTaker
	 * @return
	 */
	public static <O, K> List<O> union(Collection<O> ac, Collection<O> bc, Function<O, K> keyTaker) {
		if(ac == null || bc == null || keyTaker == null) {
			return null;
		}
		List<O> result = new ArrayList<>();
		Map<K, O> temp = new HashMap<>(10);
		if(ac.size() > 0) {
			for(O o : ac) {
				K k = keyTaker.apply(o);
				Assert.notNull(k, "对象的唯一标识不能为null");
				if(!temp.containsKey(k)) {
					result.add(o);
					temp.put(k, o);
				}
			}
		}
		if(bc.size() > 0) {
			for(O o : bc) {
				K k = keyTaker.apply(o);
				Assert.notNull(k, "对象的唯一标识不能为null");
				if(!temp.containsKey(k)) {
					result.add(o);
					temp.put(k, o);
				}
			}
		}
		return result;
	}
	
	/**
	 * 使用bc集合中的对象替换ac集合中的对象<br />
	 * 返回新的集合
	 * @param ac
	 * @param bc
	 * @param keyTaker
	 * @return
	 */
	public static <O, K> List<O> replace(Collection<O> ac, Collection<O> bc, Function<O, K> keyTaker) {
		if(ac == null || bc == null || keyTaker == null) {
			return Collections.emptyList();
		}
		Map<K, O> bMap = new HashMap<>(10);
		for(O b : bc) {
			K k = keyTaker.apply(b);
			Assert.notNull(k, "对象的唯一标识不能为null");
			bMap.put(k, b);
		}
		List<O> res = new ArrayList<>();
		for(O a : ac) {
			K k = keyTaker.apply(a);
			Assert.notNull(k, "对象的唯一标识不能为null");
			if(bMap.containsKey(k)) {
				a = bMap.get(k);
			}
			res.add(a);
		}
		return res;
	}
	
}
