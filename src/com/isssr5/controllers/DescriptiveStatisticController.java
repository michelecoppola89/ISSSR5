package com.isssr5.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.isssr5.entities.EnumerateDomain;
import com.isssr5.entities.MacroService;
import com.isssr5.entities.Operand;
import com.isssr5.entities.Result;
import com.isssr5.entities.ResultValue;
import com.isssr5.entities.ServiceUser;
import com.isssr5.exceptions.NotExistingOperandException;
import com.isssr5.exceptions.NotExistingUserException;
import com.isssr5.exceptions.WrongScaleForMacroServiceId;
import com.isssr5.service.OperandTransaction;
import com.isssr5.service.ServiceUserTransaction;

@Controller
@RequestMapping("/descriptiveStatistic")
public class DescriptiveStatisticController {

	private static OperandTransaction operandTransaction;
	private static ServiceUserTransaction serviceUserTransaction;

	@Autowired
	public DescriptiveStatisticController(
			OperandTransaction operandTransaction,
			ServiceUserTransaction servireUserTransaction) {
		DescriptiveStatisticController.operandTransaction = operandTransaction;
		DescriptiveStatisticController.serviceUserTransaction = servireUserTransaction;

	}

	public static Result get_mean(String user, int id1)
			throws NotExistingUserException, NotExistingOperandException,
			WrongScaleForMacroServiceId {

		ServiceUser u = serviceUserTransaction.getUserById(user);
		if (u == null)
			throw new NotExistingUserException();

		Operand op = operandTransaction.findOperandById(id1);

		if (op == null)
			throw new NotExistingOperandException();
		if (!op.getUser().getUserid().equals(user))
			throw new NotExistingOperandException();
		if (op.getScale().getDom() instanceof EnumerateDomain)
			throw new WrongScaleForMacroServiceId();

		List<Long> list_id = new ArrayList<Long>();
		list_id.add(new Long(id1));
		Result res = new Result(null, list_id, null);
		List<ResultValue> listVal = new ArrayList<ResultValue>();

		ResultValue rv = new ResultValue();

		rv.setOperand("mean");
		rv.setValue(String.valueOf(MacroService.compute_mean(op)));

		listVal.add(rv);

		res.setResultValueList(listVal);

		return res;

	}

	@RequestMapping(value = "/{user}/mean/{id1}", method = RequestMethod.GET)
	public @ResponseBody
	Result mean(@PathVariable String user, @PathVariable int id1)
			throws NotExistingUserException, NotExistingOperandException,
			WrongScaleForMacroServiceId {

		return get_mean(user, id1);

	}

	public static Result get_variance(String user, int id1)
			throws NotExistingUserException, NotExistingOperandException,
			WrongScaleForMacroServiceId {

		ServiceUser u = serviceUserTransaction.getUserById(user);
		if (u == null)
			throw new NotExistingUserException();

		Operand op = operandTransaction.findOperandById(id1);

		if (op == null)
			throw new NotExistingOperandException();
		if (!op.getUser().getUserid().equals(user))
			throw new NotExistingOperandException();
		if (op.getScale().getDom() instanceof EnumerateDomain)
			throw new WrongScaleForMacroServiceId();

		List<Long> list_id = new ArrayList<Long>();
		list_id.add(new Long(id1));

		Result res = new Result(null, list_id, null);
		List<ResultValue> listVal = new ArrayList<ResultValue>();

		ResultValue rv = new ResultValue();

		rv.setOperand("variance");
		rv.setValue(String.valueOf(MacroService.compute_variance(op)));

		listVal.add(rv);

		res.setResultValueList(listVal);

		return res;

	}

	@RequestMapping(value = "/{user}/variance/{id1}", method = RequestMethod.GET)
	public @ResponseBody
	Result variance(@PathVariable String user, @PathVariable int id1)
			throws NotExistingUserException, NotExistingOperandException,
			WrongScaleForMacroServiceId {

		return get_variance(user, id1);

	}

