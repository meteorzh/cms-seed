package com.github.wenzhencn.cmsseed.dev.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 字典表
 * </p>
 *
 * @author wenzhen
 * @since 2019-09-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_dict")
public class DictPO implements Serializable {


    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 字典条目名
     */
    private String label;

    /**
     * 字典条目值
     */
    private Integer value;

    /**
     * 字典条目分类
     */
    private String type;

    /**
     * 顺序
     */
    @TableField(value = "`order`")
    private Integer order;

}
