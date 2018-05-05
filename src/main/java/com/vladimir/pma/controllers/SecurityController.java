package com.vladimir.pma.controllers;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;

import com.vladimir.pma.data.dto.UserLogin;
import com.vladimir.pma.security.SecurityUtils;

@RestController
@RequestMapping("/rest/security")
public class SecurityController {
	private static final Log log = LogFactory.getLog(SecurityController.class);
	
	@Autowired
	private AuthenticationManager authenticationManager;

	
	/**
	 * Method used for authenticate user
	 * @param userAccount
	 * @return
	 */
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserLogin> createUser(@RequestBody UserLogin userLogin) {
		
		Authentication authentication = null;
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				userLogin.getUsername(), userLogin.getPassword());

		HttpStatus returnStatus = HttpStatus.UNAUTHORIZED;
		
		try {
			authentication = this.authenticationManager.authenticate(authenticationToken);
			if (authentication.isAuthenticated()) 
				returnStatus=HttpStatus.OK;
		} catch (Exception e) {
			returnStatus=HttpStatus.UNAUTHORIZED;
			
		}

		SecurityContextHolder.getContext().setAuthentication(authentication);
		//String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
		String sessionId = SecurityUtils.getSessionIdFromRequestContext();
		
		return new ResponseEntity<UserLogin>(new UserLogin(null, null, sessionId), returnStatus);
	}
	
    /**
     * Method used when logout is success
     * @return
     */
    @RequestMapping(value = "/logout-success", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> logout() {
        SecurityContextHolder.getContext().setAuthentication(null);
        return new ResponseEntity<Object>(null, HttpStatus.OK);
    }


	
}
