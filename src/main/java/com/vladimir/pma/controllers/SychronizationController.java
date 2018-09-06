package com.vladimir.pma.controllers;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vladimir.pma.data.dao.ResultDao;
import com.vladimir.pma.data.dao.SlideDao;
import com.vladimir.pma.data.dao.TestDao;
import com.vladimir.pma.data.dao.UserAccountDao;
import com.vladimir.pma.data.dto.StimulusResult;
import com.vladimir.pma.data.dto.TestScore;
import com.vladimir.pma.data.entity.Result;
import com.vladimir.pma.data.entity.Slide;
import com.vladimir.pma.data.entity.Test;
import com.vladimir.pma.data.entity.UserAccount;
import com.vladimir.pma.security.SecurityUtils;

@RestController
@RequestMapping("/rest/time")
public class SychronizationController {
	private static final Log log = LogFactory.getLog(SychronizationController.class);

	@Autowired
	private UserAccountDao userAccountDao;
	

	@RequestMapping(value = "/deltaT/{tsMobile}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Long> ping(@PathVariable(value = "tsMobile") int tsMobile, @RequestHeader HttpHeaders headers) {
		log.info("getTest(): /rest/time");
		Long serverTime = System.nanoTime();
		//UserAccount user = SecurityUtils.getUserFromContext();
		return new ResponseEntity<Long>(serverTime, HttpStatus.OK);
	}

	@RequestMapping(value = "/synchronize/{tsMobile}/{deltaT}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Long> getTest(@PathVariable(value = "tsMobile") Long tsMobile, @PathVariable(value = "deltaT") Long deltaT, @RequestHeader HttpHeaders headers) {
		log.info("getTest(): /rest/time/synchronize");
		Long serverTime = System.nanoTime();
		UserAccount user = SecurityUtils.getUserFromContext();
		user.setDeltaT(deltaT);
		user.setTsMobile(tsMobile);
		Long tsServer = System.nanoTime();
		user.setTsServer(tsServer);
		log.info("Time synchronization:");
		log.info("Delta T: "+deltaT);
		log.info("Mobile timestamp: "+tsMobile);
		log.info("Server timestamp: "+tsServer);
		return new ResponseEntity<Long>(serverTime, HttpStatus.OK);
	}

}