	public static Result get_geometricMean(String user, int id1)
			throws NotExistingUserException, NotExistingOperandException,
			WrongScaleForMacroServiceId {

		ServiceUser u = serviceUserTransaction.getUserById(user);
		if (u == null)
			throw new NotExistingUserException();

		Operand op = operandTransaction.findOperandById(id1);

		if (op == null)
			throw new NotExistingOperandException();
		if (!op.getUser().getUserid().equals(user))
			throw new NotExistingOperandException();
		if (op.getScale().getDom() instanceof EnumerateDomain)
			throw new WrongScaleForMacroServiceId();

		List<Long> list_id = new ArrayList<Long>();
		list_id.add(new Long(id1));

		Result res = new Result(null, list_id, null);
		List<ResultValue> listVal = new ArrayList<ResultValue>();

		ResultValue rv = new ResultValue();

		rv.setOperand("geometricMean");
		rv.setValue(String.valueOf(MacroService.compute_geometricMean(op)));

		listVal.add(rv);

		res.setResultValueList(listVal);

		return res;

	}

	@RequestMapping(value = "/{user}/geometricMean/{id1}", method = RequestMethod.GET)
	public @ResponseBody
	Result geometricMean(@PathVariable String user, @PathVariable int id1)
			throws NotExistingUserException, NotExistingOperandException,
			WrongScaleForMacroServiceId {

		return get_geometricMean(user, id1);

	}

	public static Result get_minValue(String user, int id1)
			throws NotExistingOperandException, NotExistingUserException,
			WrongScaleForMacroServiceId {

		ServiceUser u = serviceUserTransaction.getUserById(user);
		if (u == null)
			throw new NotExistingUserException();

		Operand op = operandTransaction.findOperandById(id1);

		if (op == null)
			throw new NotExistingOperandException();
		if (!op.getUser().getUserid().equals(user))
			throw new NotExistingOperandException();
		if (op.getScale().getDom() instanceof EnumerateDomain)
			throw new WrongScaleForMacroServiceId();

		List<Long> list_id = new ArrayList<Long>();
		list_id.add(new Long(id1));

		Result res = new Result(null, list_id, null);
		List<ResultValue> listVal = new ArrayList<ResultValue>();

		ResultValue rv = new ResultValue();

		rv.setOperand("min");
		rv.setValue(String.valueOf(MacroService.compute_minValue(op)));

		listVal.add(rv);

		res.setResultValueList(listVal);

		return res;

	}

	@RequestMapping(value = "/{user}/min/{id1}", method = RequestMethod.GET)
	public @ResponseBody
	Result minValue(@PathVariable String user, @PathVariable int id1)
			throws NotExistingOperandException, NotExistingUserException,
			WrongScaleForMacroServiceId {

		return get_minValue(user, id1);

	}

	public static Result get_maxValue(String user, int id1)
			throws NotExistingUserException, NotExistingOperandException,
			WrongScaleForMacroServiceId {

		ServiceUser u = serviceUserTransaction.getUserById(user);
		if (u == null)
			throw new NotExistingUserException();

		Operand op = operandTransaction.findOperandById(id1);

		if (op == null)
			throw new NotExistingOperandException();
		if (!op.getUser().getUserid().equals(user))
			throw new NotExistingOperandException();
		if (op.getScale().getDom() instanceof EnumerateDomain)
			throw new WrongScaleForMacroServiceId();

		List<Long> list_id = new ArrayList<Long>();
		list_id.add(new Long(id1));

		Result res = new Result(null, list_id, null);
		List<ResultValue> listVal = new ArrayList<ResultValue>();

		ResultValue rv = new ResultValue();

		rv.setOperand("max");
		rv.setValue(String.valueOf(MacroService.compute_maxValue(op)));

		listVal.add(rv);

		res.setResultValueList(listVal);

		return res;

	}

	@RequestMapping(value = "/{user}/max/{id1}", method = RequestMethod.GET)
	public @ResponseBody
	Result maxValue(@PathVariable String user, @PathVariable int id1)
			throws NotExistingUserException, NotExistingOperandException,
			WrongScaleForMacroServiceId {

		return get_maxValue(user, id1);

	}

