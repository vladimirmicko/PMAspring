package com.vladimir.pma.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;



/**
 * 403 exception handling
 * @author randjelovicv
 *
 */
public class AccessDeniedExceptionHandler implements AccessDeniedHandler  {

	@Autowired
	private MessageSource messageSource;
	
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException ex) throws IOException, ServletException {
    	
  		response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied");

    }

}