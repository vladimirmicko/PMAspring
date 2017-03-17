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

import com.vladimir.pma.data.entity.Slide;
import com.vladimir.pma.data.entity.Test;

@Repository
public class SlideDao {
	
	private static final Log log = LogFactory.getLog(SlideDao.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private TestDao testDao;
	

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
	public void merge(Slide slide, int id) {
		Test test = testDao.findById(id);
		slide.setTest(test);
		if (slide.getId() != null){
			sessionFactory.getCurrentSession().merge(slide);
		}
		else{
			sessionFactory.getCurrentSession().saveOrUpdate(slide);
		}
	}


	@Transactional(readOnly=true)
	public List<Slide> findAll() {
		return sessionFactory.getCurrentSession().createCriteria(Slide.class).list();
	}
	
}
