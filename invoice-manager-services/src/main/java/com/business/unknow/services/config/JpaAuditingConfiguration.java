/**
 * 
 */
package com.business.unknow.services.config;

import java.util.Optional;

import org.springframework.security.core.context.SecurityContext;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author hha0009
 *
 */
//@Configuration
//@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
//public class JpaAuditingConfiguration {
//
//	@Bean
//	public AuditorAware<String> auditorProvider() {
//
//		SecurityContext context = SecurityContextHolder.getContext(); 
//		
//		if(context==null) {
//			return () -> Optional.ofNullable("anonymus");
//		}else {
//			return () -> Optional.ofNullable(context.getAuthentication().getName());
//		}
//	}
//}