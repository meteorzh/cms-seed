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
 * 授权记录表
 * </p>
 *
 * @author wenzhen
 * @since 2019-09-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_entry")
public class EntryPO implements Serializable {


    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 权限ID
     */
    private Long permissionId;

    /**
     * 授权目标类型
     */
    private Integer targetType;

    /**
     * 授权目标ID
     */
    private Long targetId;


}
