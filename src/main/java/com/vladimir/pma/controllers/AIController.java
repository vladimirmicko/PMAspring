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
import com.vladimir.pma.data.dto.RestResponseDto;
import com.vladimir.pma.data.dto.Statistics;
import com.vladimir.pma.data.entity.Answer;
import com.vladimir.pma.data.entity.Result;
import com.vladimir.pma.data.entity.Test;
import com.vladimir.pma.data.entity.UserAccount;
import com.vladimir.pma.features.ai.AIService;
import com.vladimir.pma.features.statistics.StatisticsService;
import com.vladimir.pma.security.SecurityUtils;

import weka.classifiers.Classifier;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

@RestController
@RequestMapping("/rest/ai")
public class AIController {
	private static final Log log = LogFactory.getLog(AIController.class);

	
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
	

	@RequestMapping(value = "/classifier/{testId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RestResponseDto> createClassifierForTest(@PathVariable(value = "testId") int testId) {
		log.info("createClassifierForTest(): /rest/ai/toggleSupervised/{testId}");
		Instances trainingSet = aiService.getTrainingDataset(testId);
		Instances testingSet  = aiService.getTestingDataset(testId);
		MultilayerPerceptron  classifier = aiService.createMLP(trainingSet);
		
		Classifier classifierFromString=null;
		String stringClassifier=null;
		try {
			stringClassifier = Utility.toString(classifier);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Test test = testDao.changeClassifier(testId, stringClassifier);
		
		try {
			classifierFromString = (Classifier)Utility.fromString(test.getClassifier());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String evaluation = aiService.evaluateClassifier(classifierFromString, trainingSet, testingSet);
		RestResponseDto restResponseDto = new RestResponseDto(200, evaluation);
		
		return new ResponseEntity<RestResponseDto>(restResponseDto, HttpStatus.OK);
	}


}