	public static Result get_standardDeviation(String user, int id1)
			throws NotExistingUserException, NotExistingOperandException,
			WrongScaleForMacroServiceId {

		ServiceUser u = serviceUserTransaction.getUserById(user);
		if (u == null)
			throw new NotExistingUserException();

		Operand op = operandTransaction.findOperandById(id1);

		if (op == null)
			throw new NotExistingOperandException();
		if (!op.getUser().getUserid().equals(user))
			throw new NotExistingOperandException();
		if (op.getScale().getDom() instanceof EnumerateDomain)
			throw new WrongScaleForMacroServiceId();

		List<Long> list_id = new ArrayList<Long>();
		list_id.add(new Long(id1));

		Result res = new Result(null, list_id, null);
		List<ResultValue> listVal = new ArrayList<ResultValue>();

		ResultValue rv = new ResultValue();

		rv.setOperand("stdDeviation");
		rv.setValue(String.valueOf(MacroService.compute_standardDeviation(op)));

		listVal.add(rv);

		res.setResultValueList(listVal);

		return res;

	}

	@RequestMapping(value = "/{user}/stdDeviation/{id1}", method = RequestMethod.GET)
	public @ResponseBody
	Result standardDeviation(@PathVariable String user, @PathVariable int id1)
			throws NotExistingUserException, NotExistingOperandException,
			WrongScaleForMacroServiceId {

		return get_standardDeviation(user, id1);

	}

	public static Result get_median(String user, int id1)
			throws NotExistingUserException, NotExistingOperandException,
			WrongScaleForMacroServiceId {

		ServiceUser u = serviceUserTransaction.getUserById(user);
		if (u == null)
			throw new NotExistingUserException();

		Operand op = operandTransaction.findOperandById(id1);

		if (op == null)
			throw new NotExistingOperandException();
		if (!op.getUser().getUserid().equals(user))
			throw new NotExistingOperandException();
		if (op.getScale().getDom() instanceof EnumerateDomain)
			throw new WrongScaleForMacroServiceId();

		List<Long> list_id = new ArrayList<Long>();
		list_id.add(new Long(id1));

		Result res = new Result(null, list_id, null);
		List<ResultValue> listVal = new ArrayList<ResultValue>();

		ResultValue rv = new ResultValue();

		rv.setOperand("median");
		rv.setValue(String.valueOf(MacroService.compute_median(op)));

		listVal.add(rv);

		res.setResultValueList(listVal);

		return res;

	}

	@RequestMapping(value = "/{user}/median/{id1}", method = RequestMethod.GET)
	public @ResponseBody
	Result median(@PathVariable String user, @PathVariable int id1)
			throws NotExistingUserException, NotExistingOperandException,
			WrongScaleForMacroServiceId {

		return get_median(user, id1);
	}
	
	
	
	public static Result get_frequency(String user, int id1)
			throws NotExistingUserException, NotExistingOperandException,
			WrongScaleForMacroServiceId {

		ServiceUser u = serviceUserTransaction.getUserById(user);
		if (u == null)
			throw new NotExistingUserException();

		Operand op = operandTransaction.findOperandById(id1);

		if (op == null)
			throw new NotExistingOperandException();
		if (!op.getUser().getUserid().equals(user))
			throw new NotExistingOperandException();
		if (!(op.getScale().getDom() instanceof EnumerateDomain))
			throw new WrongScaleForMacroServiceId();

		List<Long> list_id = new ArrayList<Long>();
		list_id.add(new Long(id1));

		Result res = new Result(null, list_id, null);
		List<ResultValue> listVal = new ArrayList<ResultValue>();

		ResultValue rv = new ResultValue();

		rv.setOperand("frequency");
		rv.setValue(MacroService.compute_frequency(op));

		listVal.add(rv);

		res.setResultValueList(listVal);

		return res;

	}

	@RequestMapping(value = "/{user}/frequency/{id1}", method = RequestMethod.GET)
	public @ResponseBody
	Result frequency(@PathVariable String user, @PathVariable int id1)
			throws NotExistingUserException, NotExistingOperandException,
			WrongScaleForMacroServiceId {

		return get_frequency(user, id1);
	}

	
	
	
	
	
	

}
