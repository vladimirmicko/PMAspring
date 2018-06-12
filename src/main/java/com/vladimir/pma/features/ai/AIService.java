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

	public void getInstancesFromDB() {

		// Declare two numeric attributes
		Attribute Attribute1 = new Attribute("firstNumeric");
		Attribute Attribute2 = new Attribute("secondNumeric");

		// Declare a nominal attribute along with its values
		List fvNominalVal = new ArrayList();
		fvNominalVal.add("blue");
		fvNominalVal.add("gray");
		fvNominalVal.add("black");
		Attribute Attribute3 = new Attribute("aNominal", fvNominalVal);

		// Declare the class attribute along with its values
		List fvClassVal = new ArrayList();
		fvClassVal.add("positive");
		fvClassVal.add("negative");
		Attribute ClassAttribute = new Attribute("theClass", fvClassVal);

		// Declare the feature vector
		List fvWekaAttributes = new ArrayList();
		fvWekaAttributes.add(Attribute1);
		fvWekaAttributes.add(Attribute2);
		fvWekaAttributes.add(Attribute3);
		fvWekaAttributes.add(ClassAttribute);

		// Create an empty training set
		Instances isTrainingSet = new Instances("Rel", (ArrayList<Attribute>) fvWekaAttributes, 10);
		// Set class index
		isTrainingSet.setClassIndex(3);

		// Create the instance
		Instance iExample = new DenseInstance(4);
		iExample.setValue((Attribute) fvWekaAttributes.get(0), 1.0);
		iExample.setValue((Attribute) fvWekaAttributes.get(1), 0.5);
		iExample.setValue((Attribute) fvWekaAttributes.get(2), "gray");
		iExample.setValue((Attribute) fvWekaAttributes.get(3), "positive");

		// add the instance
		isTrainingSet.add(iExample);

		// Create a naïve bayes classifier
		Classifier cModel = (Classifier) new NaiveBayes();
		try {
			cModel.buildClassifier(isTrainingSet);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Create an empty training set
		Instances isTestingSet = new Instances("Rel", (ArrayList<Attribute>) fvWekaAttributes, 10);
		// Set class index
		isTestingSet.setClassIndex(3);

		// Create the instance
		Instance iExampleTest = new DenseInstance(4);
		iExampleTest.setValue((Attribute) fvWekaAttributes.get(0), 1.0);
		iExampleTest.setValue((Attribute) fvWekaAttributes.get(1), 0.5);
		iExampleTest.setValue((Attribute) fvWekaAttributes.get(2), "gray");
		iExampleTest.setValue((Attribute) fvWekaAttributes.get(3), "positive");

		// Test the model
		Evaluation eTest = null;
		try {
			eTest = new Evaluation(isTrainingSet);
			eTest.evaluateModel(cModel, isTestingSet);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Print the result à la Weka explorer:
		String strSummary = eTest.toSummaryString();
		System.out.println(strSummary);
		// Get the confusion matrix
		double[][] cmMatrix = eTest.confusionMatrix();
	}

	public void getTrainingInstancesForTest(int testId) {
		Test test = testDao.findById(testId);
		List<Result> resultList = resultDao.getAllResultsForTest(test);

		Attribute a0, a1, a2, a3, a4, a5, a6;
		
		// Declare the class attribute along with its values
		List classVal = new ArrayList();
		classVal.add("positive");
		classVal.add("negative");
		Attribute classAttribute = new Attribute("Class", classVal);
		
		// Declare the feature vector
		List fvAnswers = new ArrayList();
		

		
		if (resultList.size() > 0) {
			fvAnswers = new ArrayList();
			for (Answer answer : resultList.get(0).getAnswerList()) {
				a0 = new Attribute("Answer-Q"+String.valueOf(answer.getAnswerNumber()));
				a1 = new Attribute("ResponseTime_300-Q"+String.valueOf(answer.getAnswerNumber());
				a2 = new Attribute("ResponseTime300_750-Q"+String.valueOf(answer.getAnswerNumber());
				a3 = new Attribute("ResponseTime750_1500-Q"+String.valueOf(answer.getAnswerNumber());
				a4 = new Attribute("ResponseTime1500_3000-Q"+String.valueOf(answer.getAnswerNumber());
				a5 = new Attribute("ResponseTime3000_-Q"+String.valueOf(answer.getAnswerNumber());
				
				fvAnswers.add(a0);
				fvAnswers.add(a1);
				fvAnswers.add(a2);
				fvAnswers.add(a3);
				fvAnswers.add(a4);
				fvAnswers.add(a5);
			}
			for (Result result : resultList) {
				Instance iAnswer = new DenseInstance(6*);
				for (Answer answer : result.getAnswerList()) {
					// Create the instance
					
					iAnswer.setValue(fvAnswers.get(0), 1.0);

					
					// Create an empty training set
					Instances trainingSet = new Instances("Answers", (ArrayList<Attribute>) fvAnswers, 10000);
					trainingSet.setClassIndex(6);
					trainingSet.add(iAnswer);

				}
				fvAnswers.add(classAttribute);
			}
		}
	}
}
