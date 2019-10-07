package com.github.wenzhencn.cmsseed.dev.mapper;

import com.github.wenzhencn.cmsseed.dev.entity.UserPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author wenzhen
 * @since 2019-09-09
 */
@Mapper
public interface UserMapper extends BaseMapper<UserPO> {

    /**
     * 查询某个角色关联的用户数量
     * @param roleId
     * @return
     */
    int countUserByRole(@Param("roleId") Long roleId);

}
