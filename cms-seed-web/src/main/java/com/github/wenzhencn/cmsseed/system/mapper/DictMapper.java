package com.github.wenzhencn.cmsseed.system.mapper;

import com.github.wenzhencn.cmsseed.system.entity.DictPO;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 字典表 Mapper 接口
 * </p>
 *
 * @author wenzhen
 * @since 2019-09-05
 */
@Mapper
public interface DictMapper extends BaseMapper<DictPO> {

}
