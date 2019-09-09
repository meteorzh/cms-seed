package com.github.wenzhencn.cmsseed.dev.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.wenzhencn.cmsseed.common.BusinessException;
import com.github.wenzhencn.cmsseed.common.CommonErrorCode;
import com.github.wenzhencn.cmsseed.dev.entity.DictPO;
import com.github.wenzhencn.cmsseed.dev.mapper.DictMapper;
import com.github.wenzhencn.cmsseed.dev.service.IDictService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <p>
 * 字典表 服务实现类
 * </p>
 *
 * @author wenzhen
 * @since 2019-09-09
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, DictPO> implements IDictService {

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void create(DictPO dict) throws BusinessException {
        DictPO exist = getOne(new QueryWrapper<DictPO>().eq("value", dict.getValue()).eq("type", dict.getType()));
        if(exist != null) {
            throw BusinessException.newInstance(CommonErrorCode.FAILED, String.format("字典[%s]已存在值[%d]", dict.getType(), dict.getValue()));
        }
        // 查找最大的Order
        Integer maxOrder = baseMapper.selectMaxOrderByType(dict.getType());
        dict.setOrder(maxOrder == null ? 1 : (maxOrder + 1));
        save(dict);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(DictPO dict) throws BusinessException {
        DictPO exist = getById(dict.getId());
        if(exist == null) {
            throw BusinessException.newInstance(CommonErrorCode.FAILED, String.format("字典条目[%d]不存在", dict.getId()));
        } else if(!exist.getType().equals(dict.getType())) {
            throw BusinessException.newInstance(CommonErrorCode.FAILED, "字典条目类型不能修改");
        }
        if(!exist.getValue().equals(dict.getValue())) {
            // 修改了value，需要判断value是否重复
            DictPO had = getById(new QueryWrapper<>().eq("value", dict.getValue()).and(w -> w.eq("type", dict.getType())));
            if(had != null) {
                throw BusinessException.newInstance(CommonErrorCode.FAILED, String.format("字典[%s]已存在值[%d]", dict.getType(), dict.getValue()));
            }
        }
        // 只能修改label和value两个字段
        update(new UpdateWrapper<DictPO>().set("label", dict.getLabel()).set("value", dict.getValue()).eq("id", dict.getId()));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(Integer id) throws BusinessException {
        DictPO exist = getById(id);
        if (exist == null) {
            throw BusinessException.newInstance(CommonErrorCode.FAILED, String.format("字典条目[%d]不存在", id));
        }
        // 大于当前被删除的dict order的条目依次减1
        List<DictPO> affects = list(new QueryWrapper<DictPO>().eq("type", exist.getType()).gt("order", exist.getOrder()));
        if (!CollectionUtils.isEmpty(affects)) {
            affects.forEach(affect -> affect.setOrder(affect.getOrder() - 1));
            updateBatchById(affects);
        }
        removeById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void reorder(Integer id, Integer order) throws BusinessException {
        DictPO exist = getById(id);
        if (exist == null) {
            throw BusinessException.newInstance(CommonErrorCode.FAILED, String.format("字典条目[%d]不存在", id));
        }
        if (exist.getOrder().equals(order)) {
            return;
        }
        if (order > baseMapper.selectMaxOrderByType(exist.getType())) {
            throw BusinessException.newInstance(CommonErrorCode.FAILED, String.format("新的顺序不能大于当前类型字典条目的最大顺序", id));
        }

        Integer greater = order, smaller = exist.getOrder();
        boolean icr = order < exist.getOrder(); // 顺序受影响的条目的顺序是增是减
        QueryWrapper<DictPO> affectQuery = new QueryWrapper<DictPO>().eq("type", exist.getType());
        if (icr) {
            greater = exist.getOrder();
            smaller = order;
            affectQuery.ge("order", smaller).lt("order", greater);
        } else {
            affectQuery.gt("order", smaller).le("order", greater);
        }
        List<DictPO> affects = list(affectQuery);
        int imt = icr ? 1 : -1;
        affects.forEach(affect -> affect.setOrder(affect.getOrder() + imt));

        exist.setOrder(order);
        affects.add(exist);
        updateBatchById(affects);
    }

}
