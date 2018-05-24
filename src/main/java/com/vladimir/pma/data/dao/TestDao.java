package com.vladimir.pma.data.dao;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vladimir.pma.data.entity.Answer;
import com.vladimir.pma.data.entity.Slide;
import com.vladimir.pma.data.entity.Test;

@Repository
public class TestDao extends BaseDao  {
		
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private SlideDao slideDao;
	
	public TestDao() {
		log = LogFactory.getLog(TestDao.class);
		entityClass=Test.class;
	}

	@Transactional(readOnly=true)
	public Test findById(Integer id) {
			return sessionFactory.getCurrentSession().get(Test.class, id);
	}
	
	
	@Transactional(readOnly=true)
	public Integer count() {
		return new Integer(((Number) sessionFactory.getCurrentSession().createCriteria(Test.class)
				.setProjection(Projections.rowCount()).uniqueResult()).intValue());
	}
	
	@Transactional
	public void deleteById(Integer id) {
		Test test = this.findById(id);
		for(Slide slide : test.getSlideList()){
			slideDao.deleteById(slide.getId());
		}
		sessionFactory.getCurrentSession().delete(test);
	}
	
	
	@Transactional
	public void persist(Test transientInstance) {
		transientInstance.setCreationDate(new Date());
			sessionFactory.getCurrentSession().persist(transientInstance);
	}
	
	
	@Transactional
	public void saveOrUpdate(Test instance) {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
	}
	
	
	@Transactional
	public Test merge(Test receivedTest) {
		Test test;
		if (receivedTest.getId() != null){
			test = findById(receivedTest.getId());
			test.setCreationDate(receivedTest.getCreationDate());
			test.setDescription(receivedTest.getDescription());
			test.setTestName(receivedTest.getTestName());
			test.setResultYesDescription(receivedTest.getResultYesDescription());
			test.setResultNoDescription(receivedTest.getResultNoDescription());
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
	
	
	@Transactional(readOnly=true)
	public List<Test> findAll() {
		return sessionFactory.getCurrentSession().createCriteria(Test.class).list();
	}
	
	
	@Transactional
	public void update(Test instance) {
		sessionFactory.getCurrentSession().update(instance);
	}

}
