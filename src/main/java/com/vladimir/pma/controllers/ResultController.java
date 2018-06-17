package com.vladimir.pma.controllers;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vladimir.pma.common.utility.Utility;
import com.vladimir.pma.data.dao.ResultDao;
import com.vladimir.pma.data.dao.StatisticsDao;
import com.vladimir.pma.data.dao.TestDao;
import com.vladimir.pma.data.dto.Statistics;
import com.vladimir.pma.data.entity.Answer;
import com.vladimir.pma.data.entity.Result;
import com.vladimir.pma.data.entity.Test;
import com.vladimir.pma.data.entity.UserAccount;
import com.vladimir.pma.features.ai.AIService;
import com.vladimir.pma.features.statistics.StatisticsService;
import com.vladimir.pma.security.SecurityUtils;

import weka.classifiers.Classifier;
import weka.core.DenseInstance;
import weka.core.Instance;

@RestController
@RequestMapping("/rest/results")
public class ResultController {
	private static final Log log = LogFactory.getLog(ResultController.class);

	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private ResultDao resultDao;
	
	@Autowired
	private TestDao testDao;

	@Autowired
	private StatisticsDao statisticsDao;
	
	@Autowired
	private StatisticsService statisticsService;
	
	@Autowired
	private AIService aiService;

	
	@RequestMapping(value = "/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Double> saveResults(@PathVariable(value = "id") int id, @RequestBody Result result) {
		log.info("saveResults(): /rest/tests/results ");
		
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
		
		Classifier classifier = null;
		Test test = testDao.findById(id);
		result.setTest(test);
		result.setTestTaken(new Date());
		UserAccount userAccount = SecurityUtils.getUserFromContext();
		result.setUserAccount(userAccount);
		resultDao.persist(result);	

		if(test.getClassifier()!=null){
			try {
				classifier = (Classifier)Utility.fromString(test.getClassifier());
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
		}
		
		Instance instance=null;
		instance = new DenseInstance(result.getAnswerList().size()+1);
		int index = 0;
		for (Answer answer : result.getAnswerList()) {
			instance.setValue(index, answer.getAnswerValue());
			index++;
		}
		instance.setValue(index, 0);

		Double prediction=null;
		try {
			prediction = classifier.classifyInstance(instance);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		return new ResponseEntity<Double>(prediction, HttpStatus.OK);
	}
	
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Result> getResult(@PathVariable(value = "id") int id) {
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
	

	@RequestMapping(value = "/toggleSupervised/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Result> toggleSupervised(@PathVariable(value = "id") int id) {
		log.info("toggleSupervised(): /rest/results ");
		Result result = resultDao.findById(id);
		if(result.getSupervisedValue()!=null && result.getSupervisedValue()){
			resultDao.setSupervised(id, false);
		}
		else{
			resultDao.setSupervised(id, true);
		}
		result = resultDao.findById(id);
		return new ResponseEntity<Result>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/statisticsForTest/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Statistics> getStatisticsForTest(@PathVariable(value = "id") int testId) {
		log.info("getStatistics(): /statisticsForTest/{id}");
		Statistics statistics = statisticsService.getStatistics(testId);
		return new ResponseEntity<Statistics>(statistics, HttpStatus.OK);
	}

}
