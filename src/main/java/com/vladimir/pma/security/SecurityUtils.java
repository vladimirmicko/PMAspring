package com.vladimir.pma.security;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * The class has a number of static methods used as token utilities
 * @author randjelovicv
 *
 */
public class SecurityUtils {
	
    public static String convertObjectToJson(Object object) throws JsonProcessingException {
        if (object == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }
    
	
	/**
	 * Convert the ServletRequest into HttpServletRequest
	 * 
	 * @param request of type ServletRequest
	 * @return HttpServletRequest
	 */
	public static HttpServletRequest getAsHttpRequest(ServletRequest request) {
		if (!(request instanceof HttpServletRequest)) {
			throw new RuntimeException("Expecting an HTTP request");
		}
		return (HttpServletRequest) request;
	}
}