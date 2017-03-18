package com.vladimir.pma.controllers;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.vladimir.pma.data.dto.UserLogin;

@RestController
@RequestMapping("/rest/security")
public class SecurityController {
	private static final Log log = LogFactory.getLog(SecurityController.class);

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST, produces = "application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, String>> authenticate(@RequestBody UserLogin login) {
		log.info("HTTP request-POST: /rest/security/authenticate");
		Map<String, String> map = new HashMap();
		if ("v".equals(login.getUsername()) && "v".equals(login.getPassword())) {
			map.put("token", "OK");
		}
		else {
			map.put("token", "");
		}
		
		return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
	}

}
