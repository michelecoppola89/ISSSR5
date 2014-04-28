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
import com.isssr5.exceptions.WrongDistributionException;
import com.isssr5.service.OperandTransaction;
import com.isssr5.service.ServiceUserTransaction;

@Controller
@RequestMapping("/NonParametricTest")
public class Kolmogorov_ConfidenceIntervalController {
	private static ServiceUserTransaction serviceUserTransaction;
	private static OperandTransaction operandTransaction;
	
	@Autowired
	public Kolmogorov_ConfidenceIntervalController(ServiceUserTransaction str,OperandTransaction otr)
	{
		Kolmogorov_ConfidenceIntervalController.serviceUserTransaction=str;
		Kolmogorov_ConfidenceIntervalController.operandTransaction=otr;
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
		Result result = new Result(null, operands, resultValues);

		return result;

	}

	@RequestMapping(value = "/1SampleKolmogrov/{user}/{opId}/{distribution}", method = RequestMethod.GET)
	public @ResponseBody
	Result getOneSampleKolmogrovUrl(@PathVariable String user,
			@PathVariable long opId, @PathVariable String distribution)
			throws WrongDistributionException, NotExistingUserException,
			NotExistingOperandException {
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
		if (distribution.equals("lognormal") || distribution.equals("uniform")
				|| distribution.equals("normal")) {

			operands.add(op.getId());

			ResultValue rv = new ResultValue("1SampleKolmogrov",
					Double.toString(MacroService.kolmogorovSmirnovStatistic(
							distribution, op)));
			resultValues.add(rv);
		} else
			throw new WrongDistributionException();
		Result result = new Result(null, operands, resultValues);

		return result;

	}

	public static Result getOneSampleKolmogrov(String user, long opId,
			String distribution) throws WrongDistributionException,
			NotExistingUserException, NotExistingOperandException {
		ServiceUser u = serviceUserTransaction.getUserById(user);
		if (u == null)
			throw new NotExistingUserException();
		Operand op = operandTransaction.findOperandById(opId);
		if (op == null)
			throw new NotExistingOperandException();
		if (!op.getUser().getUserid().equals(user))
			throw new NotExistingOperandException();
		List<Long> operands = new ArrayList<Long>();

		List<ResultValue> resultValues = new ArrayList<ResultValue>();
		if (distribution.equals("lognormal") || distribution.equals("uniform")
				|| distribution.equals("normal")) {

			operands.add(op.getId());

			ResultValue rv = new ResultValue("1SampleKolmogrov",
					Double.toString(MacroService.kolmogorovSmirnovStatistic(
							distribution, op)));
			resultValues.add(rv);
		} else
			throw new WrongDistributionException();
		Result result = new Result(null, operands, resultValues);

		return result;

	}

	@RequestMapping(value = "/2SampleKolmogrov/{user}/{op1Id}/{op2Id}", method = RequestMethod.GET)
	public @ResponseBody
	Result getTwoSampleKolmogrovUrl(@PathVariable String user,
			@PathVariable long op1Id, @PathVariable long op2Id) throws NotExistingUserException,
			NotExistingOperandException {
		ServiceUser u = serviceUserTransaction.getUserById(user);
		if (u == null)
			throw new NotExistingUserException();
		Operand op1 = operandTransaction.findOperandById(op1Id);
		if (op1 == null)
			throw new NotExistingOperandException();
		if (!op1.getUser().getUserid().equals(user))
			throw new NotExistingOperandException();
		Operand op2 = operandTransaction.findOperandById(op2Id);
		if (op2 == null)
			throw new NotExistingOperandException();
		if (!op2.getUser().getUserid().equals(user))
			throw new NotExistingOperandException();
		List<Long> operands = new ArrayList<Long>();
		operands.add(op1Id);
		operands.add(op2Id);
		List<ResultValue> resultValues = new ArrayList<ResultValue>();
		ResultValue rv = new ResultValue(null, Double.toString(MacroService
				.kolmogorovSmirnovStatistic(op1, op2)));
		resultValues.add(rv);
		Result result = new Result(null, operands, resultValues);

		return result;
	}

	public @ResponseBody
	static Result getTwoSampleKolmogrov(String user, long op1Id, long op2Id) throws NotExistingUserException,
			NotExistingOperandException {
		ServiceUser u = serviceUserTransaction.getUserById(user);
		if (u == null)
			throw new NotExistingUserException();
		Operand op1 = operandTransaction.findOperandById(op1Id);
		if (op1 == null)
			throw new NotExistingOperandException();
		if (!op1.getUser().getUserid().equals(user))
			throw new NotExistingOperandException();
		Operand op2 = operandTransaction.findOperandById(op2Id);
		if (op2 == null)
			throw new NotExistingOperandException();
		if (!op2.getUser().getUserid().equals(user))
			throw new NotExistingOperandException();
		List<Long> operands = new ArrayList<Long>();
		operands.add(op1Id);
		operands.add(op2Id);
		List<ResultValue> resultValues = new ArrayList<ResultValue>();
		ResultValue rv = new ResultValue(null, Double.toString(MacroService
				.kolmogorovSmirnovStatistic(op1, op2)));
		resultValues.add(rv);
		Result result = new Result(null, operands, resultValues);

		return result;
	}

}
