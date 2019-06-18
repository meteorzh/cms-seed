package com.github.wenzhencn.cmsseed.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
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
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
//	@Autowired
//	private LdapProperties ldapProperties;
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.exceptionHandling()
				.accessDeniedHandler(new RestAccessDeniedHandler())
				.authenticationEntryPoint(new RestAuthenticationEntryPoint()).and()
			.authorizeRequests()
				.requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
//				.antMatchers("/api/**").authenticated()
				.antMatchers("/api/**").permitAll()
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
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth
//			.ldapAuthentication()
//				.contextSource()
//				.url(ldapProperties.getUrls()[0] + ldapProperties.getBase())
//				.managerDn(ldapProperties.getUsername())
//				.managerPassword(ldapProperties.getPassword());
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
