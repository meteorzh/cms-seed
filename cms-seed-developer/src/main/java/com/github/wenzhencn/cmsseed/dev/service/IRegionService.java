package com.github.wenzhencn.cmsseed.dev.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.wenzhencn.cmsseed.common.BusinessException;
import com.github.wenzhencn.cmsseed.dev.entity.RegionPO;

import java.util.List;

/**
 * <p>
 * 地区表 服务类
 * </p>
 *
 * @author wenzhen
 * @since 2019-09-09
 */
public interface IRegionService extends IService<RegionPO> {

    /**
     * 创建地区信息
     * @param region 地区信息
     */
    void create(RegionPO region) throws BusinessException;

    /**
     * 更新地区信息
     * @param region 地区信息
     */
    void update(RegionPO region);

    /**
     * 删除地区信息
     * @param id 数据ID
     */
    void delete(Long id) throws BusinessException;

    /**
     * 根据上级地区编码查询下级地区
     * @param pcode 上级地区编码
     * @return {@code List<RegionPO>}
     */
    List<RegionPO> queryByPcode(Integer pcode);
}
