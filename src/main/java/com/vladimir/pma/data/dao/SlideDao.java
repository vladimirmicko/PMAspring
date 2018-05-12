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
public class SlideDao extends BaseDao  {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private TestDao testDao;
	
	public SlideDao() {
		log = LogFactory.getLog(ResultsDao.class);
		entityClass=Slide.class;
	}
	

	@Transactional(readOnly=true)
	public Slide findById(Integer id) {
			return sessionFactory.getCurrentSession().get(Slide.class, id);
	}
	
	
	@Transactional(readOnly=true)
	public Integer count() {
		return new Integer(((Number) sessionFactory.getCurrentSession().createCriteria(Slide.class)
				.setProjection(Projections.rowCount()).uniqueResult()).intValue());
	}
	
	@Transactional
	public void deleteById(Integer id) {
		Slide instance = this.findById(id);
		sessionFactory.getCurrentSession().delete(instance);
	}
	
	
	@Transactional
	public void persist(Slide transientInstance) {
			sessionFactory.getCurrentSession().persist(transientInstance);
	}
	
	
	@Transactional
	public void saveOrUpdate(Slide instance) {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
	}
	
	
	@Transactional
	public void merge(Slide receivedSlide, int id) {
		Slide slide;
		Test test = testDao.findById(id);
		receivedSlide.setTest(test);
		if (receivedSlide.getId() != null){
			slide = findById(receivedSlide.getId());
            slide.setDelay(receivedSlide.getDelay());
            slide.setSlideName(receivedSlide.getSlideName());
            if(receivedSlide.getPrimingImage()!=null && receivedSlide.getPrimingImage().length > 0){
                slide.setPrimingImage(receivedSlide.getPrimingImage());
            }
            if(receivedSlide.getTestImage()!=null && receivedSlide.getTestImage().length > 0){
                slide.setTestImage(receivedSlide.getTestImage());
            }
            sessionFactory.getCurrentSession().merge(slide);
		}
		else{
			sessionFactory.getCurrentSession().saveOrUpdate(receivedSlide);
		}
	}


	@Transactional(readOnly=true)
	public List<Slide> findAll() {
		return sessionFactory.getCurrentSession().createCriteria(Slide.class).list();
	}
	
}
