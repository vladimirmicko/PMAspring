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
import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.SerializationHelper;
import weka.core.logging.Logger.Level;

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

	public Instances getTrainingDataset(int testId) {
		Test test = testDao.findById(testId);
		List<Result> resultList = resultDao.getAllResultsForTest(test);

		Attribute a0 = null;

		// Declare the class attribute along with its values
		List classVal = new ArrayList();
		Attribute classAttribute = new Attribute("Class");
		Instances dataSet = null;

		// Declare the feature vector
		List fvAnswers = new ArrayList();

		if (resultList.size() > 0) {
			fvAnswers = new ArrayList();
			for (Answer answer : resultList.get(0).getAnswerList()) {
				a0 = new Attribute("Answer-" + String.valueOf(answer.getAnswerNumber()));
				fvAnswers.add(a0);
			}

			fvAnswers.add(classAttribute);
			dataSet = new Instances("Answers", (ArrayList<Attribute>) fvAnswers, resultList.size());
			dataSet.setClassIndex(fvAnswers.size() - 1);

			Instance iAnswer=null;
			for (Result result : resultList) {
				if (result.getSupervisedValue() != null) {
					iAnswer = new DenseInstance(fvAnswers.size());
					int index = 0;
					for (Answer answer : result.getAnswerList()) {
						iAnswer.setValue(index, answer.getAnswerValue());
						index++;
					}
					int supervisedValue = ((result.getSupervisedValue()) ? 1 : 0);
					iAnswer.setValue(index, supervisedValue);
					dataSet.add(iAnswer);
				}
			}
			return dataSet;
		}
		return null;
	}

	public Instances getTestingDataset(int testId) {
		Test test = testDao.findById(testId);
		List<Result> resultList = resultDao.getAllResultsForTest(test);

		Attribute a0 = null;

		// Declare the class attribute along with its values
		List classVal = new ArrayList();
		Attribute classAttribute = new Attribute("Class");
		Instances dataSet = null;

		// Declare the feature vector
		List fvAnswers = new ArrayList();

		if (resultList.size() > 0) {
			fvAnswers = new ArrayList();
			for (Answer answer : resultList.get(0).getAnswerList()) {
				a0 = new Attribute("Answer-" + String.valueOf(answer.getAnswerNumber()));
				fvAnswers.add(a0);
			}

			fvAnswers.add(classAttribute);
			dataSet = new Instances("Answers", (ArrayList<Attribute>) fvAnswers, resultList.size());
			dataSet.setClassIndex(fvAnswers.size() - 1);

			Instance instance=null;
			for (Result result : resultList) {
				if (result.getEvaluation() != null) {
					instance = new DenseInstance(fvAnswers.size());
					int index = 0;
					for (Answer answer : result.getAnswerList()) {
						instance.setValue(index, answer.getAnswerValue());
						index++;
					}
					int evaluation = result.getEvaluation();
					instance.setValue(index, evaluation);
					dataSet.add(instance);
				}
			}
			return dataSet;
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
	
	
    public void saveModel(Classifier model, String modelpath) {

        try {
            SerializationHelper.write(modelpath, model);
        } catch (Exception ex) {
        	ex.printStackTrace();
        }
    }
	
    
    public double classifiy(Classifier classifier, Instance instance) {
    	double prediction = 0;
		try {
			prediction = classifier.classifyInstance(instance);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return prediction;
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
