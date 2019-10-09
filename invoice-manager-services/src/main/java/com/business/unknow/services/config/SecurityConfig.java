/**
 * 
 */
package com.business.unknow.services.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author hha0009
 *
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	
	@Autowired
	Environment environment;
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		List<String> envs = Arrays.asList(environment.getActiveProfiles());
		
		if(envs.contains("stg") || envs.contains("prod")) {
			http.csrf().disable().authorizeRequests().antMatchers("/api/**").permitAll().anyRequest().authenticated().and()
			.oauth2Login().and().logout().logoutUrl("/api/logout").invalidateHttpSession(true)
			.deleteCookies("JSESSIONID");
		}else {
			http.csrf().disable().authorizeRequests().antMatchers("/**").permitAll();
		}
	}
}
