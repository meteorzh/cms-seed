package com.github.wenzhencn.cmsseed.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2 配置<br />
 * 开发和测试环境开启,生产环境不开启
 * <h5>激活条件</h5>
 * <ul>
 * 	<li>swagger2.enable=true（即默认不开启）</li>
 *  <li>项目依赖了Swagger2包，这里以包中的{@link EnableSwagger2}为标识</li>
 * </ul>
 * @author wenzhen
 *
 */
@Configuration
@ConditionalOnProperty(value="swagger2.enable", havingValue="true")
@EnableSwagger2
@EnableConfigurationProperties({ Swagger2Properties.class })
public class Swagger2Configuration {
	
	@Autowired
	private Swagger2Properties swagger2Properties;
	
	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("cn.rooomy.cdsoftware.toolplatform"))
				.paths(PathSelectors.any())
				.build();
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title(swagger2Properties.getTitle())
				.description(swagger2Properties.getDescription())
				.version(swagger2Properties.getVersion())
				.build();
	}

}
