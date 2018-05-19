package com.vladimir.pma.security;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.context.request.RequestContextHolder;

import com.vladimir.pma.data.dto.UserLogin;
import com.vladimir.pma.data.entity.UserAccount;


public class SecurityUtils {
	
	public static UserAccount getUserFromContext() {

		SecurityContext securityContext = SecurityContextHolder.getContext();
		UserAccount userAccount = null;

		try {
			userAccount = (UserAccount) securityContext.getAuthentication().getPrincipal();
		} catch (NullPointerException e) {
			userAccount = null;
		}

		return userAccount;
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
