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
import com.vladimir.pma.data.entity.Result;
import com.vladimir.pma.data.entity.Slide;
import com.vladimir.pma.data.entity.Test;

@Repository
public class AnswerDao extends BaseDao  {
	
	public AnswerDao() {
		log = LogFactory.getLog(ResultsDao.class);
		entityClass=Answer.class;
	}
	
	@Autowired
	private SessionFactory sessionFactory;
	

}
