package com.github.wenzhencn.cmsseed.dev.generator.ext;

import com.baomidou.mybatisplus.generator.config.po.TableField;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 自定义 Velocity 模板引擎
 * @author wenzhen
 * @Description
 * @Date: Created in 2019/10/23 11:40
 */
public class ExtVelocityTemplateEngine extends VelocityTemplateEngine {

    @Override
    public Map<String, Object> getObjectMap(TableInfo tableInfo) {
        Map<String, Object> map = super.getObjectMap(tableInfo);
        List<TableField> keyFields = new ArrayList<>();
        List<TableField> mainFields = new ArrayList<>();
        tableInfo.getFields().forEach(f -> {
            if (f.isKeyFlag()) {
                keyFields.add(f);
            } else {
                mainFields.add(f);
            }
        });
        mainFields.addAll(tableInfo.getCommonFields());
        map.put("extKeyFields", keyFields);
        map.put("extMainFields", mainFields);
        return map;
    }
}
