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
import com.isssr5.service.OperandTransaction;
import com.isssr5.service.ScaleTransaction;
import com.isssr5.service.ServiceUserTransaction;

@Controller
@RequestMapping("/descriptiveStatistic")
public class DescriptiveStatisticController {

	private OperandTransaction operandTransaction;
	private ServiceUserTransaction servireUserTransaction;
	

	@Autowired
	public DescriptiveStatisticController(
			OperandTransaction operandTransaction,
			ServiceUserTransaction servireUserTransaction) {
		this.operandTransaction = operandTransaction;
		this.servireUserTransaction = servireUserTransaction;

	}

	
	@RequestMapping(value = "/{user}/mean/{id1}", method = RequestMethod.GET)
	public @ResponseBody
	Result mean(@PathVariable String user, @PathVariable int id1) {
		
		Operand op= operandTransaction.findOperandById(id1);
		
		List<Long> list_id= new ArrayList<Long>();
		list_id.add(new Long(id1));
		Result res= new Result(null, list_id, null);
		List<ResultValue> listVal = new ArrayList<ResultValue>();
		
		ResultValue rv= new ResultValue();
		
		rv.setOperand(String.valueOf(id1));
		rv.setValue(String.valueOf(MacroService.compute_mean(op)));
		
		listVal.add(rv);
		
		res.setResultValueList(listVal);

		return res;

	}
	
	
	@RequestMapping(value = "/{user}/variance/{id1}", method = RequestMethod.GET)
	public @ResponseBody
	Result variance(@PathVariable String user, @PathVariable int id1) {
		
		Operand op= operandTransaction.findOperandById(id1);
		List<Long> list_id= new ArrayList<Long>();
		list_id.add(new Long(id1));

		Result res= new Result(null, list_id, null);
		List<ResultValue> listVal = new ArrayList<ResultValue>();
		
		ResultValue rv= new ResultValue();
		
		rv.setOperand(String.valueOf(id1));
		rv.setValue(String.valueOf(MacroService.compute_variance(op)));
		
		listVal.add(rv);
		
		res.setResultValueList(listVal);

		return res;

	}
	
	
	@RequestMapping(value = "/{user}/geometricMean/{id1}", method = RequestMethod.GET)
	public @ResponseBody
	Result geometricMean(@PathVariable String user, @PathVariable int id1) {
		
		Operand op= operandTransaction.findOperandById(id1);
		
		List<Long> list_id= new ArrayList<Long>();
		list_id.add(new Long(id1));

		Result res= new Result(null, list_id, null);
		List<ResultValue> listVal = new ArrayList<ResultValue>();
		
		ResultValue rv= new ResultValue();
		
		rv.setOperand(String.valueOf(id1));
		rv.setValue(String.valueOf(MacroService.compute_geometricMean(op)));
		
		listVal.add(rv);
		
		res.setResultValueList(listVal);

		return res;

	}
	
	
	@RequestMapping(value = "/{user}/min/{id1}", method = RequestMethod.GET)
	public @ResponseBody
	Result minValue(@PathVariable String user, @PathVariable int id1) {
		
		Operand op= operandTransaction.findOperandById(id1);
		List<Long> list_id= new ArrayList<Long>();
		list_id.add(new Long(id1));

		Result res= new Result(null, list_id, null);
		List<ResultValue> listVal = new ArrayList<ResultValue>();
		
		ResultValue rv= new ResultValue();
		
		rv.setOperand(String.valueOf(id1));
		rv.setValue(String.valueOf(MacroService.compute_minValue(op)));
		
		listVal.add(rv);
		
		res.setResultValueList(listVal);

		return res;

	}
	
	
	@RequestMapping(value = "/{user}/max/{id1}", method = RequestMethod.GET)
	public @ResponseBody
	Result maxValue(@PathVariable String user, @PathVariable int id1) {
		
		Operand op= operandTransaction.findOperandById(id1);
		
		List<Long> list_id= new ArrayList<Long>();
		list_id.add(new Long(id1));

		Result res= new Result(null, list_id, null);
		List<ResultValue> listVal = new ArrayList<ResultValue>();
		
		ResultValue rv= new ResultValue();
		
		rv.setOperand(String.valueOf(id1));
		rv.setValue(String.valueOf(MacroService.compute_maxValue(op)));
		
		listVal.add(rv);
		
		res.setResultValueList(listVal);

		return res;

	}
	
	
	@RequestMapping(value = "/{user}/stdDeviation/{id1}", method = RequestMethod.GET)
	public @ResponseBody
	Result standardDeviation(@PathVariable String user, @PathVariable int id1) {
		
		Operand op= operandTransaction.findOperandById(id1);
		
		List<Long> list_id= new ArrayList<Long>();
		list_id.add(new Long(id1));

		Result res= new Result(null, list_id, null);
		List<ResultValue> listVal = new ArrayList<ResultValue>();
		
		ResultValue rv= new ResultValue();
		
		rv.setOperand(String.valueOf(id1));
		rv.setValue(String.valueOf(MacroService.compute_standardDeviation(op)));
		
		listVal.add(rv);
		
		res.setResultValueList(listVal);

		return res;

	}
	
	@RequestMapping(value = "/{user}/median/{id1}", method = RequestMethod.GET)
	public @ResponseBody
	Result median(@PathVariable String user, @PathVariable int id1) {
		
		Operand op= operandTransaction.findOperandById(id1);
		List<Long> list_id= new ArrayList<Long>();
		list_id.add(new Long(id1));

		Result res= new Result(null, list_id, null);
		List<ResultValue> listVal = new ArrayList<ResultValue>();
		
		ResultValue rv= new ResultValue();
		
		rv.setOperand(String.valueOf(id1));
		rv.setValue(String.valueOf(MacroService.compute_median(op)));
		
		listVal.add(rv);
		
		res.setResultValueList(listVal);

		return res;

	}


}
