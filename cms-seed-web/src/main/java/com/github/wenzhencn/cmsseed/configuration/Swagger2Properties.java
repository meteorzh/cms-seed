package com.github.wenzhencn.cmsseed.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Lyrids项目的Swagger2配置
 * @author wenzhen
 *
 */
@ConfigurationProperties(prefix = "swagger2")
public class Swagger2Properties {
	
	/**
	 * 标题
	 */
	private String title;
	
	/**
	 * 描述
	 */
	private String description;
	
	/**
	 * 版本
	 */
	private String version;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

}
