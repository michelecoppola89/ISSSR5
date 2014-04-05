package com.isssr5.persistence;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.isssr5.entities.ServiceUser;
@Repository
public class UserDaoImplementation implements UserDao {
	private SessionFactory sessionFactory;
	
	@Autowired
	public UserDaoImplementation(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addUser(ServiceUser user) {
		sessionFactory.getCurrentSession().save(user);
	}

	@Override
	public void updateUser(ServiceUser user) {
		sessionFactory.getCurrentSession().update(user);

	}

	@Override
	public void deleteUser(ServiceUser user) {
		sessionFactory.getCurrentSession().delete(user);

	}

	@Override
	public ServiceUser getUserById(String id) {
		return (ServiceUser)sessionFactory.getCurrentSession().get(ServiceUser.class,id);
		 

	}

}
