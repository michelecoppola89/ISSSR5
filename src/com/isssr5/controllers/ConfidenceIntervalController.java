package com.isssr5.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.isssr5.entities.MacroService;
import com.isssr5.entities.Operand;
import com.isssr5.entities.Result;
import com.isssr5.entities.ResultValue;
import com.isssr5.entities.ServiceUser;
import com.isssr5.exceptions.NotExistingOperandException;
import com.isssr5.exceptions.NotExistingUserException;
import com.isssr5.service.OperandTransaction;
import com.isssr5.service.ServiceUserTransaction;

@Controller
@RequestMapping("/ConfidenceIntervalOperation")
public class ConfidenceIntervalController {
	private static ServiceUserTransaction serviceUserTransaction;
	private static OperandTransaction operandTransaction;
	
	@Autowired
	public ConfidenceIntervalController(ServiceUserTransaction str,OperandTransaction otr)
	{
		ConfidenceIntervalController.serviceUserTransaction=str;
		ConfidenceIntervalController.operandTransaction=otr;
	}
	
	@RequestMapping(value = "/ConfidenceInterval/{user}/{opId}/{level}", method = RequestMethod.GET)
	public @ResponseBody
	Result getConfidenceIntervalUrl(@PathVariable String user,
			@PathVariable long opId, @PathVariable double level)
			throws NotExistingUserException, NotExistingOperandException {
		return getConfidenceInterval(user, opId, level);

	}

	public static Result getConfidenceInterval(String user, long opId, double level)
			throws NotExistingUserException, NotExistingOperandException {
		ServiceUser u = serviceUserTransaction.getUserById(user);
		if (u == null)
			throw new NotExistingUserException();
		Operand op = operandTransaction.findOperandById(opId);
		if (op == null)
			throw new NotExistingOperandException();
		if (!op.getUser().getUserid().equals(user))
			throw new NotExistingOperandException();
		List<Long> operands = new ArrayList<Long>();
		operands.add(opId);
		List<ResultValue> resultValues = new ArrayList<ResultValue>();
		ResultValue rv = new ResultValue("ConfidenceInterval",
				Double.toString(MacroService.Confidence_Interval(op, level)));
		resultValues.add(rv);
		Result result = new Result(operands, resultValues);

		return result;

	}


}
