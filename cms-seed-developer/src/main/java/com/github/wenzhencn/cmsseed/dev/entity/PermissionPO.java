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
 * 权限表
 * </p>
 *
 * @author wenzhen
 * @since 2019-09-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_permission")
public class PermissionPO implements Serializable {


    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 权限编码
     */
    private String code;

    /**
     * 权限名
     */
    private String label;

    /**
     * 权限说明
     */
    private String description;

    /**
     * 权限分组
     */
    private Integer category;

}
