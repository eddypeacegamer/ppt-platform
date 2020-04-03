/**
 * 
 */
package com.business.unknow.services.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author hha0009
 *
 */
//@Component
//public class AuthenticationFilter extends GenericFilterBean{
//	
//	
//	
//	private static final Logger log = LoggerFactory.getLogger(AuthenticationFilter.class);
//
//
//	@Override
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//			throws IOException, ServletException {
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		if(auth!=null) {
//			String stringDetails = new ObjectMapper().writeValueAsString(auth.getAuthorities());
//			log.info(stringDetails);
//		}else {
//			log.warn("Auth is null.");
//		}
//	}
//
//}
