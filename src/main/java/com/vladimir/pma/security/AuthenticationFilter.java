package com.vladimir.pma.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.GenericFilterBean;


/**
 * The class is used as a Spring security filter bean. It is inserted into the
 * Spring security chain of filters. Its method doFilter() is used to validate
 * the received AUTH-TOKEN. In case of positive validation, UserDetails are
 * inserted into the Spring security context.
 * 
 * @author Vladimir
 *
 */
public class AuthenticationFilter extends GenericFilterBean {

	private static final Logger LOGGER = Logger.getLogger(AuthenticationFilter.class.getName());

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = SecurityUtils.getAsHttpRequest(request);
		boolean validationResult = false;
		LOGGER.info("evo me ------------------------------");

/*		if (validationResult) {
			UserDetails userDetails = (UserDetails) user;
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,
					null, userDetails.getAuthorities());
			authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}*/
		chain.doFilter(request, response);
	}



}