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
		statistics.set_01__totalNumberOfResultsForTest(resultList.size());
		statistics.set$testId(testId);
		
		for(Result result : resultList){
			if (result!=null && result.getEvaluation()!=null && result.getUserAccount()!=null && result.getUserAccount().getSex()!=null){
				if (result.getEvaluation()==1 && result.getUserAccount().getSex().equals("M")){
					statistics.set_12__numberOfMenPositive(statistics.get_12__numberOfMenPositive()+1);
				}
				
				if (result.getEvaluation()==0 && result.getUserAccount().getSex().equals("M")){
					statistics.set_13__numberOfMenNegative(statistics.get_13__numberOfMenNegative()+1);
				}
	
				if (result.getEvaluation()==1 && result.getUserAccount().getSex().equals("M") && Utility.getAgeInYears(result.getUserAccount().getBirthdate())<31){
					statistics.set_17__numberOfMenPositive12_30(statistics.get_17__numberOfMenPositive12_30()+1);
				}
				if (result.getEvaluation()==0 && result.getUserAccount().getSex().equals("M") && Utility.getAgeInYears(result.getUserAccount().getBirthdate())<31){
					statistics.set_18__numberOfMenNegative12_30(statistics.get_18__numberOfMenNegative12_30()+1);
				}
				
				if (result.getEvaluation()==1 && result.getUserAccount().getSex().equals("M") && Utility.getAgeInYears(result.getUserAccount().getBirthdate())>=31 && Utility.getAgeInYears(result.getUserAccount().getBirthdate())<51){
					statistics.set_19__numberOfMenPositive31_50(statistics.get_19__numberOfMenPositive31_50()+1);
				}
				if (result.getEvaluation()==0 && result.getUserAccount().getSex().equals("M") && Utility.getAgeInYears(result.getUserAccount().getBirthdate())>=31 && Utility.getAgeInYears(result.getUserAccount().getBirthdate())<51){
					statistics.set_20__numberOfMenNegative31_50(statistics.get_20__numberOfMenNegative31_50()+1);
				}
				
				if (result.getEvaluation()==1 && result.getUserAccount().getSex().equals("M") && Utility.getAgeInYears(result.getUserAccount().getBirthdate())>50){
					statistics.set_21__numberOfMenPositive51_75(statistics.get_21__numberOfMenPositive51_75()+1);
				}
				if (result.getEvaluation()==0 && result.getUserAccount().getSex().equals("M") && Utility.getAgeInYears(result.getUserAccount().getBirthdate())>50){
					statistics.set_22__numberOfMenNegative51_75(statistics.get_22__numberOfMenNegative51_75()+1);
				}
	
				
				if (result.getEvaluation()==1 && result.getUserAccount().getSex().equals("F")){
					statistics.set_14__numberOfWomenPositive(statistics.get_14__numberOfWomenPositive()+1);
				}
				
				if (result.getEvaluation()==0 && result.getUserAccount().getSex().equals("F")){
					statistics.set_15__numberOfWomenNegative(statistics.get_15__numberOfWomenNegative()+1);
				}
	
				if (result.getEvaluation()==1 && result.getUserAccount().getSex().equals("F") && Utility.getAgeInYears(result.getUserAccount().getBirthdate())<31){
					statistics.set_26_numberOfWomenPositive12_30(statistics.get_26_numberOfWomenPositive12_30()+1);
				}
				if (result.getEvaluation()==0 && result.getUserAccount().getSex().equals("F") && Utility.getAgeInYears(result.getUserAccount().getBirthdate())<31){
					statistics.set_27__numberOfWomenNegative12_30(statistics.get_27__numberOfWomenNegative12_30()+1);
				}
				
				if (result.getEvaluation()==1 && result.getUserAccount().getSex().equals("F") && Utility.getAgeInYears(result.getUserAccount().getBirthdate())>=31 && Utility.getAgeInYears(result.getUserAccount().getBirthdate())<51){
					statistics.set_28__numberOfWomenPositive31_50(statistics.get_28__numberOfWomenPositive31_50()+1);
				}
				if (result.getEvaluation()==0 && result.getUserAccount().getSex().equals("F") && Utility.getAgeInYears(result.getUserAccount().getBirthdate())>=31 && Utility.getAgeInYears(result.getUserAccount().getBirthdate())<51){
					statistics.set_29__numberOfWomenNegative31_50(statistics.get_29__numberOfWomenNegative31_50()+1);
				}
				
				if (result.getEvaluation()==1 && result.getUserAccount().getSex().equals("F") && Utility.getAgeInYears(result.getUserAccount().getBirthdate())>50){
					statistics.set_30__numberOfWomenPositive51_75(statistics.get_30__numberOfWomenPositive51_75()+1);
				}
				if (result.getEvaluation()==0 && result.getUserAccount().getSex().equals("F") && Utility.getAgeInYears(result.getUserAccount().getBirthdate())>50){
					statistics.set_31__numberOfWomenNegative51_75(statistics.get_31__numberOfWomenNegative51_75()+1);
				}
	
				
				double averageAnswerTime=0;
				for(Answer answer : result.getAnswerList()){
					averageAnswerTime=averageAnswerTime+answer.getAnswerTime()-answer.getTestStimShowTime();
				}
				averageAnswerTime=averageAnswerTime/result.getAnswerList().size();
				
				statistics.set_35__averageResponseTime(statistics.get_35__averageResponseTime()+averageAnswerTime);
				if (result.getUserAccount().getSex().equals("M")){
					statistics.set_03__numberOfMen(statistics.get_03__numberOfMen()+1);
					statistics.set_36__averageResponseTimeMen(statistics.get_36__averageResponseTimeMen()+averageAnswerTime);
					
					if(Utility.getAgeInYears(result.getUserAccount().getBirthdate())<31){
						statistics.set_05__numberOfMen12_30(statistics.get_05__numberOfMen12_30()+1);
					    statistics.set_39__averageResponseTimeMen12_30(statistics.get_39__averageResponseTimeMen12_30()+averageAnswerTime);
					}
					else if(Utility.getAgeInYears(result.getUserAccount().getBirthdate())>=31 && Utility.getAgeInYears(result.getUserAccount().getBirthdate())<51){
						statistics.set_06__numberOfMen31_50(statistics.get_06__numberOfMen31_50()+1);
						statistics.set_40__averageResponseTimeMen31_50(statistics.get_40__averageResponseTimeMen31_50()+averageAnswerTime);
					}
					else {
						statistics.set_07__numberOfMen51_75(statistics.get_07__numberOfMen51_75()+1);
						statistics.set_41__averageResponseTimeMen51_75(statistics.get_41__averageResponseTimeMen51_75()+averageAnswerTime);
					}
				}
				else{
					statistics.set_04__numberOfWomen(statistics.get_04__numberOfWomen()+1);
					statistics.set_37__averageResponseTimeWomen(statistics.get_37__averageResponseTimeWomen()+averageAnswerTime);
					
					if(Utility.getAgeInYears(result.getUserAccount().getBirthdate())<31){
						statistics.set_08__numberOfWomen12_30(statistics.get_08__numberOfWomen12_30()+1);
					    statistics.set_42__averageResponseTimeWomen12_30(statistics.get_42__averageResponseTimeWomen12_30()+averageAnswerTime);
					}
					else if(Utility.getAgeInYears(result.getUserAccount().getBirthdate())>=31 && Utility.getAgeInYears(result.getUserAccount().getBirthdate())<51){
						statistics.set_09__numberOfWomen31_50(statistics.get_09__numberOfWomen31_50()+1);
						statistics.set_43__averageResponseTimeWomen31_50(statistics.get_43__averageResponseTimeWomen31_50()+averageAnswerTime);
					}
					else {
						statistics.set_10__numberOfWomen51_75(statistics.get_10__numberOfWomen51_75()+1);
						statistics.set_44__averageResponseTimeWomen51_75(statistics.get_44__averageResponseTimeWomen51_75()+averageAnswerTime);
					}
				}
			}
		}
		statistics.set_35__averageResponseTime(statistics.get_35__averageResponseTime()/statistics.get_01__totalNumberOfResultsForTest());
		statistics.set_36__averageResponseTimeMen(statistics.get_36__averageResponseTimeMen()/statistics.get_03__numberOfMen());
		statistics.set_37__averageResponseTimeWomen(statistics.get_37__averageResponseTimeWomen()/statistics.get_04__numberOfWomen());
		
		statistics.set_39__averageResponseTimeMen12_30(statistics.get_39__averageResponseTimeMen12_30()/statistics.get_05__numberOfMen12_30());
		statistics.set_40__averageResponseTimeMen31_50(statistics.get_40__averageResponseTimeMen31_50()/statistics.get_06__numberOfMen31_50());
		statistics.set_41__averageResponseTimeMen51_75(statistics.get_41__averageResponseTimeMen51_75()/statistics.get_07__numberOfMen51_75());
		statistics.set_42__averageResponseTimeWomen12_30(statistics.get_42__averageResponseTimeWomen12_30()/statistics.get_08__numberOfWomen12_30());
		statistics.set_43__averageResponseTimeWomen31_50(statistics.get_43__averageResponseTimeWomen31_50()/statistics.get_09__numberOfWomen31_50());
		statistics.set_44__averageResponseTimeWomen51_75(statistics.get_44__averageResponseTimeWomen51_75()/statistics.get_10__numberOfWomen51_75());

		return statistics;
	}
}	


