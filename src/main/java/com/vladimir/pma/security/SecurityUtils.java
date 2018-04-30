package com.vladimir.pma.security;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.context.request.RequestContextHolder;

import com.vladimir.pma.data.dto.UserLogin;


public class SecurityUtils {
	
	public static UserLogin getUserFromContext() {

		SecurityContext securityContext = SecurityContextHolder.getContext();
		Object details = null;

		try {
			details = securityContext.getAuthentication().getPrincipal();
		} catch (NullPointerException e) {
			details = null;
		}

		UserLogin user = null;
		if (details != null && details instanceof UserLogin) {
			user = (UserLogin) details;
		}

		return user;
	}
	
	public static String getSessionIdFromSecurityContext(){
		String sessionId = ((WebAuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails()).getSessionId();
		return sessionId;
	}
	
	public static String getSessionIdFromRequestContext(){
		String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
		return sessionId;
	}
}
