package com.isssr5.controllers;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.isssr5.entities.ServiceUser;
import com.isssr5.exceptions.ExistentUserException;
import com.isssr5.exceptions.NotExistingUserException;
import com.isssr5.exceptions.NullUPswUserException;
import com.isssr5.exceptions.NullUserException;
import com.isssr5.service.ServiceUserTransaction;

@Controller
@RequestMapping("/user")
public class UserController {
	private ServiceUserTransaction serviceUserTransaction;
	
	@Autowired
	public UserController(ServiceUserTransaction serviceUserTransaction) {
		this.serviceUserTransaction = serviceUserTransaction;

	}

	@RequestMapping(value = "/insertUser", method = RequestMethod.POST)
	public @ResponseBody
	String createUser(@RequestBody ServiceUser user, HttpServletResponse response) throws NullUPswUserException, NullUserException, ExistentUserException {
if(user.getPsw()==null)
	throw new NullUPswUserException();
if(user.getUserid()==null)
	throw new NullUserException();
if(serviceUserTransaction.getUserById(user.getUserid())!=null)
	throw new ExistentUserException();

		serviceUserTransaction.addUser(user);
		response.setHeader("Location",  "/user/getUserById/" + user.getUserid()
				);


		return user.getUserid();

	}
	
	@RequestMapping(value = "/getUserById/{user}", method = RequestMethod.GET)
	public @ResponseBody
	ServiceUser getUserById(@PathVariable String user)
			throws NotExistingUserException {
		ServiceUser u=serviceUserTransaction.getUserById(user);
		if(u==null)
			throw new NotExistingUserException();
		return u;
	}

}
