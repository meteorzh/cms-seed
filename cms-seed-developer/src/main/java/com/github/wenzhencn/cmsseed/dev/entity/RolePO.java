package com.github.wenzhencn.cmsseed.dev.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author wenzhen
 * @since 2019-09-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_role")
public class RolePO implements Serializable {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 角色码
     */
    private String code;

    /**
     * 角色名
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 是否是系统保留角色
     */
    private Boolean sys;

}
