package com.github.wenzhencn.cmsseed.dev.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author wenzhen
 * @since : Created in 2019/11/4 10:28
 */
@Data
public class TestDTO {

    private Integer id;

    private String name;

    private BigDecimal price;

    private LocalDateTime createTime;

}
