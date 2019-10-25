package com.github.wenzhencn.cmsseed.dev.generator.ext;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.List;

/**
 * 使用自定义的 ConfigBuilder
 * @author wenzhen
 * @Description
 * @Date: Created in 2019/10/25 16:58
 */
@Slf4j
public class ExtGenerator extends AutoGenerator {

    /**
     * 配置信息
     */
    protected ExtConfigBuilder config;

    protected AbstractExtTemplateEngine extTemplateEngine;

    /**
     * 注入配置
     */
    protected ExtInjectionConfig extInjectionConfig;

    @Override
    public void execute() {
        log.debug("==========================准备生成文件...==========================");
        // 初始化配置
        if (null == config) {
            config = new ExtConfigBuilder(getPackageInfo(), getDataSource(), getStrategy(), getTemplate(), getGlobalConfig());
            if (null != extInjectionConfig) {
                extInjectionConfig.setConfig(config);
            }
        }
        if (null == extTemplateEngine) {
            // 为了兼容之前逻辑，采用 Velocity 引擎 【 默认 】
            this.extTemplateEngine = new ExtVelocityTemplateEngine();
        }
        // 模板引擎初始化执行文件输出
        extTemplateEngine.init(this.pretreatmentConfigBuilder(config)).mkdirs().batchOutput().open();
        log.debug("==========================文件生成完成！！！==========================");
    }

    /**
     * 预处理配置
     *
     * @param config 总配置信息
     * @return 解析数据结果集
     */
    protected ExtConfigBuilder pretreatmentConfigBuilder(ExtConfigBuilder config) {
        /*
         * 注入自定义配置
         */
        if (null != extInjectionConfig) {
            extInjectionConfig.initMap();
            config.setExtInjectionConfig(extInjectionConfig);
        }
        /*
         * 表信息列表
         */
        List<TableInfo> tableList = this.getAllTableInfoList(config);
        for (TableInfo tableInfo : tableList) {
            /* ---------- 添加导入包 ---------- */
            if (config.getGlobalConfig().isActiveRecord()) {
                // 开启 ActiveRecord 模式
                tableInfo.setImportPackages(Model.class.getCanonicalName());
            }
            if (tableInfo.isConvert()) {
                // 表注解
                tableInfo.setImportPackages(TableName.class.getCanonicalName());
            }
            if (config.getStrategyConfig().getLogicDeleteFieldName() != null && tableInfo.isLogicDelete(config.getStrategyConfig().getLogicDeleteFieldName())) {
                // 逻辑删除注解
                tableInfo.setImportPackages(TableLogic.class.getCanonicalName());
            }
            if (StringUtils.isNotEmpty(config.getStrategyConfig().getVersionFieldName())) {
                // 乐观锁注解
                tableInfo.setImportPackages(Version.class.getCanonicalName());
            }
            boolean importSerializable = true;
            if (StringUtils.isNotEmpty(config.getSuperEntityClass())) {
                // 父实体
                tableInfo.setImportPackages(config.getSuperEntityClass());
                importSerializable = false;
            }
            if (config.getGlobalConfig().isActiveRecord()) {
                importSerializable = true;
            }
            if (importSerializable) {
                tableInfo.setImportPackages(Serializable.class.getCanonicalName());
            }
            // Boolean类型is前缀处理
            if (config.getStrategyConfig().isEntityBooleanColumnRemoveIsPrefix()) {
                tableInfo.getFields().stream().filter(field -> "boolean".equalsIgnoreCase(field.getPropertyType()))
                        .filter(field -> field.getPropertyName().startsWith("is"))
                        .forEach(field -> {
                            field.setConvert(true);
                            field.setPropertyName(StringUtils.removePrefixAfterPrefixToLower(field.getPropertyName(), 2));
                        });
            }
        }
        return config.setTableInfoList(tableList);
    }

    /**
     * 开放表信息、预留子类重写
     *
     * @param config 配置信息
     * @return ignore
     */
    protected List<TableInfo> getAllTableInfoList(ExtConfigBuilder config) {
        return config.getTableInfoList();
    }

    public AbstractExtTemplateEngine getExtTemplateEngine() {
        return extTemplateEngine;
    }

    public void setExtTemplateEngine(AbstractExtTemplateEngine extTemplateEngine) {
        this.extTemplateEngine = extTemplateEngine;
    }

    public ExtInjectionConfig getExtCfg() {
        return extInjectionConfig;
    }

    public ExtGenerator setExtCfg(ExtInjectionConfig extInjectionConfig) {
        this.extInjectionConfig = extInjectionConfig;
        return this;
    }

}
