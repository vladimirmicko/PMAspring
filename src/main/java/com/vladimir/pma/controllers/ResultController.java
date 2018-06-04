package com.vladimir.pma.controllers;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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
		if(result.getSupervisedValue()){
			resultDao.setSupervised(id, false);
		}
		else{
			resultDao.setSupervised(id, true);
		}
		result = resultDao.findById(id);
		return new ResponseEntity<Result>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/statisticsForTest/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Statistics> getStatisticsForTest(@PathVariable(value = "id") int id) {
		log.info("getResult(): /statisticsForTest/{id}");
		
		Test test = testDao.findById(id);
		List<Result> resultList = statisticsDao.getAllResultsForTest(test);
		Statistics statistics = new Statistics();
		statistics.setTotalNumberOfResultsForTest(resultList.size());
		
		for(Result result : resultList){
			if (result!=null && result.getEvaluation()!=null && result.getUserAccount()!=null && result.getUserAccount().getSex()!=null){
				if (result.getEvaluation()==1 && result.getUserAccount().getSex().equals("M")){
					statistics.setNumberOfMenPositive(statistics.getNumberOfMenPositive()+1);
				}
				
				if (result.getEvaluation()==0 && result.getUserAccount().getSex().equals("M")){
					statistics.setNumberOfMenNegative(statistics.getNumberOfMenNegative()+1);
				}
	
				if (result.getEvaluation()==1 && result.getUserAccount().getSex().equals("M") && Utility.getAgeInYears(result.getUserAccount().getBirthdate())<31){
					statistics.setNumberOfMenPositive_30(statistics.getNumberOfMenPositive_30()+1);
				}
				if (result.getEvaluation()==0 && result.getUserAccount().getSex().equals("M") && Utility.getAgeInYears(result.getUserAccount().getBirthdate())<31){
					statistics.setNumberOfMenNegative_30(statistics.getNumberOfMenNegative_30()+1);
				}
				
				if (result.getEvaluation()==1 && result.getUserAccount().getSex().equals("M") && Utility.getAgeInYears(result.getUserAccount().getBirthdate())>=31 && Utility.getAgeInYears(result.getUserAccount().getBirthdate())<51){
					statistics.setNumberOfMenPositive31_50(statistics.getNumberOfMenPositive31_50()+1);
				}
				if (result.getEvaluation()==0 && result.getUserAccount().getSex().equals("M") && Utility.getAgeInYears(result.getUserAccount().getBirthdate())>=31 && Utility.getAgeInYears(result.getUserAccount().getBirthdate())<51){
					statistics.setNumberOfMenNegative31_50(statistics.getNumberOfMenNegative31_50()+1);
				}
				
				if (result.getEvaluation()==1 && result.getUserAccount().getSex().equals("M") && Utility.getAgeInYears(result.getUserAccount().getBirthdate())>50){
					statistics.setNumberOfMenNegative51_(statistics.getNumberOfMenNegative51_()+1);
				}
				if (result.getEvaluation()==0 && result.getUserAccount().getSex().equals("M") && Utility.getAgeInYears(result.getUserAccount().getBirthdate())>50){
					statistics.setNumberOfMenNegative51_(statistics.getNumberOfMenNegative51_()+1);
				}
	
	
				if (result.getEvaluation()==1 && result.getUserAccount().getSex().equals("F") && Utility.getAgeInYears(result.getUserAccount().getBirthdate())<31){
					statistics.setNumberOfWomenPositive_30(statistics.getNumberOfWomenPositive_30()+1);
				}
				if (result.getEvaluation()==0 && result.getUserAccount().getSex().equals("F") && Utility.getAgeInYears(result.getUserAccount().getBirthdate())<31){
					statistics.setNumberOfWomenNegative_30(statistics.getNumberOfWomenNegative_30()+1);
				}
				
				if (result.getEvaluation()==1 && result.getUserAccount().getSex().equals("F") && Utility.getAgeInYears(result.getUserAccount().getBirthdate())>=31 && Utility.getAgeInYears(result.getUserAccount().getBirthdate())<51){
					statistics.setNumberOfWomenPositive31_50(statistics.getNumberOfWomenPositive31_50()+1);
				}
				if (result.getEvaluation()==0 && result.getUserAccount().getSex().equals("F") && Utility.getAgeInYears(result.getUserAccount().getBirthdate())>=31 && Utility.getAgeInYears(result.getUserAccount().getBirthdate())<51){
					statistics.setNumberOfWomenNegative31_50(statistics.getNumberOfWomenNegative31_50()+1);
				}
				
				if (result.getEvaluation()==1 && result.getUserAccount().getSex().equals("F") && Utility.getAgeInYears(result.getUserAccount().getBirthdate())>50){
					statistics.setNumberOfWomenPositive51_(statistics.getNumberOfWomenPositive51_()+1);
				}
				if (result.getEvaluation()==0 && result.getUserAccount().getSex().equals("F") && Utility.getAgeInYears(result.getUserAccount().getBirthdate())>50){
					statistics.setNumberOfWomenNegative51_(statistics.getNumberOfWomenNegative51_()+1);
				}
	
				
				double averageAnswerTime=0;
				for(Answer answer : result.getAnswerList()){
					averageAnswerTime=averageAnswerTime+answer.getAnswerTime();
				}
				averageAnswerTime=averageAnswerTime/result.getAnswerList().size();
				
				statistics.setAverageResponseTime(statistics.getAverageResponseTime()+averageAnswerTime);
				if (result.getUserAccount().getSex().equals("M")){
					statistics.setNumberOfMen(statistics.getNumberOfMen()+1);
					statistics.setAverageResponseTimeMen(statistics.getAverageResponseTimeMen()+averageAnswerTime);
					
					if(Utility.getAgeInYears(result.getUserAccount().getBirthdate())<31){
						statistics.setNumberOfMen_30(statistics.getNumberOfMen_30()+1);
					    statistics.setAverageResponseTimeMen_30(statistics.getAverageResponseTimeMen_30()+averageAnswerTime);
					}
					else if(Utility.getAgeInYears(result.getUserAccount().getBirthdate())>=31 && Utility.getAgeInYears(result.getUserAccount().getBirthdate())<51){
						statistics.setNumberOfMen31_50(statistics.getNumberOfMen31_50()+1);
						statistics.setAverageResponseTimeMen31_50(statistics.getAverageResponseTimeMen31_50()+averageAnswerTime);
					}
					else {
						statistics.setNumberOfMen51_(statistics.getNumberOfMen51_()+1);
						statistics.setAverageResponseTimeMen51_(statistics.getAverageResponseTimeMen51_()+averageAnswerTime);
					}
				}
				else{
					statistics.setNumberOfWomen(statistics.getNumberOfWomen()+1);
					statistics.setAverageResponseTimeWomen(statistics.getAverageResponseTimeWomen()+averageAnswerTime);
					
					if(Utility.getAgeInYears(result.getUserAccount().getBirthdate())<31){
						statistics.setNumberOfWomen_30(statistics.getNumberOfWomen_30()+1);
					    statistics.setAverageResponseTimeWomen_30(statistics.getAverageResponseTimeWomen_30()+averageAnswerTime);
					}
					else if(Utility.getAgeInYears(result.getUserAccount().getBirthdate())>=31 && Utility.getAgeInYears(result.getUserAccount().getBirthdate())<51){
						statistics.setNumberOfWomen31_50(statistics.getNumberOfWomen31_50()+1);
						statistics.setAverageResponseTimeWomen31_50(statistics.getAverageResponseTimeWomen31_50()+averageAnswerTime);
					}
					else {
						statistics.setNumberOfWomen51_(statistics.getNumberOfWomen51_()+1);
						statistics.setAverageResponseTimeWomen51_(statistics.getAverageResponseTimeWomen51_()+averageAnswerTime);
					}
				}
			}
			
			statistics.setAverageResponseTime(statistics.getAverageResponseTime()/statistics.getTotalNumberOfResultsForTest());
			statistics.setAverageResponseTimeMen(statistics.getAverageResponseTimeMen()/statistics.getNumberOfMen());
			statistics.setAverageResponseTimeWomen(statistics.getAverageResponseTimeWomen()/statistics.getNumberOfWomen());
			
			statistics.setAverageResponseTimeMen_30(statistics.getAverageResponseTimeMen_30()/statistics.getNumberOfMen_30());
			statistics.setAverageResponseTimeMen31_50(statistics.getAverageResponseTimeMen31_50()/statistics.getNumberOfMen31_50());
			statistics.setAverageResponseTimeMen51_(statistics.getAverageResponseTimeMen51_()/statistics.getNumberOfMen51_());
			statistics.setAverageResponseTimeWomen_30(statistics.getAverageResponseTimeWomen_30()/statistics.getNumberOfWomen_30());
			statistics.setAverageResponseTimeWomen31_50(statistics.getAverageResponseTimeWomen31_50()/statistics.getNumberOfWomen31_50());
			statistics.setAverageResponseTimeWomen51_(statistics.getAverageResponseTimeWomen51_()/statistics.getNumberOfWomen51_());
		}
		
		return new ResponseEntity<Statistics>(statistics, HttpStatus.OK);
	}

}
