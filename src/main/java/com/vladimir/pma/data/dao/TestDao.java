package com.vladimir.pma.data.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vladimir.pma.data.entity.Test;

@Repository
public class TestDao {
	
	private static final Log log = LogFactory.getLog(TestDao.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	

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
		Test instance = this.findById(id);
		sessionFactory.getCurrentSession().delete(instance);
	}
	
	
	@Transactional
	public void persist(Test transientInstance) {
			sessionFactory.getCurrentSession().persist(transientInstance);
	}
	
	
	@Transactional
	public void saveOrUpdate(Test instance) {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
	}
	
	
	@Transactional
	public Test merge(Test detachedInstance) {
			return (Test) sessionFactory.getCurrentSession().merge(detachedInstance);
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
