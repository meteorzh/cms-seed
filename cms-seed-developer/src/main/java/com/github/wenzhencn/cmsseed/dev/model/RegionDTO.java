package com.github.wenzhencn.cmsseed.dev.model;

import com.github.wenzhencn.cmsseed.dev.entity.RegionPO;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 地区PO对象
 * @author wenzhen
 * @since Created in 2020/7/3 13:27
 */
@Data
public class RegionDTO {

    /** 主键 */
    private Integer id;

    /** 区域编码 */
    @NotNull
    private Integer code;

    /** 区域名称 */
    @NotEmpty
    private String name;

    /** 区域上级标识 */
    @NotNull
    private Integer pcode;

    /** 地名简称 */
    @NotEmpty
    private String sname;

    /** 区域等级 */
    @NotNull
    private Integer level;

    /** 区域编码 */
    private String citycode;

    /** 邮政编码 */
    private String yzcode;

    /** 组合名称 */
    @NotEmpty
    private String mername;

    /** 经度 */
    private Float lng;

    /** 纬度 */
    private Float lat;

    /** 拼音 */
    private String pinyin;

    public RegionPO toPO() {
        RegionPO po = new RegionPO();
        po.setId(id);
        po.setCode(code);
        po.setName(name);
        po.setPcode(pcode);
        po.setSname(sname);
        po.setLevel(level);
        po.setCitycode(citycode);
        po.setYzcode(yzcode);
        po.setMername(mername);
        po.setLng(lng);
        po.setLat(lat);
        po.setPinyin(pinyin);
        return po;
    }
}
