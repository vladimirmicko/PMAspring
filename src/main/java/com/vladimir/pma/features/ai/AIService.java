package com.vladimir.pma.features.ai;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.vladimir.pma.features.statistics.StatisticsService;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;
import weka.experiment.InstanceQuery;

@Service
public class AIService {

	private static final Log log = LogFactory.getLog(AIService.class);

	public void getInstancesFromDB(){
			
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
			iExample.setValue((Attribute)fvWekaAttributes.get(0), 1.0);
			iExample.setValue((Attribute)fvWekaAttributes.get(1), 0.5);
			iExample.setValue((Attribute)fvWekaAttributes.get(2), "gray");
			iExample.setValue((Attribute)fvWekaAttributes.get(3), "positive");
			
			// add the instance
			isTrainingSet.add(iExample);
			
			// Create a naïve bayes classifier
			Classifier cModel = (Classifier)new NaiveBayes();
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
			iExampleTest.setValue((Attribute)fvWekaAttributes.get(0), 1.0);
			iExampleTest.setValue((Attribute)fvWekaAttributes.get(1), 0.5);
			iExampleTest.setValue((Attribute)fvWekaAttributes.get(2), "gray");
			iExampleTest.setValue((Attribute)fvWekaAttributes.get(3), "positive");
			
			
			// Test the model
			Evaluation eTest=null;
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

}
