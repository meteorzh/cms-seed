package com.github.wenzhencn.cmsseed.dev.mapper;

import com.github.wenzhencn.cmsseed.dev.entity.DictPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 字典表 Mapper 接口
 * </p>
 *
 * @author wenzhen
 * @since 2019-09-09
 */
@Mapper
public interface DictMapper extends BaseMapper<DictPO> {

    /**
     * 查询某一类字典条目的最大顺序Order
     * @param type
     * @return
     */
    Integer selectMaxOrderByType(@Param("type") String type);

}
