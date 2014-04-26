package com.isssr5.controllers;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.OutOfRangeException;
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
@RequestMapping("/parametricTest")
public class ParametricTestController {

	private static ServiceUserTransaction serviceUserTransaction;
	private static OperandTransaction operandTransaction;

	@Autowired
	public ParametricTestController(
			ServiceUserTransaction serviceUserTransaction,
			OperandTransaction operandTransaction) {
		ParametricTestController.serviceUserTransaction = serviceUserTransaction;
		ParametricTestController.operandTransaction = operandTransaction;
	}

	// idCode: TTEST_STAT
	public static Result getTTestStat(String userid, long op1, double mu)
			throws NotExistingUserException, NotExistingOperandException,
			WrongScaleForMacroServiceId {

		ServiceUser user = serviceUserTransaction.getUserById(userid);
		if (user == null)
			throw new NotExistingUserException();
		Operand op = operandTransaction.findOperandById(op1);
		if (op == null)
			throw new NotExistingOperandException();
		if (!op.getUser().getUserid().equals(userid))
			throw new NotExistingOperandException();
		if (op.getScale().getDom() instanceof EnumerateDomain)
			throw new WrongScaleForMacroServiceId();

		Double result = MacroService.tTestStatistic(op, mu);
		Result ret = new Result();
		ArrayList<ResultValue> res_ret = new ArrayList<ResultValue>();
		ResultValue res1 = new ResultValue("TTEST_STAT", result.toString());
		res_ret.add(res1);

		ret.setResultValueList(res_ret);

		List<Long> list_id = new ArrayList<Long>();
		list_id.add(new Long(op1));

		ret.setIdOperand(list_id);
		return ret;
	}

	// idCode: TTEST_STAT
	@RequestMapping(value = "/{userid}/TTEST_STAT/{op1}/{mu}", method = RequestMethod.GET)
	public @ResponseBody
	Result getTTestStatUrl(@PathVariable String userid, @PathVariable long op1,
			@PathVariable double mu) throws NotExistingUserException,
			NotExistingOperandException, WrongScaleForMacroServiceId {

		return getTTestStat(userid, op1, mu);
	}

	// idCode: TTEST_ALPHA_2SIDED

	public static Result getTTestAlphaTwoSided(String userid, long op1,
			double mu, double alpha) throws NotExistingUserException,
			NotExistingOperandException, OutOfRangeException,
			WrongScaleForMacroServiceId {

		ServiceUser user = serviceUserTransaction.getUserById(userid);
		if (user == null)
			throw new NotExistingUserException();
		Operand op = operandTransaction.findOperandById(op1);
		if (op == null)
			throw new NotExistingOperandException();
		if (!op.getUser().getUserid().equals(userid))
			throw new NotExistingOperandException();
		if (op.getScale().getDom() instanceof EnumerateDomain)
			throw new WrongScaleForMacroServiceId();

		Result ret = new Result();
		Boolean result = MacroService.tTestAlphaTwoSided(op, mu, alpha);
		ArrayList<ResultValue> res_ret = new ArrayList<ResultValue>();
		ResultValue res1 = new ResultValue("TTEST_ALPHA_2SIDED",
				result.toString());
		res_ret.add(res1);

		List<Long> list_id = new ArrayList<Long>();
		list_id.add(new Long(op1));

		ret.setIdOperand(list_id);
		ret.setResultValueList(res_ret);
		return ret;
	}

	// idCode: TTEST_ALPHA_2SIDED
	@RequestMapping(value = "/{userid}/TTEST_ALPHA_2SIDED/{op1}/{mu}/{alpha}", method = RequestMethod.GET)
	public @ResponseBody
	Result getTTestAlphaTwoSidedUrl(@PathVariable String userid,
			@PathVariable long op1, @PathVariable double mu,
			@PathVariable double alpha) throws NotExistingUserException,
			NotExistingOperandException, OutOfRangeException,
			WrongScaleForMacroServiceId {

		return getTTestAlphaTwoSided(userid, op1, mu, alpha);
	}

	// idCode: TTEST_PVALUE_2SIDED

	public static Result getTTestPValueTwoSided(String userid, long op1,
			double mu) throws NotExistingUserException,
			NotExistingOperandException, WrongScaleForMacroServiceId {

		ServiceUser user = serviceUserTransaction.getUserById(userid);
		if (user == null)
			throw new NotExistingUserException();
		Operand op = operandTransaction.findOperandById(op1);
		if (op == null)
			throw new NotExistingOperandException();
		if (!op.getUser().getUserid().equals(userid))
			throw new NotExistingOperandException();
		if (op.getScale().getDom() instanceof EnumerateDomain)
			throw new WrongScaleForMacroServiceId();

		Result ret = new Result();
		Double result = MacroService.tTestPValueTwoSided(op, mu);
		ArrayList<ResultValue> res_ret = new ArrayList<ResultValue>();
		ResultValue res1 = new ResultValue("TTEST_PVALUE_2SIDED",
				result.toString());
		res_ret.add(res1);

		List<Long> list_id = new ArrayList<Long>();
		list_id.add(new Long(op1));

		ret.setIdOperand(list_id);
		ret.setResultValueList(res_ret);
		return ret;
	}

	// idCode: TTEST_PVALUE_2SIDED
	@RequestMapping(value = "/{userid}/TTEST_PVALUE_2SIDED/{op1}/{mu}", method = RequestMethod.GET)
	public @ResponseBody
	Result getTTestPVAlueTwoSidedUrl(@PathVariable String userid,
			@PathVariable long op1, @PathVariable double mu)
			throws NotExistingUserException, NotExistingOperandException,
			WrongScaleForMacroServiceId {

		return getTTestPValueTwoSided(userid, op1, mu);
	}

