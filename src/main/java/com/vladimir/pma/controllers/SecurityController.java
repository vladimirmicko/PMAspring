package com.vladimir.pma.controllers;

import java.util.HashMap;
import java.util.Map;

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
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map> getAllUsers(HttpServletRequest request) {
		log.info("HTTP request-GET: /rest/security/authenticate");
		Map<String, String> map = new HashMap();
		map.put("token", "OK");
		return new ResponseEntity(map, HttpStatus.OK);
	}
}
