package com.isssr5.persistence;


import com.isssr5.entities.ServiceUser;

public interface UserDao {
	public void addUser(ServiceUser user);
	public void updateUser(ServiceUser user);
	public void deleteUser(ServiceUser user);
	public ServiceUser getUserById(String id);
	
	
	
	

}