	// idCode: TTEST_1SIDED_LESSEQUAL

	public static Result getTTestOneSidedLessEqual(String userid, long op1,
			double mu, double alpha) throws NotExistingUserException,
			NotExistingOperandException, OutOfRangeException,
			WrongScaleForMacroServiceId {

		ServiceUser user = serviceUserTransaction.getUserById(userid);
		if (user == null)
			throw new NotExistingUserException();
		Operand op = operandTransaction.findOperandById(op1);
		if (op == null)
			throw new NotExistingOperandException();
		if (!op.getUser().getUserid().equals(userid))
			throw new NotExistingOperandException();
		if (op.getScale().getDom() instanceof EnumerateDomain)
			throw new WrongScaleForMacroServiceId();

		Result ret = new Result();
		Boolean result = MacroService.tTestAlphaUnilateralLessEqual(op, mu,
				alpha);
		ArrayList<ResultValue> res_ret = new ArrayList<ResultValue>();
		ResultValue res1 = new ResultValue("TTEST_1SIDED_LESSEQUAL",
				result.toString());
		res_ret.add(res1);

		List<Long> list_id = new ArrayList<Long>();
		list_id.add(new Long(op1));

		ret.setIdOperand(list_id);
		ret.setResultValueList(res_ret);
		return ret;
	}

	// idCode: TTEST_1SIDED_LESSEQUAL
	@RequestMapping(value = "/{userid}/TTEST_1SIDED_LESSEQUAL/{op1}/{mu}/{alpha}", method = RequestMethod.GET)
	public @ResponseBody
	Result getTTestOneSidedLessEqualUrl(@PathVariable String userid,
			@PathVariable long op1, @PathVariable double mu,
			@PathVariable double alpha) throws NotExistingUserException,
			NotExistingOperandException, OutOfRangeException,
			WrongScaleForMacroServiceId {

		return getTTestOneSidedLessEqual(userid, op1, mu, alpha);
	}

	// idCode: TTEST_1SIDED_GREATEREQUAL
	public static Result getTTestOneSidedGreaterEqual(String userid, long op1,
			double mu, double alpha) throws NotExistingUserException,
			NotExistingOperandException, OutOfRangeException,
			WrongScaleForMacroServiceId {

		ServiceUser user = serviceUserTransaction.getUserById(userid);
		if (user == null)
			throw new NotExistingUserException();
		Operand op = operandTransaction.findOperandById(op1);
		if (op == null)
			throw new NotExistingOperandException();
		if (!op.getUser().getUserid().equals(userid))
			throw new NotExistingOperandException();
		if (op.getScale().getDom() instanceof EnumerateDomain)
			throw new WrongScaleForMacroServiceId();

		Result ret = new Result();
		Boolean result = MacroService.tTestAlphaUnilateralGreaterEqual(op, mu,
				alpha);
		ArrayList<ResultValue> res_ret = new ArrayList<ResultValue>();
		ResultValue res1 = new ResultValue("TTEST_1SIDED_GREATEREQUAL",
				result.toString());
		res_ret.add(res1);

		List<Long> list_id = new ArrayList<Long>();
		list_id.add(new Long(op1));

		ret.setIdOperand(list_id);
		ret.setResultValueList(res_ret);
		return ret;
	}

	// idCode: TTEST_1SIDED_GREATEREQUAL
	@RequestMapping(value = "/{userid}/TTEST_1SIDED_GREATEREQUAL/{op1}/{mu}/{alpha}", method = RequestMethod.GET)
	public @ResponseBody
	Result getTTestOneSidedGreaterEqualUrl(@PathVariable String userid,
			@PathVariable long op1, @PathVariable double mu,
			@PathVariable double alpha) throws NotExistingUserException,
			NotExistingOperandException, OutOfRangeException,
			WrongScaleForMacroServiceId {

		return getTTestOneSidedGreaterEqual(userid, op1, mu, alpha);
	}

	// idCode: TTEST_PAIRED_STAT
	public static Result getTTestPairedStat(String userid, long op1, long op2)
			throws NotExistingUserException, NotExistingOperandException,
			DimensionMismatchException, WrongScaleForMacroServiceId {

		ServiceUser user = serviceUserTransaction.getUserById(userid);
		if (user == null)
			throw new NotExistingUserException();
		Operand op1_obj = operandTransaction.findOperandById(op1);
		Operand op2_obj = operandTransaction.findOperandById(op2);
		if (op1_obj == null || op2_obj == null)
			throw new NotExistingOperandException();
		if (!op1_obj.getUser().getUserid().equals(userid)
				|| !op2_obj.getUser().getUserid().equals(userid))
			throw new NotExistingOperandException();
		if (op1_obj.getScale().getDom() instanceof EnumerateDomain
				|| op2_obj.getScale().getDom() instanceof EnumerateDomain)
			throw new WrongScaleForMacroServiceId();

		Result ret = new Result();
		Double result = MacroService.tTestPairedStatistic(op1_obj, op2_obj);
		ArrayList<ResultValue> res_ret = new ArrayList<ResultValue>();
		ResultValue res1 = new ResultValue("TTEST_PAIRED_STAT",
				result.toString());
		res_ret.add(res1);

		List<Long> list_id = new ArrayList<Long>();
		list_id.add(new Long(op1));
		list_id.add(new Long(op2));
		ret.setIdOperand(list_id);

		ret.setResultValueList(res_ret);
		return ret;
	}

