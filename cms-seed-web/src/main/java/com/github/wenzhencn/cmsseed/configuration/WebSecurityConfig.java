package com.github.wenzhencn.cmsseed.configuration;

import com.github.wenzhencn.cmsseed.filter.EncryptionFilter;
import com.github.wenzhencn.cmsseed.filter.EncryptionProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.web.cors.CorsUtils;

import com.github.wenzhencn.cmsseed.security.RestAccessDeniedHandler;
import com.github.wenzhencn.cmsseed.security.RestAuthenticationEntryPoint;
import com.github.wenzhencn.cmsseed.security.RestAuthenticationFailureHandler;
import com.github.wenzhencn.cmsseed.security.RestAuthenticationSuccessHandler;
import com.github.wenzhencn.cmsseed.security.RestLogoutSuccessHandler;

/**
 * Security 配置
 * @author zhen.wen
 *
 */
@Configuration
@EnableWebSecurity
@EnableConfigurationProperties({ EncryptionProperties.class })
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private EncryptionProperties encryptionProperties;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		DefaultWebSecurityExpressionHandler expressionHandler = new DefaultWebSecurityExpressionHandler();
		expressionHandler.setDefaultRolePrefix(null);
		
		http.exceptionHandling()
				.accessDeniedHandler(new RestAccessDeniedHandler())
				.authenticationEntryPoint(new RestAuthenticationEntryPoint()).and()
			.authorizeRequests()
				.requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
				.antMatchers("/api/**").authenticated()
//				.antMatchers("/api/**").permitAll()
				.expressionHandler(expressionHandler)
				.and()
			.formLogin()
				.loginProcessingUrl("/api/login")
				.successHandler(new RestAuthenticationSuccessHandler())
				.failureHandler(new RestAuthenticationFailureHandler())
				.permitAll().and()
			.rememberMe().and()
			.csrf().disable()
			.cors().and()
			.logout()
				.logoutSuccessHandler(new RestLogoutSuccessHandler())
				.permitAll();

//		http.addFilterBefore(new EncryptionFilter(encryptionProperties), SecurityContextPersistenceFilter.class);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("wenzhen").password("123456").roles("ADMIN");
	}
	
//	@Bean
//	@Override
//	protected UserDetailsService userDetailsService() {
//		UserDetails user = User.withDefaultPasswordEncoder().username("wenzhen").password("123456").roles("USER").build();
//		return new InMemoryUserDetailsManager(user);
//	}
	
}
