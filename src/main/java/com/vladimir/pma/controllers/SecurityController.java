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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.vladimir.pma.data.dto.UserLogin;

@RestController
@RequestMapping("/rest/security")
public class SecurityController {
	private static final Log log = LogFactory.getLog(SecurityController.class);
	
	@Autowired
	private AuthenticationManager authenticationManager;

//	@RequestMapping(value = "/authenticate", method = RequestMethod.POST, produces = "application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Map<String, String>> authenticate(@RequestBody UserLogin login) {
//		log.info("HTTP request-POST: /rest/security/authenticate");
//		Map<String, String> map = new HashMap();
//		if ("v".equals(login.getUsername()) && "v".equals(login.getPassword())) {
//			map.put("token", "OK");
//		}
//		else {
//			map.put("token", "");
//		}
//		
//		return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
//	}
//	
	

	
	/**
	 * Method used for authenticate user
	 * @param userAccount
	 * @return
	 */
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> createUser(@RequestBody UserLogin userLogin) {
		
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
		
		
//		log.info("HTTP request-POST: /rest/security/authenticate");
//		Map<String, String> map = new HashMap();
//		if ("v".equals(userLogin.getUsername()) && "v".equals(userLogin.getPassword())) {
//			map.put("token", "OK");
//		}
//		else {
//			map.put("token", "");
//		}

		
		return new ResponseEntity<Object>(null, returnStatus);
	}
	
	/**
	 * Method used when logout is success
	 * @return
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> logout() {
		
		return new ResponseEntity<Object>(null, HttpStatus.OK);
	}

}
