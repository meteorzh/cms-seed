package com.github.wenzhencn.cmsseed.configuration;

import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

import com.github.wenzhencn.cmsseed.system.security.EntryPermissionEvaluator;

@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppGlobalMethodSecurityConfiguration extends GlobalMethodSecurityConfiguration {
	
	@Override
	protected MethodSecurityExpressionHandler createExpressionHandler() {
		DefaultMethodSecurityExpressionHandler expr = new DefaultMethodSecurityExpressionHandler();
		expr.setPermissionEvaluator(new EntryPermissionEvaluator());
		return expr;
	}

}
