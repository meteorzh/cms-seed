package com.github.wenzhencn.cmsseed.dev.model;

import com.github.wenzhencn.cmsseed.dev.entity.PermissionPO;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 代码生成参数
 * @author wenzh
 *
 */
@Data
public class GenerationDTO {

    /**
     * 项目绝对路径
     */
    @NotEmpty
	private String projectPath;

    /**
     * 代码作者
     */
	private String author;

    /**
     * Entity 文件后缀
     */
	private String entitySuffix;

    /**
     * 输出文件的包名
     */
	private String outputPackage;

    /**
     * 输出文件模块名
     */
	private String moduleName;

    /**
     * 需要生成代码的数据库表名，多个表使用英文逗号(",")分开
     */
    @NotEmpty
	private String tableName;

    /**
     * 数据库表名前缀
     */
	private String tableNamePrefix;

    /**
     * 使用的模板标识
     */
	private String template;

    /**
     * 数据源信息
     */
    @NotNull
	private DataSourceInfo dataSourceInfo;

    /**
     * 数据源配置
     * @author wenzhen
     * @Description
     * @Date: 2019-10-23 15:35:57
     */
	@Data
	public static class DataSourceInfo {

        /**
         * URL
         */
	    private String url;

        /**
         * 驱动类
         */
	    private String driver;

        /**
         * 用户名
         */
	    private String username;

        /**
         * 密码
         */
	    private String password;

        /**
         * PostgreSQL SchemeName
         */
	    private String schemeName;

    }

}
