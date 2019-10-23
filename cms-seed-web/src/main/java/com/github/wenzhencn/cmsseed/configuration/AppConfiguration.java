package com.github.wenzhencn.cmsseed.configuration;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * 应用配置
 * @author zhen.wen
 *
 */
@Configuration
public class AppConfiguration {
	
	@Bean
    public Validator validator() {
        ValidatorFactory factory = Validation.byProvider(HibernateValidator.class)
                .configure()
                .failFast(true)
                .buildValidatorFactory();
        return factory.getValidator();
    }
	
//	@Bean
//	public MessageInterpolator validationMessageInterpolator() {
//		AggregateResourceBundleLocator arbl = new AggregateResourceBundleLocator(strSources);
//		ResourceBundleMessageInterpolator rbmi = new ResourceBundleMessageInterpolator(arbl);
//	}
	
	@Bean
	public PaginationInterceptor paginationInterceptor() {
		PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
		return paginationInterceptor;
	}
	
}
