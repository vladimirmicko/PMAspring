package com.vladimir.pma.data.dao;

import java.util.List;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vladimir.pma.data.entity.Result;
import com.vladimir.pma.data.entity.Test;

@Repository
public class StatisticsDao extends BaseDao {

	public StatisticsDao() {
		log = LogFactory.getLog(StatisticsDao.class);
		entityClass = Result.class;
	}

	@Autowired
	private SessionFactory sessionFactory;


	@Transactional(readOnly = true)
	public List<Result> getAllResultsForTest(Test test) {
		return (List<Result>) sessionFactory.getCurrentSession().createCriteria(Result.class).add(Restrictions.eq("test", test)).list();
	}

}
