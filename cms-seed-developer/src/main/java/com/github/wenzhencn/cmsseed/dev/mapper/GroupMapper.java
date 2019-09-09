package com.github.wenzhencn.cmsseed.dev.mapper;

import com.github.wenzhencn.cmsseed.dev.entity.GroupPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 用户组表 Mapper 接口
 * </p>
 *
 * @author wenzhen
 * @since 2019-09-09
 */
public interface GroupMapper extends BaseMapper<GroupPO> {

    /**
     * 查询用户组中的用户数量
     * @param groupId
     * @return
     */
    int countUserByGroup(@Param("groupId") Long groupId);

    /**
     * 查询某个角色关联的用户组的数量
     * @param roleId
     * @return
     */
    int countGroupByRole(@Param("roleId") Long roleId);

}
