package com.vladimir.pma.data.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vladimir.pma.data.entity.Proba;

@Repository
public class ProbaDao {
	
	private static final Log log = LogFactory.getLog(ProbaDao.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	

	@Transactional(readOnly=true)
	public Proba findById(Integer id) {
			Proba instance = sessionFactory.getCurrentSession().get(Proba.class, id);
			return instance;
	}

}
