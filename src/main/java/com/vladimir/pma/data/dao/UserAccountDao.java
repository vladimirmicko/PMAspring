package com.vladimir.pma.data.dao;

import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import com.vladimir.pma.data.entity.UserAccount;

@Repository
public class UserAccountDao extends BaseDao implements UserDetailsService  {
	
	private static final String USERNAME = "username"; 
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public UserAccountDao() {
		log = LogFactory.getLog(UserAccountDao.class);
		entityClass=UserAccount.class;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserAccount user = (UserAccount) sessionFactory.getCurrentSession()
				.createCriteria(UserAccount.class).add(Restrictions.eq(USERNAME, username)).uniqueResult();
		if (null == user)
			throw new UsernameNotFoundException("User not found: " + username);
		
		return user;
	}
	
}
