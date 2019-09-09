package com.github.wenzhencn.cmsseed.system.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.wenzhencn.cmsseed.common.BusinessException;
import com.github.wenzhencn.cmsseed.common.CommonErrorCode;
import com.github.wenzhencn.cmsseed.common.ErrorCode;
import com.github.wenzhencn.cmsseed.system.entity.DictPO;
import com.github.wenzhencn.cmsseed.system.mapper.DictMapper;
import com.github.wenzhencn.cmsseed.system.service.IDictService;

/**
 * <p>
 * 字典表 服务实现类
 * </p>
 *
 * @author wenzhen
 * @since 2019-09-05
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, DictPO> implements IDictService {
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void customSaveOrUpdate(DictPO dict) throws BusinessException {
		if(dict.getId() == null) {
			// 新增
			DictPO exist = getOne(new QueryWrapper<DictPO>().eq("value", dict.getValue()).eq("type", dict.getType()));
			if(exist != null) {
				throw BusinessException.newInstance(ErrorCode.DICT_EXIST, String.format("字典[%s]已存在值[%d]", dict.getType(), dict.getValue()));
			}
			save(dict);
		} else {
			// 更新
			DictPO exist = getById(dict.getId());
			if(exist == null) {
				throw BusinessException.newInstance(ErrorCode.DICT_NOT_FOUND, String.format("字典条目[%d]不存在", dict.getId()));
			} else if(!exist.getType().equals(dict.getType())) {
				throw BusinessException.newInstance(CommonErrorCode.FAILED, "字典条目类型不能修改");
			}
			if(!exist.getValue().equals(dict.getValue())) {
				// 修改了value，需要判断value是否重复
				DictPO had = getById(new QueryWrapper<>().eq("value", dict.getValue()).and(w -> w.eq("type", dict.getType())));
				if(had != null) {
					throw BusinessException.newInstance(ErrorCode.DICT_EXIST, String.format("字典[%s]已存在值[%d]", dict.getType(), dict.getValue()));
				}
			}
			updateById(dict);
		}
	}

}
