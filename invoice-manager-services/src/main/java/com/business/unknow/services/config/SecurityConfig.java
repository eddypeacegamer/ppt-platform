/**
 * 
 */
package com.business.unknow.services.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;


/**
 *@author ralfdemoledor
 *
 */
@Configuration
@Profile("!local")
public class SecurityConfig extends WebSecurityConfigurerAdapter {


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and()
		.authorizeRequests()
         .anyRequest().authenticated().and().oauth2Login()
         .and().logout().logoutUrl("/api/logout").invalidateHttpSession(true)
		 .deleteCookies("JSESSIONID");
	}
}
