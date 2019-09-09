package com.github.wenzhencn.cmsseed.dev.controller;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.github.wenzhencn.cmsseed.common.BusinessException;
import com.github.wenzhencn.cmsseed.common.CommonResponse;
import com.github.wenzhencn.cmsseed.dev.model.GenerationDTO;
import com.github.wenzhencn.cmsseed.dev.model.GroupDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 代码生成器 前端控制器
 * </p>
 *
 * @author wenzhen
 * @since 2019-09-09
 */
@Slf4j
@RestController
public class GeneratorController {

    @Autowired
    private DataSourceProperties dataSourceProperties;

    /**
     * 生成代码接口
     * @param generation
     * @return
     * @throws BusinessException
     */
    @PostMapping("/sys/codegen/gen")
    public CommonResponse<Object> generate(@Validated @RequestBody GenerationDTO generation) throws BusinessException {
        log.debug("保存用户组: generation={}", generation);

        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(generation.getProjectPath() + "/src/main/java");
        System.out.println(gc.getOutputDir());
        gc.setAuthor(generation.getAuthor());
        gc.setOpen(false);
        // gc.setSwagger2(true); 实体属性 Swagger2 注解
        if (!StringUtils.isEmpty(generation.getEntitySuffix())) {
            gc.setEntityName("%s" + generation.getEntitySuffix());
        }
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(dataSourceProperties.getUrl());
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername(dataSourceProperties.getUsername());
        dsc.setPassword(dataSourceProperties.getPassword());
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        if (!StringUtils.isEmpty(generation.getModuleName())) {
            pc.setModuleName(generation.getModuleName());
        }
        if (!StringUtils.isEmpty(generation.getOutputPackage())) {
            pc.setParent(generation.getOutputPackage());
        }
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
//      String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                String entityName = tableInfo.getEntityName();
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                String clearEntityName = StringUtils.isEmpty(generation.getEntitySuffix()) ? entityName :
                        entityName.substring(0, entityName.length() - generation.getEntitySuffix().length());

                return generation.getProjectPath() + "/src/main/resources/mapper/" + pc.getModuleName() + "/"
                        + clearEntityName + "Mapper" + StringPool.DOT_XML;
            }
        });
        /*
         * cfg.setFileCreate(new IFileCreate() {
         *
         * @Override public boolean isCreate(ConfigBuilder configBuilder, FileType
         * fileType, String filePath) { // 判断自定义文件夹是否需要创建 checkDir("调用默认方法创建的目录");
         * return false; } });
         */
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        // 指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        // templateConfig.setService();
        // templateConfig.setController();

        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//		strategy.setSuperEntityClass("com.github.wenzhencn.cmsseed.common.BaseEntity");
        strategy.setEntityLombokModel(true);
        strategy.setEntitySerialVersionUID(false);
        strategy.setRestControllerStyle(true);
        // 公共父类
//        strategy.setSuperControllerClass("com.baomidou.ant.common.BaseController");
        // 写于父类中的公共字段
//		strategy.setSuperEntityColumns("id");
        strategy.setInclude(generation.getTableName());
        strategy.setControllerMappingHyphenStyle(true);
        if (!StringUtils.isEmpty(generation.getTableNamePrefix())) {
            strategy.setTablePrefix(generation.getTableNamePrefix());
        }
        mpg.setStrategy(strategy);
//        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();

        return CommonResponse.newSuccessResponse();
    }

}
