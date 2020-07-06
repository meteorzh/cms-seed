package com.github.wenzhencn.cmsseed.dev.common;

import lombok.Getter;

/**
 * 地区等级
 * @author wenzhen
 * @since Created in 2020/7/6 14:17
 */
public enum RegionLevel {

    /** 国 */
    COUNTRY(0),

    /** 省 */
    PROVINCE(1),

    /** 市 */
    CITY(2),

    /** 区 */
    COUNTY(3)

    ;

    @Getter
    private int level;

    RegionLevel(int level) {
        this.level = level;
    }
}
