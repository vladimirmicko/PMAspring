package com.vladimir.pma.controllers;

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

import com.vladimir.pma.data.dao.UserAccountDao;
import com.vladimir.pma.data.entity.UserAccount;
import com.vladimir.pma.security.SecurityUtils;

@RestController
@RequestMapping("/rest/security")
public class SecurityController {
	private static final Log log = LogFactory.getLog(SecurityController.class);
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserAccountDao userAccountDao;
		
	/**
	 * Method used for authenticate user
	 * @param userAccount
	 * @return
	 */
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserAccount> createUser(@RequestBody UserAccount userAccount) {
		
		Authentication authentication = null;
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				userAccount.getUsername(), userAccount.getPassword());

		HttpStatus returnStatus = HttpStatus.UNAUTHORIZED;
		
		try {
			authentication = this.authenticationManager.authenticate(authenticationToken);
			if (authentication.isAuthenticated()) 
				returnStatus=HttpStatus.OK;
		} catch (Exception e) {
			returnStatus=HttpStatus.UNAUTHORIZED;
			
		}

		userAccount = (UserAccount) userAccountDao.loadUserByUsername(userAccount.getUsername());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		//String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
		String sessionId = SecurityUtils.getSessionIdFromRequestContext();
		userAccount.setSessionId(sessionId);
		
		return new ResponseEntity<UserAccount>(userAccount, returnStatus);
	}
	
	
	/**
	 * Method used for authenticate user
	 * @param userAccount
	 * @return
	 */
	@RequestMapping(value = "/myProfile", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserAccount> changeMyProfile(@RequestBody UserAccount userAccountReceived) {
		
		UserAccount userAccount = (UserAccount) userAccountDao.merge(userAccountReceived);
		HttpStatus returnStatus = HttpStatus.OK;
		
		return new ResponseEntity<UserAccount>(userAccount, returnStatus);
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