	// idCode: TTEST_PAIRED_STAT
	@RequestMapping(value = "/{userid}/TTEST_PAIRED_STAT/{op1}/{op2}", method = RequestMethod.GET)
	public @ResponseBody
	Result getTTestPairedStatUrl(@PathVariable String userid,
			@PathVariable long op1, @PathVariable long op2)
			throws NotExistingUserException, NotExistingOperandException,
			DimensionMismatchException, WrongScaleForMacroServiceId {

		return getTTestPairedStat(userid, op1, op2);
	}

	// idCode: TTEST_PAIRED_PVALUE
	public static Result getTTestPairedPValue(String userid, long op1, long op2)
			throws NotExistingUserException, NotExistingOperandException,
			DimensionMismatchException, WrongScaleForMacroServiceId {
		ServiceUser user = serviceUserTransaction.getUserById(userid);
		if (user == null)
			throw new NotExistingUserException();
		Operand op1_obj = operandTransaction.findOperandById(op1);
		Operand op2_obj = operandTransaction.findOperandById(op2);
		if (op1_obj == null || op2_obj == null)
			throw new NotExistingOperandException();
		if (!op1_obj.getUser().getUserid().equals(userid)
				|| !op2_obj.getUser().getUserid().equals(userid))
			throw new NotExistingOperandException();
		if (op1_obj.getScale().getDom() instanceof EnumerateDomain
				|| op2_obj.getScale().getDom() instanceof EnumerateDomain)
			throw new WrongScaleForMacroServiceId();

		Result ret = new Result();
		Double result = MacroService.tTestPairedPValue(op1_obj, op2_obj);
		ArrayList<ResultValue> res_ret = new ArrayList<ResultValue>();
		ResultValue res1 = new ResultValue("TTEST_PAIRED_PVALUE",
				result.toString());
		res_ret.add(res1);

		List<Long> list_id = new ArrayList<Long>();
		list_id.add(new Long(op1));
		list_id.add(new Long(op2));
		ret.setIdOperand(list_id);

		ret.setResultValueList(res_ret);
		return ret;
	}

	// idCode: TTEST_PAIRED_PVALUE
	@RequestMapping(value = "/{userid}/TTEST_PAIRED_PVALUE/{op1}/{op2}", method = RequestMethod.GET)
	public @ResponseBody
	Result getTTestPairedPValueUrl(@PathVariable String userid,
			@PathVariable long op1, @PathVariable long op2)
			throws NotExistingUserException, NotExistingOperandException,
			DimensionMismatchException, WrongScaleForMacroServiceId {
		return getTTestPairedPValue(userid, op1, op2);
	}

	// idCode: TTEST_PAIRED_ALPHA
	public static Result getTTestPairedAlpha(String userid, long op1, long op2,
			double alpha) throws NotExistingUserException,
			NotExistingOperandException, OutOfRangeException,
			DimensionMismatchException, WrongScaleForMacroServiceId {

		ServiceUser user = serviceUserTransaction.getUserById(userid);
		if (user == null)
			throw new NotExistingUserException();
		Operand op1_obj = operandTransaction.findOperandById(op1);
		Operand op2_obj = operandTransaction.findOperandById(op2);
		if (op1_obj == null || op2_obj == null)
			throw new NotExistingOperandException();
		if (!op1_obj.getUser().getUserid().equals(userid)
				|| !op2_obj.getUser().getUserid().equals(userid))
			throw new NotExistingOperandException();
		if (op1_obj.getScale().getDom() instanceof EnumerateDomain
				|| op2_obj.getScale().getDom() instanceof EnumerateDomain)
			throw new WrongScaleForMacroServiceId();

		Result ret = new Result();
		Boolean result = MacroService.tTestPairedAlpha(op1_obj, op2_obj, alpha);
		ArrayList<ResultValue> res_ret = new ArrayList<ResultValue>();
		ResultValue res1 = new ResultValue("TTEST_PAIRED_ALPHA",
				result.toString());
		res_ret.add(res1);

		List<Long> list_id = new ArrayList<Long>();
		list_id.add(new Long(op1));
		list_id.add(new Long(op2));
		ret.setIdOperand(list_id);

		ret.setResultValueList(res_ret);
		return ret;
	}

	// idCode: TTEST_PAIRED_ALPHA
	@RequestMapping(value = "/{userid}/TTEST_PAIRED_ALPHA/{op1}/{op2}/{alpha}", method = RequestMethod.GET)
	public @ResponseBody
	Result getTTestPairedAlphaUrl(@PathVariable String userid,
			@PathVariable long op1, @PathVariable long op2,
			@PathVariable double alpha) throws NotExistingUserException,
			NotExistingOperandException, OutOfRangeException,
			DimensionMismatchException, WrongScaleForMacroServiceId {

		return getTTestPairedAlpha(userid, op1, op2, alpha);
	}

