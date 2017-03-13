package com.vladimir.pma.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.vladimir.pma.data.dao.SlideDao;
import com.vladimir.pma.data.dao.TestDao;
import com.vladimir.pma.data.dto.TestScore;
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
	public ResponseEntity<Test> getTest(@PathVariable(value = "id") int id, @RequestHeader HttpHeaders headers, @RequestHeader("Authorization") String encoding) {
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

	
	@RequestMapping(value = "/{id}/results", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> saveResults(@PathVariable(value = "id") int id, @RequestBody TestScore testScore) {
		log.info("saveResults(): /rest/tests/results ");
		Test test = testDao.findById(id);

		StringBuilder results = new StringBuilder();
		Integer counter = 1;
		for (Slide slide : test.getSlideList()) {
			results.append(counter.toString()).append(". ").append(slide.getSlideName()).append(" = ").append(
					(testScore.getScoreList().get(counter - 1) == null ? 0 : testScore.getScoreList().get(counter - 1))
							+ "\n");
			counter++;
		}

		log.info("RESULTS: "+"\n"+results.toString());
		return new ResponseEntity<String>(results.toString(), HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/statistics/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getStatistics(@PathVariable(value = "id") int id, @RequestBody TestScore testScore) {
		log.info("getStatistics(): /rest/tests/statistics ");
		Test test = testDao.findById(id);

		StringBuilder results = new StringBuilder();
		Integer counter = 1;
		for (Slide slide : test.getSlideList()) {
			results.append(counter.toString())
					.append(". ")
					.append(slide.getSlideName()).append(" = ")
					.append((testScore.getScoreList().get(counter - 1) == null ? 
							0 : (testScore.getScoreList().get(counter - 1).equals("0") ? "You are below average!" : "Great achievement!")) + "\n");
			counter++;
		}

		log.info("STATISTICS: "+"\n"+results.toString());
		return new ResponseEntity<String>(results.toString(), HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST, consumes="multipart/form-data")
	public ResponseEntity<Map<String, String>> uploadFile(@RequestPart(required = false, name="imageFile") MultipartFile imageFile, @RequestPart(name="test") Test receivedTest) {
		
		try {
			if(imageFile !=null && !imageFile.isEmpty()){
				receivedTest.setTestPromoImage(imageFile.getBytes());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		testDao.merge(receivedTest);
		Map<String, String> response = new HashMap<String, String>();
		response.put("Status", "OK");
		return new ResponseEntity<Map<String, String>>(response, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, String>> postTest(@RequestBody Test test) {
		testDao.post(test);
		log.info("putTest(): " + test.getTestName());
		Map<String, String> response = new HashMap<String, String>();
		response.put("Status", "OK");
		return new ResponseEntity<Map<String, String>>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, String>> persistTest(@RequestBody Test test) {
		testDao.persist(test);
		log.info("postTest(): " + test.getTestName());
		Map<String, String> response = new HashMap<String, String>();
		response.put("Status", "OK");
		return new ResponseEntity<Map<String, String>>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, String>> uploadFile(@PathVariable(value = "id") int id) {
		testDao.deleteById(id);
		Map<String, String> response = new HashMap<String, String>();
		response.put("Status", "OK");
		return new ResponseEntity<Map<String, String>>(response, HttpStatus.OK);
	}

}
