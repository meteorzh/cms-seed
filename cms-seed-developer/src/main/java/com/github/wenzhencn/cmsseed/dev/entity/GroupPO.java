package com.github.wenzhencn.cmsseed.dev.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户组表
 * </p>
 *
 * @author wenzhen
 * @since 2019-09-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_group")
public class GroupPO implements Serializable {


    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 组名
     */
    private String name;

    /**
     * 组说明
     */
    private String description;

    /**
     * 是否是系统保留用户组
     */
    private Boolean sys;

}