	// idCode: TTEST_STAT2
	public static Result getTTestStat(String userid, long op1, long op2)
			throws NotExistingUserException, NotExistingOperandException,
			WrongScaleForMacroServiceId {

		ServiceUser user = serviceUserTransaction.getUserById(userid);
		if (user == null)
			throw new NotExistingUserException();
		Operand op1_obj = operandTransaction.findOperandById(op1);
		Operand op2_obj = operandTransaction.findOperandById(op2);
		if (op1_obj == null || op2_obj == null)
			throw new NotExistingOperandException();
		if (!op1_obj.getUser().getUserid().equals(userid)
				|| !op2_obj.getUser().getUserid().equals(userid))
			throw new NotExistingOperandException();
		if (op1_obj.getScale().getDom() instanceof EnumerateDomain
				|| op2_obj.getScale().getDom() instanceof EnumerateDomain)
			throw new WrongScaleForMacroServiceId();

		Result ret = new Result();
		Double result = MacroService.tTestStatistic(op1_obj, op2_obj);
		ArrayList<ResultValue> res_ret = new ArrayList<ResultValue>();
		ResultValue res1 = new ResultValue("TTEST_STAT2", result.toString());
		res_ret.add(res1);

		List<Long> list_id = new ArrayList<Long>();
		list_id.add(new Long(op1));
		list_id.add(new Long(op2));
		ret.setIdOperand(list_id);

		ret.setResultValueList(res_ret);
		return ret;
	}

	// idCode: TTEST_STAT2
	@RequestMapping(value = "/{userid}/TTEST_STAT2/{op1}/{op2}", method = RequestMethod.GET)
	public @ResponseBody
	Result getTTestStatUrl(@PathVariable String userid, @PathVariable long op1,
			@PathVariable long op2) throws NotExistingUserException,
			NotExistingOperandException, WrongScaleForMacroServiceId {

		return getTTestStat(userid, op1, op2);
	}

	// idCode: TTEST_PVALUE_2SIDED_2
	public static Result getTTestPValueTwoSided(String userid, long op1,
			long op2) throws NotExistingUserException,
			NotExistingOperandException, WrongScaleForMacroServiceId {

		ServiceUser user = serviceUserTransaction.getUserById(userid);
		if (user == null)
			throw new NotExistingUserException();
		Operand op1_obj = operandTransaction.findOperandById(op1);
		Operand op2_obj = operandTransaction.findOperandById(op2);
		if (op1_obj == null || op2_obj == null)
			throw new NotExistingOperandException();
		if (!op1_obj.getUser().getUserid().equals(userid)
				|| !op2_obj.getUser().getUserid().equals(userid))
			throw new NotExistingOperandException();
		if (op1_obj.getScale().getDom() instanceof EnumerateDomain
				|| op2_obj.getScale().getDom() instanceof EnumerateDomain)
			throw new WrongScaleForMacroServiceId();

		Result ret = new Result();
		Double result = MacroService.tTestPValueTwoSided(op1_obj, op2_obj);
		ArrayList<ResultValue> res_ret = new ArrayList<ResultValue>();
		ResultValue res1 = new ResultValue("TTEST_PVALUE_2SIDED_2",
				result.toString());
		res_ret.add(res1);

		List<Long> list_id = new ArrayList<Long>();
		list_id.add(new Long(op1));
		list_id.add(new Long(op2));
		ret.setIdOperand(list_id);

		ret.setResultValueList(res_ret);
		return ret;
	}

	// idCode: TTEST_PVALUE_2SIDED_2
	@RequestMapping(value = "/{userid}/TTEST_PVALUE_2SIDED_2/{op1}/{op2}", method = RequestMethod.GET)
	public @ResponseBody
	Result getTTestPValueTwoSidedUrl(@PathVariable String userid,
			@PathVariable long op1, @PathVariable long op2)
			throws NotExistingUserException, NotExistingOperandException,
			WrongScaleForMacroServiceId {

		return getTTestPValueTwoSided(userid, op1, op2);
	}

	// idCode: TTEST_ALPHA_2SIDED_2
	public static Result getTTestAlphaTwoSided(String userid, long op1,
			long op2, double alpha) throws NotExistingUserException,
			NotExistingOperandException, OutOfRangeException,
			WrongScaleForMacroServiceId {

		ServiceUser user = serviceUserTransaction.getUserById(userid);
		if (user == null)
			throw new NotExistingUserException();
		Operand op1_obj = operandTransaction.findOperandById(op1);
		Operand op2_obj = operandTransaction.findOperandById(op2);
		if (op1_obj == null || op2_obj == null)
			throw new NotExistingOperandException();
		if (!op1_obj.getUser().getUserid().equals(userid)
				|| !op2_obj.getUser().getUserid().equals(userid))
			throw new NotExistingOperandException();
		if (op1_obj.getScale().getDom() instanceof EnumerateDomain
				|| op2_obj.getScale().getDom() instanceof EnumerateDomain)
			throw new WrongScaleForMacroServiceId();

		Result ret = new Result();
		Boolean result = MacroService.tTestAlphaTwoSided(op1_obj, op2_obj,
				alpha);
		ArrayList<ResultValue> res_ret = new ArrayList<ResultValue>();
		ResultValue res1 = new ResultValue("TTEST_ALPHA_2SIDED_2",
				result.toString());
		res_ret.add(res1);

		List<Long> list_id = new ArrayList<Long>();
		list_id.add(new Long(op1));
		list_id.add(new Long(op2));
		ret.setIdOperand(list_id);

		ret.setResultValueList(res_ret);
		return ret;
	}

	// idCode: TTEST_ALPHA_2SIDED_2
	@RequestMapping(value = "/{userid}/TTEST_ALPHA_2SIDED_2/{op1}/{op2}/{alpha}", method = RequestMethod.GET)
	public @ResponseBody
	Result getTTestAlphaTwoSidedUrl(@PathVariable String userid,
			@PathVariable long op1, @PathVariable long op2,
			@PathVariable double alpha) throws NotExistingUserException,
			NotExistingOperandException, OutOfRangeException,
			WrongScaleForMacroServiceId {

		return getTTestAlphaTwoSided(userid, op1, op2, alpha);
	}

