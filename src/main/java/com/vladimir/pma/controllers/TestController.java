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
@RequestMapping("/rest/tests")
public class TestController {
	private static final Log log = LogFactory.getLog(TestController.class);

	@Autowired
	private TestDao testDao;
	
	@Autowired
	private ResultDao resultDao;

	@Autowired
	private SlideDao slideDao;
	
	@Autowired
	private UserAccountDao userAccountDao;
	

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Test> getTest(@PathVariable(value = "id") int id, @RequestHeader HttpHeaders headers) {
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
	
	
	@RequestMapping(value = "/generateException", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> generateException() {
		log.info("generateException(): /rest/tests/generateException --- before Exception");
		throw new RuntimeException("Ovo je greska generisana na nivou kontrolera!");
//		log.info("generateException(): /rest/tests/generateException --- after Exception");
//		return new ResponseEntity<String>("generateException controller", HttpStatus.OK);
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
	public ResponseEntity<String> saveResults(@PathVariable(value = "id") int id, @RequestBody Result result) {
		log.info("saveResults(): /rest/tests/results ");
		
		Test test = testDao.findById(result.getTestId());
		result.setTest(test);
		result.setTestTaken(new Date());
		UserAccount userAccount = SecurityUtils.getUserFromContext();
		result.setUserAccount(userAccount);
		resultDao.persist(result);	

		
		
//		ObjectMapper objectMapper = new ObjectMapper();
//		try {
//			Result result = objectMapper.readValue(json, Result.class);
//		} catch (JsonParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (JsonMappingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}  
		
		return new ResponseEntity<String>("OK", HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/statistics/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getStatistics(@PathVariable(value = "id") int id, @RequestBody TestScore testScore) {
		log.info("getStatistics(): /rest/tests/statistics ");

		return new ResponseEntity<String>("OK", HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST, consumes="multipart/form-data")
	public ResponseEntity<Map<String, String>> uploadTest(@RequestPart(required = false, name="imageFile") MultipartFile imageFile, @RequestPart(name="test") Test receivedTest) {
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
	
	
	@RequestMapping(value = "/slides/upload/{id}", method = RequestMethod.POST, consumes="multipart/form-data")
	public ResponseEntity<Map<String, String>> uploadSlide(@RequestPart(required = false, name="primingImageFile") MultipartFile primingImageFile, @RequestPart(required = false, name="testImageFile") MultipartFile testImageFile, @RequestPart(name="slide") Slide receivedSlide, @PathVariable(value = "id") int id) {
		try {
			if(primingImageFile !=null && !primingImageFile.isEmpty()){
				receivedSlide.setPrimingImage(primingImageFile.getBytes());
			}
			if(testImageFile !=null && !testImageFile.isEmpty()){
				receivedSlide.setTestImage(testImageFile.getBytes());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		slideDao.merge(receivedSlide, id);
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
	public ResponseEntity<Map<String, String>> deleteTest(@PathVariable(value = "id") int id) {
		testDao.deleteById(id);
		Map<String, String> response = new HashMap<String, String>();
		response.put("Status", "OK");
		return new ResponseEntity<Map<String, String>>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/slides/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, String>> deleteSlide(@PathVariable(value = "id") int id) {
		slideDao.deleteById(id);
		Map<String, String> response = new HashMap<String, String>();
		response.put("Status", "OK");
		return new ResponseEntity<Map<String, String>>(response, HttpStatus.OK);
	}

}
