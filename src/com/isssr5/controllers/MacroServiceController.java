package com.isssr5.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.math3.exception.OutOfRangeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.isssr5.entities.MacroService;
import com.isssr5.entities.Result;
import com.isssr5.entities.ResultValue;
import com.isssr5.entities.ServiceUser;
import com.isssr5.exceptions.NotExistingMacroServiceException;
import com.isssr5.exceptions.NotExistingOperandException;
import com.isssr5.exceptions.NotExistingUserException;
import com.isssr5.exceptions.RequestedMacroServiceIsPrivateException;
import com.isssr5.exceptions.WrongDistributionException;
import com.isssr5.exceptions.WrongOperandNumberException;
import com.isssr5.exceptions.WrongScaleForMacroServiceId;
import com.isssr5.service.MacroServiceTransaction;
import com.isssr5.service.ServiceUserTransaction;

@Controller
@RequestMapping("/macroService")
public class MacroServiceController {
	@Autowired
	public MacroServiceController(
			MacroServiceTransaction macroServiceTransaction,
			ServiceUserTransaction serviceUserTransaction) {
		this.macroServiceTransaction = macroServiceTransaction;
		this.serviceUserTransaction = serviceUserTransaction;

	}

	private MacroServiceTransaction macroServiceTransaction;
	private ServiceUserTransaction serviceUserTransaction;



	@RequestMapping(value = "/{userId}/resolve/{msId}/{parameters}", method = RequestMethod.GET)
	public @ResponseBody
	Result ResolveMacroServiceUrl(@PathVariable String userId,
			@PathVariable String msId, @PathVariable String parameters)
			throws NotExistingUserException, NumberFormatException,
			NotExistingOperandException, WrongDistributionException,
			OutOfRangeException, WrongScaleForMacroServiceId,
			NotExistingMacroServiceException, WrongOperandNumberException,
			RequestedMacroServiceIsPrivateException {
		List<ResultValue> resultVList = new ArrayList<ResultValue>();
		ResolveMacroService(userId, msId, parameters, resultVList);
		Result result = new Result();
		result.setResultValueList(resultVList);
		return result;

	}

	public void ResolveMacroService(String user, String msId,
			String parameters, List<ResultValue> results)
			throws NotExistingUserException, NumberFormatException,
			NotExistingOperandException, WrongDistributionException,
			OutOfRangeException, WrongScaleForMacroServiceId,
			NotExistingMacroServiceException, WrongOperandNumberException, RequestedMacroServiceIsPrivateException {

		StringTokenizer st = new StringTokenizer(parameters, "_");
		List<String> parametersArray = new ArrayList<String>();
		while (st.hasMoreElements()) {
			parametersArray.add((st.nextElement().toString()));
		}
		MacroService ms = macroServiceTransaction.findMacroServiceById(msId);
		ServiceUser u = serviceUserTransaction.getUserById(user);
		if (u == null)
			throw new NotExistingUserException();
		if (ms == null) 
			throw new NotExistingMacroServiceException();
		if(ms.getIs_private()==true && !ms.getUser().getUserid().equals(user))
			throw new RequestedMacroServiceIsPrivateException();
		if(ms.getNumOperand()!=parametersArray.size())
			throw new WrongOperandNumberException();

		List<String> services = new ArrayList<String>();
		services = ms.getElementaryServices();
		for (int i = 0; i < services.size(); i++) {
			MacroService tempMs;
			tempMs = (macroServiceTransaction.findMacroServiceById(services
					.get(i)));

			if (tempMs == null) {// elementary service
				ResultValue rValue;
				List<Integer> tempOrder;
				tempOrder = ms.getOperationOrder().get(i).getParList();
				List<String> operandsOfElemService = new ArrayList<String>();
				for (int m = 0; m < tempOrder.size(); m++) {
					operandsOfElemService.add(parametersArray.get(tempOrder
							.get(m) - 1));

				}
				rValue = ResolveElementaryService(user, services.get(i),
						operandsOfElemService).getResultValueList().get(0);
				results.add(rValue);

			} else {
				List<Integer> tempOrder;
				String String1 = "";
				tempOrder = ms.getOperationOrder().get(i).getParList();
				for (int n = 0; n < tempOrder.size(); n++) {
					String1 += parametersArray.get(tempOrder.get(n) - 1);
					if (n < tempOrder.size() - 1)
						String1 += "_";
				}
				ResolveMacroService(user, tempMs.getIdCode(), String1, results);
			}

		}

	}

