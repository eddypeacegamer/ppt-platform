package com.business.unknow.services.security;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.server.ResponseStatusException;



@Component
public class AuthenticationFilter extends GenericFilterBean{
	
	
	private static final Logger log = LoggerFactory.getLogger(AuthenticationFilter.class);

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest  req = (HttpServletRequest) request;
		
		OidcUser oidcUser = (OidcUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(oidcUser!=null && oidcUser.getAttributes()!=null && oidcUser.getEmail()!=null) {
			log.debug("{} is requesting {}?{} from {}", oidcUser.getEmail(),req.getRequestURL(),req.getQueryString(),request.getRemoteAddr());
			filterChain.doFilter(request, response);	
		}else {
			log.error("Unauhtorized request {}?{} from {}",req.getRequestURL(),req.getQueryString(),request.getRemoteAddr());
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Session invalida o usuario no autorizado.");
		}
	}

}
