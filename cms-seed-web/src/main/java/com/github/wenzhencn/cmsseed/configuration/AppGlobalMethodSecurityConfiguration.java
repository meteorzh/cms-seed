//package com.github.wenzhencn.cmsseed.configuration;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
//import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
//
//import com.github.wenzhencn.cmsseed.system.security.EntryPermissionEvaluator;
//
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//@ConditionalOnBean(value = { ResourceObjectService.class })
//public class AppGlobalMethodSecurityConfiguration extends GlobalMethodSecurityConfiguration {
//	
//	@Autowired
//	private ResourceObjectService resourceObjectService;
//	
//	@Bean
//	protected EntryPermissionEvaluator permissionEvaluator() {
//		return new EntryPermissionEvaluator(resourceObjectService);
//	}
//	
//	@Override
//	protected MethodSecurityExpressionHandler createExpressionHandler() {
//		DefaultMethodSecurityExpressionHandler expr = new DefaultMethodSecurityExpressionHandler();
//		expr.setPermissionEvaluator(permissionEvaluator());
//		return expr;
//	}
//
//}
