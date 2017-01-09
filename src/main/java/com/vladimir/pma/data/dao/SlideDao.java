package com.vladimir.pma.data.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vladimir.pma.data.entity.Slide;

@Repository
public class SlideDao {
	
	private static final Log log = LogFactory.getLog(SlideDao.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	

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
	public Slide merge(Slide detachedInstance) {
			return (Slide) sessionFactory.getCurrentSession().merge(detachedInstance);
	}


	@Transactional(readOnly=true)
	public List<Slide> findAll() {
		return sessionFactory.getCurrentSession().createCriteria(Slide.class).list();
	}
	
	
	@Transactional
	public void update(Slide instance) {
		sessionFactory.getCurrentSession().update(instance);
	}

}
