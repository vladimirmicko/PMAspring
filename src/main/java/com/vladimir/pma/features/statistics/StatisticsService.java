package com.vladimir.pma.features.statistics;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vladimir.pma.common.utility.Utility;
import com.vladimir.pma.controllers.ResultController;
import com.vladimir.pma.data.dao.ResultDao;
import com.vladimir.pma.data.dao.StatisticsDao;
import com.vladimir.pma.data.dao.TestDao;
import com.vladimir.pma.data.dto.Statistics;
import com.vladimir.pma.data.entity.Answer;
import com.vladimir.pma.data.entity.Result;
import com.vladimir.pma.data.entity.Test;

@Service
public class StatisticsService {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private ResultDao resultDao;
	
	@Autowired
	private TestDao testDao;

	@Autowired
	private StatisticsDao statisticsDao;

	public Statistics getStatistics(int testId){
		Test test = testDao.findById(testId);
		List<Result> resultList = resultDao.getAllResultsForTest(test);
		Statistics statistics = new Statistics();
		statistics.setTotalNumberOfResultsForTest(resultList.size());
		statistics.setTestId(testId);
		
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
	
				
				if (result.getEvaluation()==1 && result.getUserAccount().getSex().equals("F")){
					statistics.setNumberOfWomenPositive(statistics.getNumberOfWomenPositive()+1);
				}
				
				if (result.getEvaluation()==0 && result.getUserAccount().getSex().equals("F")){
					statistics.setNumberOfWomenNegative(statistics.getNumberOfWomenNegative()+1);
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
					averageAnswerTime=averageAnswerTime+answer.getAnswerTime()-answer.getTestStimShowTime();
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

		return statistics;
	}
}	


