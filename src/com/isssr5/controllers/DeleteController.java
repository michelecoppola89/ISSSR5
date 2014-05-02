package com.isssr5.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.isssr5.entities.ServiceUser;
import com.isssr5.exceptions.NotExistingUserException;
import com.isssr5.service.ScaleTransaction;
import com.isssr5.service.ServiceUserTransaction;


@Controller
@RequestMapping("/delete")
public class DeleteController {

	private ScaleTransaction scaleTransaction;
	private ServiceUserTransaction serviceUserTransaction;

	@Autowired
	public DeleteController(ScaleTransaction scaleTransaction,
			ServiceUserTransaction serviceUserTransaction) {
		this.scaleTransaction = scaleTransaction;
		this.serviceUserTransaction = serviceUserTransaction;
	}

	
	@RequestMapping(value = "/{user}/temporaryFiles", method = RequestMethod.DELETE)
	public @ResponseBody
	void deleteScales(@PathVariable String user) throws NotExistingUserException {
		ServiceUser u = serviceUserTransaction.getUserById(user);
		if(u==null)
			throw new NotExistingUserException();
		for(int i=0; i<u.getScaleList().size();i++){
			scaleTransaction.deleteScale(u.getScaleList().get(i));
		}

	}
	
	
	
}
