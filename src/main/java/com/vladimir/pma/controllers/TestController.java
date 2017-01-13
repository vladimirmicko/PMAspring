package com.vladimir.pma.controllers;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vladimir.pma.data.dao.ProbaDao;
import com.vladimir.pma.data.dao.SlideDao;
import com.vladimir.pma.data.dao.TestDao;
import com.vladimir.pma.data.dao.UserDao;
import com.vladimir.pma.data.entity.Proba;
import com.vladimir.pma.data.entity.Slide;
import com.vladimir.pma.data.entity.Test;
import com.vladimir.pma.data.entity.UserAccounts;

@RestController
@RequestMapping("/rest/tests")
public class TestController {
	private static final Log log = LogFactory.getLog(TestController.class);
	
	@Autowired
	private TestDao testDao;
	
	@Autowired
	private SlideDao slideDao;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Test> getTest(@PathVariable(value = "id") int id) {
		log.info("getTest(): /rest/tests ");
		Test test = testDao.findById(id);
		return new ResponseEntity<Test>(test, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Test>> getAllTests() {
		log.info("getAllTests(): /rest/tests ");
		List<Test> testList = testDao.findAll();
		return new ResponseEntity<List<Test>>(testList, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/slides/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Slide> getSlide(@PathVariable(value = "id") int id) {
		log.info("getSlide(): /rest/tests ");
		Slide slide = slideDao.findById(id);
		return new ResponseEntity<Slide>(slide, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/slides", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Slide>> getAllSlides() {
		log.info("getAllSlides(): /rest/tests ");
		List<Slide> testList = slideDao.findAll();
		return new ResponseEntity<List<Slide>>(testList, HttpStatus.OK);
	}
}
