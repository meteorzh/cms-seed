package com.github.wenzhencn.cmsseed.system.service;

import com.github.wenzhencn.cmsseed.common.BusinessException;
import com.github.wenzhencn.cmsseed.system.entity.DictPO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 字典表 服务类
 * </p>
 *
 * @author wenzhen
 * @since 2019-09-05
 */
public interface IDictService extends IService<DictPO> {
	
	/**
	 * 自定义保存/更新字典条目逻辑
	 * @param dict
	 * @throws BusinessException
	 */
	void customSaveOrUpdate(DictPO dict) throws BusinessException;
	
}
