package com.vladimir.pma.controllers;

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
import weka.classifiers.functions.MultilayerPerceptron;
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
	
	
	
	@RequestMapping(value = "/train", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> train() {
		log.info("train(): /rest/ai ");
		Instances trainingSet = aiService.getTrainingDataset(1);
		Instances testingSet  = aiService.getTestingDataset(1);
		Classifier classifier = aiService.createClassifier(MultilayerPerceptron.class, trainingSet);
		String evaluation = aiService.evaluateClassifier(classifier, trainingSet, testingSet);
		
		return new ResponseEntity<String>(evaluation, HttpStatus.OK);
	}


}
