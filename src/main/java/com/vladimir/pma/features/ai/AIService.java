package com.vladimir.pma.features.ai;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vladimir.pma.data.dao.ResultDao;
import com.vladimir.pma.data.dao.StatisticsDao;
import com.vladimir.pma.data.dao.TestDao;
import com.vladimir.pma.data.entity.Answer;
import com.vladimir.pma.data.entity.Result;
import com.vladimir.pma.data.entity.Test;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

@Service
public class AIService {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private ResultDao resultDao;

	@Autowired
	private TestDao testDao;

	@Autowired
	private StatisticsDao statisticsDao;

	public Instances getInstancesForTest(int testId, Boolean trainingOrTesting) {
		Test test = testDao.findById(testId);
		List<Result> resultList = resultDao.getAllResultsForTest(test);

		Attribute a0 = null;

		// Declare the class attribute along with its values
		List classVal = new ArrayList();
		Attribute classAttribute = new Attribute("Class");
		Instances trainingSet = null;

		// Declare the feature vector
		List fvAnswers = new ArrayList();

		if (resultList.size() > 0) {
			fvAnswers = new ArrayList();
			for (Answer answer : resultList.get(0).getAnswerList()) {
				a0 = new Attribute("Answer-" + String.valueOf(answer.getAnswerNumber()));
				fvAnswers.add(a0);
			}
			fvAnswers.add(classAttribute);
			trainingSet = new Instances("Answers", (ArrayList<Attribute>) fvAnswers, 10000);
			trainingSet.setClassIndex(fvAnswers.size() - 1);

			for (Result result : resultList) {
				if ((result.getSupervisedValue() != null && trainingOrTesting) || (result.getSupervisedValue() == null && !trainingOrTesting)) {
					Instance iAnswer = new DenseInstance(fvAnswers.size() + 1);
					int index = 0;
					for (Answer answer : result.getAnswerList()) {
						// Create the instance
						iAnswer.setValue(index, answer.getAnswerValue());
						index++;
					}
					if(result.getSupervisedValue()!=null){
						int supervisedValue = ((result.getSupervisedValue()) ? 1 : 0);
						iAnswer.setValue(index, supervisedValue);
					}
					trainingSet.add(iAnswer);
				}
			}
			return trainingSet;
		}
		return null;
	}

	public Classifier createClassifier(Class classifierClass, Instances trainingSet) {

		Classifier classifier = null;
		try {
			classifier = (Classifier) classifierClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e1) {
			e1.printStackTrace();
		}
		try {
			classifier.buildClassifier(trainingSet);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return classifier;
	}

	public String evaluateClassifier(Classifier classifier, Instances trainingSet, Instances testingSet) {

		Evaluation evaluation = null;
		try {
			evaluation = new Evaluation(trainingSet);
			evaluation.evaluateModel(classifier, testingSet);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String summary = evaluation.toSummaryString();
//		double[][] confusionMatrix = evaluation.confusionMatrix();
		
		System.out.println(summary);
//		System.out.println(confusionMatrix);
		
		return summary;
	}


}
