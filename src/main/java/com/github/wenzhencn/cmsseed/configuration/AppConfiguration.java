package com.github.wenzhencn.cmsseed.configuration;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 应用配置
 * @author zhen.wen
 *
 */
@Configuration
//@EnableConfigurationProperties({ LdapProperties.class })
public class AppConfiguration {
	
//	@Bean
//	@ConditionalOnBean({ LdapProperties.class })
//	public LdapContextSource ldapContextSource(LdapProperties ldapProperties) {
//		LdapContextSource ctx = new LdapContextSource();
//		ctx.setUrls(ldapProperties.getUrls());
//		ctx.setBase(ldapProperties.getBase());
//		ctx.setUserDn(ldapProperties.getUsername());
//		ctx.setPassword(ldapProperties.getPassword());
//		
//		return ctx;
//	}
//	
//	@Bean
//	public LdapTemplate ldapTemplate(LdapContextSource lcs) {
//		return new LdapTemplate(lcs);
//	}
	
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
	
}
