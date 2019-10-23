package com.github.wenzhencn.cmsseed.test.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author wenzhen
 * @since 2019-10-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("b_test")
public class TestPO implements Serializable {


    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String na;

    private String gt;

    private String wdUh;

    private String fwYrh;


}
