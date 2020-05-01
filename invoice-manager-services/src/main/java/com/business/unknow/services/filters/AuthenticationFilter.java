/**
 * 
 */
package com.business.unknow.services.filters;

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