	// idCode: TTEST_STAT_2SIDED_EQVAR
	public static Result getTTestStatTwoSidedEqVar(String userid, long op1,
			long op2) throws NotExistingUserException,
			NotExistingOperandException, WrongScaleForMacroServiceId {

		ServiceUser user = serviceUserTransaction.getUserById(userid);
		if (user == null)
			throw new NotExistingUserException();
		Operand op1_obj = operandTransaction.findOperandById(op1);
		Operand op2_obj = operandTransaction.findOperandById(op2);
		if (op1_obj == null || op2_obj == null)
			throw new NotExistingOperandException();
		if (!op1_obj.getUser().getUserid().equals(userid)
				|| !op2_obj.getUser().getUserid().equals(userid))
			throw new NotExistingOperandException();
		if (op1_obj.getScale().getDom() instanceof EnumerateDomain
				|| op2_obj.getScale().getDom() instanceof EnumerateDomain)
			throw new WrongScaleForMacroServiceId();

		Result ret = new Result();
		Double result = MacroService.tTestStatisticTwoSidedEqualVar(op1_obj,
				op2_obj);
		ArrayList<ResultValue> res_ret = new ArrayList<ResultValue>();
		ResultValue res1 = new ResultValue("TTEST_STAT_2SIDED_EQVAR",
				result.toString());
		res_ret.add(res1);

		List<Long> list_id = new ArrayList<Long>();
		list_id.add(new Long(op1));
		list_id.add(new Long(op2));
		ret.setIdOperand(list_id);

		ret.setResultValueList(res_ret);
		return ret;
	}

	// idCode: TTEST_STAT_2SIDED_EQVAR
	@RequestMapping(value = "/{userid}/TTEST_STAT_2SIDED_EQVAR/{op1}/{op2}", method = RequestMethod.GET)
	public @ResponseBody
	Result getTTestStatTwoSidedEqVarUrl(@PathVariable String userid,
			@PathVariable long op1, @PathVariable long op2)
			throws NotExistingUserException, NotExistingOperandException,
			WrongScaleForMacroServiceId {

		return getTTestStatTwoSidedEqVar(userid, op1, op2);
	}

	// idCode: TTEST_PVALUE_2SIDED_EQVAR
	public static Result getTTestPValueTwoSidedEqVar(String userid, long op1,
			long op2) throws NotExistingUserException,
			NotExistingOperandException, WrongScaleForMacroServiceId {

		ServiceUser user = serviceUserTransaction.getUserById(userid);
		if (user == null)
			throw new NotExistingUserException();
		Operand op1_obj = operandTransaction.findOperandById(op1);
		Operand op2_obj = operandTransaction.findOperandById(op2);
		if (op1_obj == null || op2_obj == null)
			throw new NotExistingOperandException();
		if (!op1_obj.getUser().getUserid().equals(userid)
				|| !op2_obj.getUser().getUserid().equals(userid))
			throw new NotExistingOperandException();
		if (op1_obj.getScale().getDom() instanceof EnumerateDomain
				|| op2_obj.getScale().getDom() instanceof EnumerateDomain)
			throw new WrongScaleForMacroServiceId();

		Result ret = new Result();
		Double result = MacroService.tTestPValueTwoSidedEqualVar(op1_obj,
				op2_obj);
		ArrayList<ResultValue> res_ret = new ArrayList<ResultValue>();
		ResultValue res1 = new ResultValue("TTEST_PVALUE_2SIDED_EQVAR",
				result.toString());
		res_ret.add(res1);

		List<Long> list_id = new ArrayList<Long>();
		list_id.add(new Long(op1));
		list_id.add(new Long(op2));
		ret.setIdOperand(list_id);

		ret.setResultValueList(res_ret);
		return ret;
	}

	// idCode: TTEST_PVALUE_2SIDED_EQVAR
	@RequestMapping(value = "/{userid}/TTEST_PVALUE_2SIDED_EQVAR/{op1}/{op2}", method = RequestMethod.GET)
	public @ResponseBody
	Result getTTestPValueTwoSidedEqVarUrl(@PathVariable String userid,
			@PathVariable long op1, @PathVariable long op2)
			throws NotExistingUserException, NotExistingOperandException,
			WrongScaleForMacroServiceId {

		return getTTestPValueTwoSidedEqVar(userid, op1, op2);
	}

	// idCode: TTEST_ALPHA_2SIDED_EQVAR
	public static Result getTTestAlphaTwoSidedEqVar(String userid, long op1,
			long op2, double alpha) throws NotExistingUserException,
			NotExistingOperandException, OutOfRangeException,
			WrongScaleForMacroServiceId {

		ServiceUser user = serviceUserTransaction.getUserById(userid);
		if (user == null)
			throw new NotExistingUserException();
		Operand op1_obj = operandTransaction.findOperandById(op1);
		Operand op2_obj = operandTransaction.findOperandById(op2);
		if (op1_obj == null || op2_obj == null)
			throw new NotExistingOperandException();
		if (!op1_obj.getUser().getUserid().equals(userid)
				|| !op2_obj.getUser().getUserid().equals(userid))
			throw new NotExistingOperandException();
		if (op1_obj.getScale().getDom() instanceof EnumerateDomain
				|| op2_obj.getScale().getDom() instanceof EnumerateDomain)
			throw new WrongScaleForMacroServiceId();

		Result ret = new Result();
		Boolean result = MacroService.tTestAlphaTwoSidedEqualVar(op1_obj,
				op2_obj, alpha);
		ArrayList<ResultValue> res_ret = new ArrayList<ResultValue>();
		ResultValue res1 = new ResultValue("TTEST_ALPHA_2SIDED_2_EQVAR",
				result.toString());
		res_ret.add(res1);

		List<Long> list_id = new ArrayList<Long>();
		list_id.add(new Long(op1));
		list_id.add(new Long(op2));
		ret.setIdOperand(list_id);

		ret.setResultValueList(res_ret);
		return ret;
	}

