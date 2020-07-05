package com.github.wenzhencn.cmsseed.dev.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.wenzhencn.cmsseed.dev.entity.RegionPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 地区 Mapper 对象
 * @author wenzhen
 * @since Created in 2020/7/3 13:26
 */
@Mapper
public interface RegionMapper extends BaseMapper<RegionPO> {

    /**
     * 新增地区数据
     * @param region 地区数据
     * @return int
     */
//    int insert(RegionPO region);

    /**
     * 更新地区数据
     * @param region 地区
     * @return int
     */
    int update(RegionPO region);

    /**
     * 根据上级地区编码查询地区信息
     * @param pcode 上级地区编码
     * @return {@code List<RegionPO>}
     */
    List<RegionPO> selectByPcode(@Param("pcode") Integer pcode);
}
