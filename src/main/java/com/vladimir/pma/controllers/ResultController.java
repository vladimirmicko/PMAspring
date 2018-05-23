package com.vladimir.pma.controllers;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.vladimir.pma.data.dao.ResultDao;
import com.vladimir.pma.data.dao.TestDao;
import com.vladimir.pma.data.dao.UserAccountDao;
import com.vladimir.pma.data.entity.Result;

@RestController
@RequestMapping("/rest/results")
public class ResultController {
	private static final Log log = LogFactory.getLog(ResultController.class);

	@Autowired
	private ResultDao resultDao;

	

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

}