	// idCode: TTEST_ALPHA_2SIDED_EQVAR
	@RequestMapping(value = "/{userid}/TTEST_ALPHA_2SIDED_2_EQVAR/{op1}/{op2}/{alpha}", method = RequestMethod.GET)
	public @ResponseBody
	Result getTTestAlphaTwoSidedEqVarUrl(@PathVariable String userid,
			@PathVariable long op1, @PathVariable long op2,
			@PathVariable double alpha) throws NotExistingUserException,
			NotExistingOperandException, OutOfRangeException,
			WrongScaleForMacroServiceId {

		return getTTestAlphaTwoSidedEqVar(userid, op1, op2, alpha);
	}

	// idCode: TTEST_1SIDED_LESSEQUAL_2_EQVAR
	public static Result getTTestAlphaOneSidedLessEqualEqVar(String userid,
			long op1, long op2, double alpha) throws NotExistingUserException,
			NotExistingOperandException, OutOfRangeException,
			WrongScaleForMacroServiceId {

		ServiceUser user = serviceUserTransaction.getUserById(userid);
		if (user == null)
			throw new NotExistingUserException();
		Operand op1_obj = operandTransaction.findOperandById(op1);
		Operand op2_obj = operandTransaction.findOperandById(op2);
		if (op1_obj == null || op2_obj == null)
			throw new NotExistingOperandException();
		if (!op1_obj.getUser().getUserid().equals(userid)
				|| !op2_obj.getUser().getUserid().equals(userid))
			throw new NotExistingOperandException();
		if (op1_obj.getScale().getDom() instanceof EnumerateDomain
				|| op2_obj.getScale().getDom() instanceof EnumerateDomain)
			throw new WrongScaleForMacroServiceId();

		Result ret = new Result();
		Boolean result = MacroService.tTestAlphaUnilateraLessEqualVar(op1_obj,
				op2_obj, alpha);
		ArrayList<ResultValue> res_ret = new ArrayList<ResultValue>();
		ResultValue res1 = new ResultValue("TTEST_1SIDED_LESSEQUAL_2_EQVAR",
				result.toString());
		res_ret.add(res1);

		List<Long> list_id = new ArrayList<Long>();
		list_id.add(new Long(op1));
		list_id.add(new Long(op2));
		ret.setIdOperand(list_id);

		ret.setResultValueList(res_ret);
		return ret;
	}

	// idCode: TTEST_1SIDED_LESSEQUAL_2_EQVAR
	@RequestMapping(value = "/{userid}/TTEST_1SIDED_LESSEQUAL_2_EQVAR/{op1}/{op2}/{alpha}", method = RequestMethod.GET)
	public @ResponseBody
	Result getTTestAlphaOneSidedLessEqualEqVarUrl(@PathVariable String userid,
			@PathVariable long op1, @PathVariable long op2,
			@PathVariable double alpha) throws NotExistingUserException,
			NotExistingOperandException, OutOfRangeException,
			WrongScaleForMacroServiceId {

		return getTTestAlphaOneSidedLessEqualEqVar(userid, op1, op2, alpha);
	}

	// idCode: TTEST_1SIDED_GREATEREQUAL_2_EQVAR
	public static Result getTTestAlphaOneSidedGreaterEqualEqVar(String userid,
			long op1, long op2, double alpha) throws NotExistingUserException,
			NotExistingOperandException, OutOfRangeException,
			WrongScaleForMacroServiceId {

		ServiceUser user = serviceUserTransaction.getUserById(userid);
		if (user == null)
			throw new NotExistingUserException();
		Operand op1_obj = operandTransaction.findOperandById(op1);
		Operand op2_obj = operandTransaction.findOperandById(op2);
		if (op1_obj == null || op2_obj == null)
			throw new NotExistingOperandException();
		if (!op1_obj.getUser().getUserid().equals(userid)
				|| !op2_obj.getUser().getUserid().equals(userid))
			throw new NotExistingOperandException();
		if (op1_obj.getScale().getDom() instanceof EnumerateDomain
				|| op2_obj.getScale().getDom() instanceof EnumerateDomain)
			throw new WrongScaleForMacroServiceId();

		Result ret = new Result();
		Boolean result = MacroService.tTestAlphaUnilateralGreaterEqualVar(
				op1_obj, op2_obj, alpha);
		ArrayList<ResultValue> res_ret = new ArrayList<ResultValue>();
		ResultValue res1 = new ResultValue("TTEST_1SIDED_GREATEREQUAL_2_EQVAR",
				result.toString());
		res_ret.add(res1);

		List<Long> list_id = new ArrayList<Long>();
		list_id.add(new Long(op1));
		list_id.add(new Long(op2));
		ret.setIdOperand(list_id);

		ret.setResultValueList(res_ret);
		return ret;
	}

	// idCode: TTEST_1SIDED_GREATEREQUAL_2_EQVAR
	@RequestMapping(value = "/{userid}/TTEST_1SIDED_GREATEREQUAL_2_EQVAR/{op1}/{op2}/{alpha}", method = RequestMethod.GET)
	public @ResponseBody
	Result getTTestAlphaOneSidedGreaterEqualEqVarUrl(
			@PathVariable String userid, @PathVariable long op1,
			@PathVariable long op2, @PathVariable double alpha)
			throws NotExistingUserException, NotExistingOperandException,
			OutOfRangeException, WrongScaleForMacroServiceId {

		return getTTestAlphaOneSidedGreaterEqualEqVar(userid, op1, op2, alpha);
	}

