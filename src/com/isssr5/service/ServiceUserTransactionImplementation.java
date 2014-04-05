package com.isssr5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.isssr5.entities.ServiceUser;
import com.isssr5.persistence.UserDao;

@Service
public class ServiceUserTransactionImplementation implements
		ServiceUserTransaction {
	
	@Autowired
	private UserDao userDao;

	@Override
	@Transactional
	public void addUser(ServiceUser user) {
		userDao.addUser(user);

	}

	@Override
	@Transactional
	public void updateUser(ServiceUser user) {
		userDao.updateUser(user);

	}

	@Override
	@Transactional
	public void deleteUser(ServiceUser user) {
		userDao.deleteUser(user);

	}

	@Override
	@Transactional
	public ServiceUser getUserById(String id) {
		return userDao.getUserById(id);
	}

}
