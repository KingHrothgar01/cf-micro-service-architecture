package com.cloudnativecoffee.market.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
/**
 * Provides security configuration for the application
 * @author lshannon
 *
 */
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
	        .authorizeRequests()
				.antMatchers("/", "/home", "/images/**", "/favicon.ico").permitAll()
				.antMatchers("/hystrix.stream/**").permitAll()
				.antMatchers("/webjars/**").permitAll()
				.anyRequest().authenticated()
			.and()
				.formLogin()
					.loginPage("/")
					.loginProcessingUrl("/login")
					.successForwardUrl("/home");

    }
}