	// idCode: ONEWAY_ANOVA_PVALUE_3OP
	public static Result getOneWayAnovaPValue(String userid, long op1,
			long op2, long op3) throws NotExistingUserException,
			NotExistingOperandException, WrongScaleForMacroServiceId {

		ServiceUser user = serviceUserTransaction.getUserById(userid);
		if (user == null)
			throw new NotExistingUserException();
		Operand op1_obj = operandTransaction.findOperandById(op1);
		Operand op2_obj = operandTransaction.findOperandById(op2);
		Operand op3_obj = operandTransaction.findOperandById(op3);
		if (op1_obj == null || op2_obj == null || op3_obj == null)
			throw new NotExistingOperandException();
		if (!op1_obj.getUser().getUserid().equals(userid)
				|| !op2_obj.getUser().getUserid().equals(userid)
				|| !op3_obj.getUser().getUserid().equals(userid))
			throw new NotExistingOperandException();
		if (op1_obj.getScale().getDom() instanceof EnumerateDomain
				|| op2_obj.getScale().getDom() instanceof EnumerateDomain
				|| op3_obj.getScale().getDom() instanceof EnumerateDomain)
			throw new WrongScaleForMacroServiceId();

		Result ret = new Result();
		Double result = MacroService.oneWayAnovaPValue(op1_obj, op2_obj,
				op3_obj);
		ArrayList<ResultValue> res_ret = new ArrayList<ResultValue>();
		ResultValue res1 = new ResultValue("ONEWAY_ANOVA_PVALUE_3OP",
				result.toString());
		res_ret.add(res1);

		List<Long> list_id = new ArrayList<Long>();
		list_id.add(new Long(op1));
		list_id.add(new Long(op2));
		list_id.add(new Long(op3));
		ret.setIdOperand(list_id);

		ret.setResultValueList(res_ret);
		return ret;
	}

	// idCode: ONEWAY_ANOVA_PVALUE_3OP
	@RequestMapping(value = "/{userid}/ONEWAY_ANOVA_PVALUE_3OP/{op1}/{op2}/{op3}", method = RequestMethod.GET)
	public @ResponseBody
	Result getOneWayAnovaPValueUrl(@PathVariable String userid,
			@PathVariable long op1, @PathVariable long op2,
			@PathVariable long op3) throws NotExistingUserException,
			NotExistingOperandException, WrongScaleForMacroServiceId {

		return getOneWayAnovaPValue(userid, op1, op2, op3);
	}

	// idCode: ONEWAY_ANOVA_ALPHA_3OP
	public static Result getOneWayAnovaAlpha(String userid, long op1, long op2,
			long op3, double alpha) throws NotExistingUserException,
			NotExistingOperandException, OutOfRangeException,
			WrongScaleForMacroServiceId {

		ServiceUser user = serviceUserTransaction.getUserById(userid);
		if (user == null)
			throw new NotExistingUserException();
		Operand op1_obj = operandTransaction.findOperandById(op1);
		Operand op2_obj = operandTransaction.findOperandById(op2);
		Operand op3_obj = operandTransaction.findOperandById(op3);
		if (op1_obj == null || op2_obj == null || op3_obj == null)
			throw new NotExistingOperandException();
		if (!op1_obj.getUser().getUserid().equals(userid)
				|| !op2_obj.getUser().getUserid().equals(userid)
				|| !op3_obj.getUser().getUserid().equals(userid))
			throw new NotExistingOperandException();
		if (op1_obj.getScale().getDom() instanceof EnumerateDomain
				|| op2_obj.getScale().getDom() instanceof EnumerateDomain
				|| op3_obj.getScale().getDom() instanceof EnumerateDomain)
			throw new WrongScaleForMacroServiceId();

		Result ret = new Result();
		Boolean result = MacroService.oneWayAnovaAlpha(op1_obj, op2_obj,
				op3_obj, alpha);
		ArrayList<ResultValue> res_ret = new ArrayList<ResultValue>();
		ResultValue res1 = new ResultValue("ONEWAY_ANOVA_ALPHA_3OP",
				result.toString());
		res_ret.add(res1);

		List<Long> list_id = new ArrayList<Long>();
		list_id.add(new Long(op1));
		list_id.add(new Long(op2));
		list_id.add(new Long(op3));
		ret.setIdOperand(list_id);

		ret.setResultValueList(res_ret);
		return ret;
	}

	// idCode: ONEWAY_ANOVA_ALPHA_3OP
	@RequestMapping(value = "/{userid}/ONEWAY_ANOVA_ALPHA_3OP/{op1}/{op2}/{op3}/{alpha}", method = RequestMethod.GET)
	public @ResponseBody
	Result getOneWayAnovaAlphaUrl(@PathVariable String userid,
			@PathVariable long op1, @PathVariable long op2,
			@PathVariable long op3, @PathVariable double alpha)
			throws NotExistingUserException, NotExistingOperandException,
			OutOfRangeException, WrongScaleForMacroServiceId {

		return getOneWayAnovaAlpha(userid, op1, op2, op3, alpha);
	}

