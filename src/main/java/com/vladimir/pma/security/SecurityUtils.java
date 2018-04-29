package com.vladimir.pma.security;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

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
}
