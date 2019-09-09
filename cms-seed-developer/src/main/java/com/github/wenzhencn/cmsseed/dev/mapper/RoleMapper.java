package com.github.wenzhencn.cmsseed.dev.mapper;

import com.github.wenzhencn.cmsseed.dev.entity.RolePO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author wenzhen
 * @since 2019-09-09
 */
public interface RoleMapper extends BaseMapper<RolePO> {

    /**
     * 查询某个用户组关联的角色的数量
     * @param groupId
     * @return
     */
    int countRoleByGroup(@Param("groupId") Long groupId);

}
