package com.vladimir.pma.features.ai;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.vladimir.pma.features.statistics.StatisticsService;

import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instances;
import weka.experiment.InstanceQuery;

@Service
public class AIService {

	private static final Log log = LogFactory.getLog(AIService.class);
	
	public void getInstancesFromDB(){

		try {
			InstanceQuery query = new InstanceQuery();
			
			 query.setUsername("VLADIMIR");
			 query.setPassword("VLADIMIR");
			 query.setQuery("select * from results");
			 
			 // You can declare that your data set is sparse
			 // query.setSparseData(true);
			 
			 Instances data = query.retrieveInstances();
		//	
//			// Declare two numeric attributes
//			Attribute Attribute1 = new Attribute("firstNumeric");
//			Attribute Attribute2 = new Attribute("secondNumeric");
		//	
//			// Declare a nominal attribute along with its values
//			FastVector fvNominalVal = new FastVector(3);
//			fvNominalVal.addElement("blue");
//			fvNominalVal.addElement("gray");
//			fvNominalVal.addElement("black");
//			Attribute Attribute3 = new Attribute("aNominal", fvNominalVal);
		//	
//			// Declare the class attribute along with its values
//			FastVector fvClassVal = new FastVector(2);
//			fvClassVal.addElement("positive");
//			fvClassVal.addElement("negative");
//			Attribute ClassAttribute = new Attribute("theClass", fvClassVal);
		//	
//			// Declare the feature vector
//			FastVector fvWekaAttributes = new FastVector(4);
//			fvWekaAttributes.addElement(Attribute1);
//			fvWekaAttributes.addElement(Attribute2);
//			fvWekaAttributes.addElement(Attribute3);
//			fvWekaAttributes.addElement(ClassAttribute);
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	




	
	
	
}
