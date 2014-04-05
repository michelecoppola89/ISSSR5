package com.isssr5.service;

import com.isssr5.entities.ServiceUser;

public interface ServiceUserTransaction {
	public void addUser(ServiceUser user);
	public void updateUser(ServiceUser user);
	public void deleteUser(ServiceUser user);
	public ServiceUser getUserById(String id);
	
	
	
	

}

