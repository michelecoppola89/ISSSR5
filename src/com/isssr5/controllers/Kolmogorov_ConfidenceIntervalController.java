package com.isssr5.controllers;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.distribution.AbstractRealDistribution;
import org.apache.commons.math3.distribution.LogNormalDistribution;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.distribution.RealDistribution;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.isssr5.controllers.ScaleController.Wrapper;
import com.isssr5.entities.MacroService;
import com.isssr5.entities.Operand;
import com.isssr5.entities.Result;
import com.isssr5.entities.ResultValue;
import com.isssr5.entities.ServiceUser;
import com.isssr5.exceptions.NotExistingUserException;
import com.isssr5.service.MacroServiceTransaction;
import com.isssr5.service.OperandTransaction;
import com.isssr5.service.ServiceUserTransaction;
@Controller
@RequestMapping("/scale")
public class Kolmogorov_ConfidenceIntervalController {
	private ServiceUserTransaction serviceUserTransaction;
	private MacroServiceTransaction macroServiceTransaction;
	private OperandTransaction operandTransaction;

	@RequestMapping(value = "/1SampleKolmogrov/{user}/{msId}/{opId}/{level}", method = RequestMethod.GET)
	public @ResponseBody
	Result getConfidenceInterval(@PathVariable String user,
			@PathVariable String msId, @PathVariable long opId,
			@PathVariable double level) {
		ServiceUser u = serviceUserTransaction.getUserById(user);
		MacroService ms = macroServiceTransaction.findMacroServiceById(msId);
		Operand op = operandTransaction.findOperandById(opId);
		List<Long> operands = new ArrayList<Long>();
		operands.add(opId);
		List<ResultValue> resultValues = new ArrayList<ResultValue>();
		resultValues.add(ConfidenceInterval(ms, op, level));
		Result result = new Result(ms, operands, resultValues);

		return result;
	}

	ResultValue ConfidenceInterval(MacroService ms, Operand op, double level) {

		ResultValue rv = new ResultValue(ms.getIdCode(), Double.toString(ms
				.Confidence_Interval(op, level)));
		return rv;

	}

	@RequestMapping(value = "/1SampleKolmogrov/{user}/{msId}/{opId}/{distribution}", method = RequestMethod.GET)
	public @ResponseBody
	Result getOneSampleKolmogrov(@PathVariable String user, @PathVariable String msId,
			@PathVariable long opId, @PathVariable String distribution) {
		ServiceUser u = serviceUserTransaction.getUserById(user);
		MacroService ms = macroServiceTransaction.findMacroServiceById(msId);
		Operand op = operandTransaction.findOperandById(opId);
		List<Long> operands = new ArrayList<Long>();
		operands.add(opId);
		List<ResultValue> resultValues = new ArrayList<ResultValue>();
		resultValues.add(OneSampleKolmogorovSmirnov(ms,distribution,op));
		Result result = new Result(ms,operands,resultValues);

		return result;
	}

	ResultValue OneSampleKolmogorovSmirnov(MacroService ms,
			String distribution, Operand op) {
		List<Long> operands = new ArrayList<Long>();
		operands.add(op.getId());

		ResultValue rv = new ResultValue(ms.getIdCode(), Double.toString(ms
				.kolmogorovSmirnovStatistic(distribution, op)));
		return rv;

	}
	@RequestMapping(value = "/2SampleKolmogrov/{user}/{msId}/{op1Id}/{op2Id}/{distribution}", method = RequestMethod.GET)
	public @ResponseBody
	Result getOneSampleKolmogrov(@PathVariable String user, @PathVariable String msId,
			@PathVariable long op1Id,@PathVariable long op2Id, @PathVariable String distribution) {
		ServiceUser u = serviceUserTransaction.getUserById(user);
		MacroService ms = macroServiceTransaction.findMacroServiceById(msId);
		Operand op1 = operandTransaction.findOperandById(op1Id);
		Operand op2 = operandTransaction.findOperandById(op2Id);
		List<Long> operands = new ArrayList<Long>();
		operands.add(op1Id);
		operands.add(op2Id);
		List<ResultValue> resultValues = new ArrayList<ResultValue>();
		resultValues.add(TwoSampleKolmogorovSmirnov(ms,op1,op2));
		Result result = new Result(ms,operands,resultValues);

		return result;
	}
	ResultValue TwoSampleKolmogorovSmirnov(MacroService ms, Operand op1,
			Operand op2) {

		ResultValue rv = new ResultValue(null, Double.toString(ms
				.kolmogorovSmirnovStatistic(op1, op2)));
		return rv;

	}

}
