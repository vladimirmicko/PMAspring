package com.vladimir.pma.controllers;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/security")
public class SecurityController {
	private static final Log log = LogFactory.getLog(SecurityController.class);
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity getAllUsers(HttpServletRequest request) {
		log.info("HTTP request-GET: /rest/security/authenticate");
		return new ResponseEntity(null, HttpStatus.OK);
	}
}
