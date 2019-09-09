package com.github.wenzhencn.cmsseed.dev.service;

import com.github.wenzhencn.cmsseed.common.BusinessException;
import com.github.wenzhencn.cmsseed.dev.entity.DictPO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 字典表 服务类
 * </p>
 *
 * @author wenzhen
 * @since 2019-09-09
 */
public interface IDictService extends IService<DictPO> {

    /**
     * 创建字典条目
     * @param dict
     * @throws BusinessException
     */
    void create(DictPO dict) throws BusinessException;

    /**
     * 修改字典条目
     * @param dict
     * @throws BusinessException
     */
    void update(DictPO dict) throws BusinessException;

    /**
     * 删除字典条目
     * @param id
     */
    void delete(Integer id) throws BusinessException;

    /**
     * 重新设置某个字典条目的顺序
     * @param id
     * @param order
     */
    void reorder(Integer id, Integer order) throws BusinessException;

}
