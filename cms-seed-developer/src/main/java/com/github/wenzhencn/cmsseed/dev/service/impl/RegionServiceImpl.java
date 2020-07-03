package com.github.wenzhencn.cmsseed.dev.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.wenzhencn.cmsseed.common.BusinessException;
import com.github.wenzhencn.cmsseed.dev.entity.RegionPO;
import com.github.wenzhencn.cmsseed.dev.mapper.RegionMapper;
import com.github.wenzhencn.cmsseed.dev.service.IRegionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 地区表 服务实现类
 * </p>
 *
 * @author wenzhen
 * @since 2019-09-09
 */
@Service
public class RegionServiceImpl extends ServiceImpl<RegionMapper, RegionPO> implements IRegionService {

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void create(RegionPO region) throws BusinessException {
        this.baseMapper.insert(region);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(RegionPO region) {
        this.baseMapper.update(region);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(Long id) throws BusinessException {
        removeById(id);
    }

    @Override
    public List<RegionPO> queryByPcode(Integer pcode) {
        return this.baseMapper.selectByPcode(pcode);
    }
}
