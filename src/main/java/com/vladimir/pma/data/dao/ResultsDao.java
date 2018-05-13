package com.vladimir.pma.data.dao;

import java.util.Date;
import java.util.List;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.vladimir.pma.data.entity.Answer;
import com.vladimir.pma.data.entity.Result;
import com.vladimir.pma.data.entity.Test;

@Repository
public class ResultsDao extends BaseDao {
	
	public ResultsDao() {
		log = LogFactory.getLog(ResultsDao.class);
		entityClass=Result.class;
	}
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private AnswerDao answerDao;
	

	
	@Transactional
	public void deleteById(Integer id) {
		Result result = this.findById(id);
		for(Answer answer : result.getAnswerList()){
			answerDao.delete(answer.getId());
		}
		delete(result);
	}
	
	
	@Transactional
	public Test merge(Test receivedTest) {
		Test test;
		if (receivedTest.getId() != null){
			test = findById(receivedTest.getId());
			test.setCreationDate(receivedTest.getCreationDate());
			test.setDescription(receivedTest.getDescription());
			test.setTestName(receivedTest.getTestName());
			if(receivedTest.getTestPromoImage()!=null && receivedTest.getTestPromoImage().length > 0){
				test.setTestPromoImage(receivedTest.getTestPromoImage());
			}
			if(receivedTest.getSlideList()!=null && !receivedTest.getSlideList().isEmpty()){
				test.setSlideList(receivedTest.getSlideList());
			}
		}
		else{
			test=receivedTest;
			test.setCreationDate(new Date());
		}
		return (Test) sessionFactory.getCurrentSession().merge(test);
	}
	

}