	public Result ResolveElementaryService(String user, String service,
			List<String> parametersArray) throws NumberFormatException,
			NotExistingUserException, NotExistingOperandException,
			WrongDistributionException, OutOfRangeException,
			WrongScaleForMacroServiceId {

		if (service.equals("ConfidenceInterval"))
			return ConfidenceIntervalController
					.getConfidenceInterval(user,
							Long.parseLong(parametersArray.get(0)),
							Double.parseDouble(parametersArray.get(1)));
		else if (service.equals("1SampleKolmogrov"))
			return NonParametricTestController
					.getOneSampleKolmogrov(user,
							Long.parseLong(parametersArray.get(0)),
							parametersArray.get(1));
		else if (service.equals("2SampleKolmogrov"))
			return NonParametricTestController
					.getTwoSampleKolmogrov(user,
							Long.parseLong(parametersArray.get(0)),
							Long.parseLong(parametersArray.get(0)));

		else if (service.equals("TTEST_STAT"))
			return ParametricTestController.getTTestStat(user,
					Long.parseLong(parametersArray.get(0)),
					Double.parseDouble(parametersArray.get(1)));

		else if (service.equals("TTEST_PVALUE_2SIDED"))
			return ParametricTestController.getTTestPValueTwoSided(user,
					Long.parseLong(parametersArray.get(0)),
					Double.parseDouble(parametersArray.get(1)));

		else if (service.equals("TTEST_ALPHA_2SIDED"))
			return ParametricTestController.getTTestAlphaTwoSided(user,
					Long.parseLong(parametersArray.get(0)),
					Double.parseDouble(parametersArray.get(1)),
					Double.parseDouble(parametersArray.get(2)));

		else if (service.equals("TTEST_1SIDED_LESSEQUAL"))
			return ParametricTestController.getTTestOneSidedLessEqual(user,
					Long.parseLong(parametersArray.get(0)),
					Double.parseDouble(parametersArray.get(1)),
					Double.parseDouble(parametersArray.get(2)));
		else if (service.equals("TTEST_1SIDED_GREATEREQUAL"))
			return ParametricTestController.getTTestOneSidedGreaterEqual(user,
					Long.parseLong(parametersArray.get(0)),
					Double.parseDouble(parametersArray.get(1)),
					Double.parseDouble(parametersArray.get(2)));
		else if (service.equals("TTEST_PAIRED_STAT"))
			return ParametricTestController.getTTestPairedStat(user,
					Long.parseLong(parametersArray.get(0)),
					Long.parseLong(parametersArray.get(1)));
		else if (service.equals("TTEST_PAIRED_PVALUE"))
			return ParametricTestController.getTTestPairedPValue(user,
					Long.parseLong(parametersArray.get(0)),
					Long.parseLong(parametersArray.get(1)));
		else if (service.equals("TTEST_PAIRED_ALPHA"))
			return ParametricTestController.getTTestPairedAlpha(user,
					Long.parseLong(parametersArray.get(0)),
					Long.parseLong(parametersArray.get(1)),
					Double.parseDouble(parametersArray.get(2)));

		else if (service.equals("TTEST_STAT2"))
			return ParametricTestController.getTTestStat(user,
					Long.parseLong(parametersArray.get(0)),
					Long.parseLong(parametersArray.get(1)));

		else if (service.equals("TTEST_PVALUE_2SIDED_2"))
			return ParametricTestController.getTTestPValueTwoSided(user,
					Long.parseLong(parametersArray.get(0)),
					Long.parseLong(parametersArray.get(1)));

		else if (service.equals("TTEST_ALPHA_2SIDED_2"))
			return ParametricTestController.getTTestAlphaTwoSided(user,
					Long.parseLong(parametersArray.get(0)),
					Long.parseLong(parametersArray.get(1)),
					Double.parseDouble(parametersArray.get(0)));

		else if (service.equals("TTEST_STAT_2SIDED_EQVAR"))
			return ParametricTestController.getTTestStatTwoSidedEqVar(user,
					Long.parseLong(parametersArray.get(0)),
					Long.parseLong(parametersArray.get(1)));

		else if (service.equals("TTEST_PVALUE_2SIDED_EQVAR"))
			return ParametricTestController.getTTestPValueTwoSidedEqVar(user,
					Long.parseLong(parametersArray.get(0)),
					Long.parseLong(parametersArray.get(0)));
		else if (service.equals("TTEST_ALPHA_2SIDED_EQVAR"))
			return ParametricTestController.getTTestAlphaTwoSidedEqVar(user,
					Long.parseLong(parametersArray.get(0)),
					Long.parseLong(parametersArray.get(1)),
					Double.parseDouble(parametersArray.get(2)));

		else if (service.equals("TTEST_1SIDED_LESSEQUAL_2_EQVAR"))
			return ParametricTestController
					.getTTestAlphaOneSidedLessEqualEqVar(user,
							Long.parseLong(parametersArray.get(0)),
							Long.parseLong(parametersArray.get(1)),
							Double.parseDouble(parametersArray.get(02)));

		else if (service.equals("TTEST_1SIDED_GREATEREQUAL_2_EQVAR"))
			return ParametricTestController
					.getTTestAlphaOneSidedGreaterEqualEqVar(user,
							Long.parseLong(parametersArray.get(0)),
							Long.parseLong(parametersArray.get(0)),
							Double.parseDouble(parametersArray.get(2)));

		else if (service.equals("ONEWAY_ANOVA_PVALUE_3OP"))
			return ParametricTestController.getOneWayAnovaPValue(user,
					Long.parseLong(parametersArray.get(0)),
					Long.parseLong(parametersArray.get(1)),
					Long.parseLong(parametersArray.get(2)));

		else if (service.equals("ONEWAY_ANOVA_ALPHA_3OP"))
			return ParametricTestController.getOneWayAnovaAlpha(user,
					Long.parseLong(parametersArray.get(0)),
					Long.parseLong(parametersArray.get(1)),
					Long.parseLong(parametersArray.get(2)),
					Double.parseDouble(parametersArray.get(3)));

		else if (service.equals("ONEWAY_ANOVA_PVALUE_4OP"))
			return ParametricTestController.getOneWayAnovaPValue(user,
					Long.parseLong(parametersArray.get(0)),
					Long.parseLong(parametersArray.get(1)),
					Long.parseLong(parametersArray.get(2)),
					Long.parseLong(parametersArray.get(3)));

		else if (service.equals("ONEWAY_ANOVA_ALPHA_4OP"))
			return ParametricTestController.getOneWayAnovaAlpha(user,
					Long.parseLong(parametersArray.get(0)),
					Long.parseLong(parametersArray.get(1)),
					Long.parseLong(parametersArray.get(2)),
					Long.parseLong(parametersArray.get(3)),
					Double.parseDouble(parametersArray.get(4)));

		else if (service.equals("mean"))
			return DescriptiveStatisticController.get_mean(user,
					Integer.parseInt(parametersArray.get(0)));

		else if (service.equals("variance"))
			return DescriptiveStatisticController.get_variance(user,
					Integer.parseInt(parametersArray.get(0)));

		else if (service.equals("min"))
			return DescriptiveStatisticController.get_minValue(user,
					Integer.parseInt(parametersArray.get(0)));

		else if (service.equals("max"))
			return DescriptiveStatisticController.get_maxValue(user,
					Integer.parseInt(parametersArray.get(0)));

		else if (service.equals("stdDeviation"))
			return DescriptiveStatisticController.get_standardDeviation(user,
					Integer.parseInt(parametersArray.get(0)));

		else if (service.equals("median"))
			return DescriptiveStatisticController.get_median(user,
					Integer.parseInt(parametersArray.get(0)));

		else if (service.equals("geometricMean"))
			return DescriptiveStatisticController.get_geometricMean(user,
					Integer.parseInt(parametersArray.get(0)));

		else
			return null;

	}

}
