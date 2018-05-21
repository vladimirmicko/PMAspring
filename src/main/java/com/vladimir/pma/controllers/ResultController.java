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
@RequestMapping("/rest/results")
public class ResultController {
	private static final Log log = LogFactory.getLog(ResultController.class);

	@Autowired
	private TestDao testDao;
	
	@Autowired
	private ResultDao resultDao;
	
	@Autowired
	private ResultDao answertDao;
	
	@Autowired
	private UserAccountDao userAccountDao;
	

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Result> getResult(@PathVariable(value = "id") int id, @RequestHeader HttpHeaders headers, @RequestHeader("Authorization") String encoding) {
		log.info("getResult(): /rest/results ");
		Result result = resultDao.findById(id);
		return new ResponseEntity<Result>(result, HttpStatus.OK);
	}


	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Result>> getAllResults() {
		log.info("getAllResults(): /rest/results ");
		List<Result> resultList = resultDao.findAll();
		return new ResponseEntity<List<Result>>(resultList, HttpStatus.OK);
	}
	



}
