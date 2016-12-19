package com.vladimir.pma.data.dao;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vladimir.pma.data.entity.UserAccounts;

@Repository(value = "userDao")
public class UserDao implements UserDetailsService {
	
	private static final Log log = LogFactory.getLog(UserDao.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserAccounts user = (UserAccounts) sessionFactory.getCurrentSession().createCriteria(UserAccounts.class)
				.add(Restrictions.eq("username", username)).uniqueResult();
		if (null == user)
			throw new UsernameNotFoundException("The user with name " + username + " was not found");
		return user;
	}


	@Transactional
	public Integer countUsers() {
		return new Integer(((Number) sessionFactory.getCurrentSession().createCriteria(UserAccounts.class)
				.setProjection(Projections.rowCount()).uniqueResult()).intValue());
	}
	
	@Transactional
	public void deleteUserById(Integer id) {
		UserAccounts user = this.findById(id);
		sessionFactory.getCurrentSession().delete(user);
	}
	
	@Transactional
	public void persist(UserAccounts transientInstance) {
			sessionFactory.getCurrentSession().persist(transientInstance);
	}
	
	
	@Transactional
	public void saveOrUpdate(UserAccounts instance) {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
	}
	
	
	@Transactional
	public UserAccounts merge(UserAccounts detachedInstance) {
			UserAccounts result = (UserAccounts) sessionFactory.getCurrentSession().merge(detachedInstance);
			return result;
	}

	@Transactional(readOnly=true)
	public UserAccounts findById(Integer id) {
			UserAccounts instance = (UserAccounts) sessionFactory.getCurrentSession().get(UserAccounts.class, id);
			return instance;
	}


	@Transactional
	public List<UserAccounts> findAllUsers() {
		return sessionFactory.getCurrentSession().createCriteria(UserAccounts.class).list();
	}
	
	@Transactional
	public void updateUser(UserAccounts user) {
		sessionFactory.getCurrentSession().update(user);
	}

	@Transactional
	public List<String> findAllUsersByName() {
		String query = "select username from USER_ACCOUNTS";
		List<String> listUsernames = (List<String>) this.sessionFactory.getCurrentSession().createSQLQuery(query).list();
		return listUsernames;
	}

}