	// idCode: ONEWAY_ANOVA_PVALUE_4OP
	public static Result getOneWayAnovaPValue(String userid, long op1,
			long op2, long op3, long op4) throws NotExistingUserException,
			NotExistingOperandException, WrongScaleForMacroServiceId {

		ServiceUser user = serviceUserTransaction.getUserById(userid);
		if (user == null)
			throw new NotExistingUserException();
		Operand op1_obj = operandTransaction.findOperandById(op1);
		Operand op2_obj = operandTransaction.findOperandById(op2);
		Operand op3_obj = operandTransaction.findOperandById(op3);
		Operand op4_obj = operandTransaction.findOperandById(op4);
		if (op1_obj == null || op2_obj == null || op3_obj == null
				|| op4_obj == null)
			throw new NotExistingOperandException();
		if (!op1_obj.getUser().getUserid().equals(userid)
				|| !op2_obj.getUser().getUserid().equals(userid)
				|| !op3_obj.getUser().getUserid().equals(userid)
				|| !op4_obj.getUser().getUserid().equals(userid))
			throw new NotExistingOperandException();
		if (op1_obj.getScale().getDom() instanceof EnumerateDomain
				|| op2_obj.getScale().getDom() instanceof EnumerateDomain
				|| op3_obj.getScale().getDom() instanceof EnumerateDomain
				|| op4_obj.getScale().getDom() instanceof EnumerateDomain)
			throw new WrongScaleForMacroServiceId();

		Result ret = new Result();
		Double result = MacroService.oneWayAnovaPValue(op1_obj, op2_obj,
				op3_obj, op4_obj);
		ArrayList<ResultValue> res_ret = new ArrayList<ResultValue>();
		ResultValue res1 = new ResultValue("ONEWAY_ANOVA_PVALUE_4OP",
				result.toString());
		res_ret.add(res1);

		List<Long> list_id = new ArrayList<Long>();
		list_id.add(new Long(op1));
		list_id.add(new Long(op2));
		list_id.add(new Long(op3));
		list_id.add(new Long(op4));
		ret.setIdOperand(list_id);

		ret.setResultValueList(res_ret);
		return ret;
	}

	// idCode: ONEWAY_ANOVA_PVALUE_4OP
	@RequestMapping(value = "/{userid}/ONEWAY_ANOVA_PVALUE_4OP/{op1}/{op2}/{op3}/{op4}", method = RequestMethod.GET)
	public @ResponseBody
	Result getOneWayAnovaPValueUrl(@PathVariable String userid,
			@PathVariable long op1, @PathVariable long op2,
			@PathVariable long op3, @PathVariable long op4)
			throws NotExistingUserException, NotExistingOperandException, WrongScaleForMacroServiceId {

		return getOneWayAnovaPValue(userid, op1, op2, op3, op4);
	}

	// idCode: ONEWAY_ANOVA_ALPHA_4OP
	public static Result getOneWayAnovaAlpha(String userid, long op1, long op2,
			long op3, long op4, double alpha) throws NotExistingUserException,
			NotExistingOperandException, OutOfRangeException, WrongScaleForMacroServiceId {

		ServiceUser user = serviceUserTransaction.getUserById(userid);
		if (user == null)
			throw new NotExistingUserException();
		Operand op1_obj = operandTransaction.findOperandById(op1);
		Operand op2_obj = operandTransaction.findOperandById(op2);
		Operand op3_obj = operandTransaction.findOperandById(op3);
		Operand op4_obj = operandTransaction.findOperandById(op4);
		if (op1_obj == null || op2_obj == null || op3_obj == null
				|| op4_obj == null)
			throw new NotExistingOperandException();
		if (!op1_obj.getUser().getUserid().equals(userid)
				|| !op2_obj.getUser().getUserid().equals(userid)
				|| !op3_obj.getUser().getUserid().equals(userid)
				|| !op4_obj.getUser().getUserid().equals(userid))
			throw new NotExistingOperandException();
		if (op1_obj.getScale().getDom() instanceof EnumerateDomain
				|| op2_obj.getScale().getDom() instanceof EnumerateDomain
				|| op3_obj.getScale().getDom() instanceof EnumerateDomain
				|| op4_obj.getScale().getDom() instanceof EnumerateDomain)
			throw new WrongScaleForMacroServiceId();

		Result ret = new Result();
		Boolean result = MacroService.oneWayAnovaAlpha(op1_obj, op2_obj,
				op3_obj, op4_obj, alpha);
		ArrayList<ResultValue> res_ret = new ArrayList<ResultValue>();
		ResultValue res1 = new ResultValue("ONEWAY_ANOVA_ALPHA_4OP",
				result.toString());
		res_ret.add(res1);

		List<Long> list_id = new ArrayList<Long>();
		list_id.add(new Long(op1));
		list_id.add(new Long(op2));
		list_id.add(new Long(op3));
		list_id.add(new Long(op4));
		ret.setIdOperand(list_id);

		ret.setResultValueList(res_ret);
		return ret;
	}

	// idCode: ONEWAY_ANOVA_ALPHA_4OP
	@RequestMapping(value = "/{userid}/ONEWAY_ANOVA_ALPHA_3OP/{op1}/{op2}/{op3}/{op4}/{alpha}", method = RequestMethod.GET)
	public @ResponseBody
	Result getOneWayAnovaAlphaUrl(@PathVariable String userid,
			@PathVariable long op1, @PathVariable long op2,
			@PathVariable long op3, @PathVariable long op4,
			@PathVariable double alpha) throws NotExistingUserException,
			NotExistingOperandException, OutOfRangeException,
			WrongScaleForMacroServiceId {

		return getOneWayAnovaAlpha(userid, op1, op2, op3, op4, alpha);
	}

}
