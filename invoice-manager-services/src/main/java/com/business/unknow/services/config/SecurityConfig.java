/**
 * 
 */
package com.business.unknow.services.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 *@author ralfdemoledor
 *
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//http.csrf().disable().authorizeRequests().antMatchers("/api/**").permitAll() // TODO create local profile to protect everything in higher environments
		http.csrf().disable().authorizeRequests()
         .anyRequest().authenticated().and().oauth2Login()
         .and().logout().logoutUrl("/api/logout").invalidateHttpSession(true)
		 .deleteCookies("JSESSIONID");
	}
}
