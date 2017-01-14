package com.vladimir.pma.controllers;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vladimir.pma.data.dao.SlideDao;
import com.vladimir.pma.data.dao.TestDao;
import com.vladimir.pma.data.entity.Slide;
import com.vladimir.pma.data.entity.Test;



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
	
	
	@RequestMapping(value = "/{id}/slides", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Slide>> getSlidesByTest(@PathVariable(value = "id") int id) {
		log.info("getSlide(): /rest/tests/slides ");
		Test test = testDao.findById(id);
		List<Slide> slideList = test.getSlideList();
		return new ResponseEntity<List<Slide>>(slideList, HttpStatus.OK);
	}
	

	
	
	@RequestMapping(value = "/slides", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Slide>> getAllSlides() {
		log.info("getAllSlides(): /rest/tests ");
		List<Slide> testList = slideDao.findAll();
		return new ResponseEntity<List<Slide>>(testList, HttpStatus.OK);
	}
}
