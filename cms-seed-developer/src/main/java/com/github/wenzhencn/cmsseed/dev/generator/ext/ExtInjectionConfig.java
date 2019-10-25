package com.github.wenzhencn.cmsseed.dev.generator.ext;

import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author wenzhen
 * @Description
 * @Date: Created in 2019/10/25 17:21
 */
@Data
public abstract class ExtInjectionConfig {

    /**
     * 全局配置
     */
    private ExtConfigBuilder config;

    /**
     * 自定义返回配置 Map 对象
     */
    private Map<String, Object> map;

    /**
     * 自定义输出文件
     */
    private List<FileOutConfig> fileOutConfigList;

    /**
     * 自定义判断是否创建文件
     */
    private IExtFileCreate fileCreate;

    /**
     * 注入自定义 Map 对象
     */
    public abstract void initMap();

    /**
     * 模板待渲染 Object Map 预处理<br>
     * com.baomidou.mybatisplus.generator.engine.AbstractTemplateEngine
     * 方法： getObjectMap 结果处理
     */
    public Map<String, Object> prepareObjectMap(Map<String, Object> objectMap) {
        return objectMap;
    }

}
