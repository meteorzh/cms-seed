package com.github.wenzhencn.cmsseed.dev.entity;

import lombok.Data;

/**
 * 地区PO对象
 * @author wenzhen
 * @since Created in 2020/7/3 13:27
 */
@Data
public class RegionPO {

    /** 主键 */
    private Integer id;

    /** 区域编码 */
    private Integer code;

    /** 区域名称 */
    private String name;

    /** 区域上级标识 */
    private Integer pcode;

    /** 地名简称 */
    private String sname;

    /** 区域等级 */
    private Integer level;

    /** 区域编码 */
    private String citycode;

    /** 邮政编码 */
    private String yzcode;

    /** 组合名称 */
    private String mername;

    /** 经度 */
    private Float lng;

    /** 纬度 */
    private Float lat;

    /** 拼音 */
    private String pinyin;
}
