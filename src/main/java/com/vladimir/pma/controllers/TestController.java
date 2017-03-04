package com.vladimir.pma.controllers;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.vladimir.pma.data.dao.SlideDao;
import com.vladimir.pma.data.dao.TestDao;
import com.vladimir.pma.data.dto.Hero;
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
	public ResponseEntity<Test> getTest(@PathVariable(value = "id") int id) {
		log.info("getTest(): /rest/tests ");
		Test test = testDao.findById(id);
		return new ResponseEntity<Test>(test, HttpStatus.OK);
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

	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Test>> getAllTests() {
		log.info("getAllTests(): /rest/tests ");
		List<Test> testList = testDao.findAll();
		
		for(Test test : testList){
			test.setPromoImage(new String(Base64.getEncoder().encode(test.getTestPromoImage())));
		}
		
		return new ResponseEntity<List<Test>>(testList, HttpStatus.OK);
	}
	
	
//	@RequestMapping(value = "/captchacode", method = RequestMethod.GET)
//	public ResponseEntity<RestResponseDto> getCaptchaCode() {
//		HashMap<String, String> map = new HashMap<>();
//
//		String text = CSRCaptchaEngine.generateRandomWords();
//		byte[] imageBytes = CSRCaptchaEngine.generateImage(text);
//		byte[] encoded = Base64.getEncoder().encode(imageBytes);
//		String captchaCode = new String(encoded);
//
//		String hashCode = Hashing.sha256().hashString(text, StandardCharsets.UTF_8).toString();
//		map.put("imageBase64", captchaCode);
//		map.put("hashedCode", hashCode);
//		return new ResponseEntity<RestResponseDto>(new RestResponseDto(map, HttpStatus.OK.value()), HttpStatus.OK);
//	}
	

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
	
	
	@RequestMapping(value = "/heros", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Hero>> getAllHeros() {
		log.info("getAllHeros(): /rest/tests/heros ");
				
		List<Hero> heroList = new ArrayList();
		heroList.add(new Hero(11, "Micko"));
		heroList.add(new Hero(12, "Cicko"));
		heroList.add(new Hero(13, "Prcko"));
		heroList.add(new Hero(14, "Cvrcko"));
		heroList.add(new Hero(15, "Mile"));
		heroList.add(new Hero(16, "Pile"));
		heroList.add(new Hero(17, "Cvile"));
		heroList.add(new Hero(18, "Lale"));
		
		return new ResponseEntity<List<Hero>>(heroList, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST, consumes="multipart/form-data")
	public ResponseEntity<String> uploadFile(@RequestPart(name="imageFile") MultipartFile imageFile) {
		return new ResponseEntity<String>("OK", HttpStatus.OK);
	}

}
