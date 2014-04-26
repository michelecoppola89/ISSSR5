package com.isssr5.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

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
import com.isssr5.exceptions.NotExistingOperandException;
import com.isssr5.exceptions.NotExistingUserException;
import com.isssr5.exceptions.WrongDistributionException;
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

	// private static DefaultServicesTable dataTable = DefaultServicesTable
	// .getInstance();

	@RequestMapping(value = "/{userId}/resolve/{msId}/{parameters}", method = RequestMethod.GET)
	public @ResponseBody
	Result ResolveMacroServiceUrl(@PathVariable String userId,
			@PathVariable String msId, @PathVariable String parameters)
			throws NotExistingUserException, NumberFormatException, NotExistingOperandException, WrongDistributionException {
		List <ResultValue> resultVList= new ArrayList<ResultValue>();
		ResolveMacroService(userId, msId, parameters, resultVList);
		Result result=new Result();
		result.setResultValueList(resultVList);
		return result;

	}

	public void ResolveMacroService(String user, String msId,
			String parameters, List<ResultValue> results)
			throws NotExistingUserException, NumberFormatException, NotExistingOperandException, WrongDistributionException {
		// List<Long> idOperands = new ArrayList<Long>();
		StringTokenizer st = new StringTokenizer(parameters, ",");
		List<String> parametersArray = new ArrayList<String>();
		while (st.hasMoreElements()) {
			parametersArray.add((st.nextElement().toString()));
		}
		MacroService ms = macroServiceTransaction.findMacroServiceById(user);
		ServiceUser u = serviceUserTransaction.getUserById(user);
		if (u == null)
			throw new NotExistingUserException();

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
							.get(m) + 1));

				}
				rValue = ResolveElementaryService(user, services.get(i),
						operandsOfElemService).getResultValueList().get(0);
				results.add(rValue);

			} else {
				for (int l = 0; l < tempMs.getElementaryServices().size(); l++) {
					List<Integer> tempOrder;
					String String1 = "";
					tempOrder = ms.getOperationOrder().get(i).getParList();
					for (int n = 0; n < tempOrder.size(); n++) {
						String1 += parametersArray.get(tempOrder.get(n) + 1);
						if (n < tempOrder.size() - 1)
							String1 += ",";

					}
					ResolveMacroService(user, msId, String1, results);
				}

			}

		}

	

	}

	public Result ResolveElementaryService(String user, String service,
			List<String> parametersArray) throws NumberFormatException,
			NotExistingUserException, NotExistingOperandException,
			WrongDistributionException {

		if (service.equals("ConfidenceInterval"))
			return Kolmogorov_ConfidenceIntervalController
					.getConfidenceInterval(user,
							Long.parseLong(parametersArray.get(0)),
							Double.parseDouble(parametersArray.get(1)));
		else if (service.equals("1SampleKolmogrov"))
			return Kolmogorov_ConfidenceIntervalController
					.getOneSampleKolmogrov(user,
							Long.parseLong(parametersArray.get(0)),
							parametersArray.get(1));
		else if (service.equals("2SampleKolmogrov"))
			return Kolmogorov_ConfidenceIntervalController
					.getTwoSampleKolmogrov(user,
							Long.parseLong(parametersArray.get(0)),
							Long.parseLong(parametersArray.get(0)));
		else if (service.equals("TTEST_STAT"))
			return ParametricTestController.getTTestAlphaTwoSided(user,
					Long.parseLong(parametersArray.get(0)),
					Double.parseDouble(parametersArray.get(1)),
					Double.parseDouble(parametersArray.get(2)));
		else if (service.equals("TTEST_PVALUE_2SIDED"))
			return ParametricTestController.getTTestPValueTwoSided(user,
					Long.parseLong(parametersArray.get(0)),
					Double.parseDouble(parametersArray.get(1)));
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
					Double.parseDouble(parametersArray.get(1)));

		else if (service.equals("TTEST_PVALUE_2SIDED_2"))
			return ParametricTestController.getTTestPValueTwoSided(user,
					Long.parseLong(parametersArray.get(0)),
					Long.parseLong(parametersArray.get(1)));

		else if (service.equals("TTEST_ALPHA_2SIDED_2"))
			return ParametricTestController.getTTestAlphaTwoSided(user,
					Long.parseLong(parametersArray.get(0)),
					Long.parseLong(parametersArray.get(1)),
					Double.parseDouble(parametersArray.get(0)));
		// GLI ULTIMI TRE METODI ESISTEVANO DOPPI SIA CON LONG CHE CON DOUBLE

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
					Long.parseLong(parametersArray.get(2)),
					Long.parseLong(parametersArray.get(3)));

		else if (service.equals("ONEWAY_ANOVA_ALPHA_3OP"))
			return ParametricTestController.getOneWayAnovaAlpha(user,
					Long.parseLong(parametersArray.get(0)),
					Long.parseLong(parametersArray.get(1)),
					Long.parseLong(parametersArray.get(2)),
					Long.parseLong(parametersArray.get(3)),
					Double.parseDouble(parametersArray.get(4)));

		else if (service.equals("ONEWAY_ANOVA_PVALUE_4OP"))
			return ParametricTestController.getOneWayAnovaPValue(user,
					Long.parseLong(parametersArray.get(0)),
					Long.parseLong(parametersArray.get(1)),
					Long.parseLong(parametersArray.get(2)));
		// ULTIME TRE HANNO DUE METODI UGUALI

		else if (service.equals("ONEWAY_ANOVA_ALPHA_4OP"))
			return ParametricTestController.getOneWayAnovaAlpha(user,
					Long.parseLong(parametersArray.get(0)),
					Long.parseLong(parametersArray.get(1)),
					Long.parseLong(parametersArray.get(2)),
					Long.parseLong(parametersArray.get(3)),
					Double.parseDouble(parametersArray.get(4)));

		else
			return null;

	}

	// private void checkForMacroServiceAcquisition(MacroService ms)
	// throws NullOperandException, NullOperandTypeException,
	// NullOperandModeException {
	// if ((ms.getOperandList() == null) || (ms.getOperandList().size() == 0)) {
	//
	// throw new NullOperandException();
	// }
	//
	// for (int i = 0; i < ms.getOperandList().size(); i++) {
	// if ((ms.getOperandList().get(i).getDataType() == null)
	// || (!(ms.getOperandList().get(i).getDataType()
	// .equals("String")) && !(ms.getOperandList().get(i)
	// .getDataType().equals("Double")))) {
	// throw new NullOperandTypeException();
	//
	// }
	//
	// if ((!(ms.getOperandList().get(i).getOperandMode().equals("F"))
	// && !(ms.getOperandList().get(i).getOperandMode()
	// .equals("D")) && !(ms.getOperandList().get(i)
	// .getOperandMode().equals("E")))) {
	// throw new NullOperandModeException();
	//
	// }
	//
	// }

	// if((ms.getElementaryServices()==null)||(ms.getElementaryServices().size()==0)){
	// throw new NullElementaryServiceListException();
	//
	// }

	// }

	// @RequestMapping(value = "/testMacroService", method = RequestMethod.GET)
	// public @ResponseBody
	// MacroService testMacroservice() {
	// String idCode = "MEAN";
	// Operand op1 = new Operand("Double", null, "E");
	// ArrayList<MacroService> al = new ArrayList<MacroService>();
	// ArrayList<Operand> opList = new ArrayList<Operand>();
	// opList.add(op1);
	// MacroService ms = new MacroService(idCode, opList, al);
	// ms.setNumOperand(1);
	// return ms;
	//
	// }

	// @RequestMapping(value = "/createMacroService", method =
	// RequestMethod.POST)
	// public @ResponseBody
	// String createMacroservice(@RequestBody MacroService ms)
	// throws NullOperandException, NullOperandTypeException,
	// NullOperandModeException, NullElementaryServiceListException,
	// NotExistingMacroServiceException {
	//
	// checkForMacroServiceAcquisition(ms);
	//
	// ms.decodeMacroService(dataTable);
	// String st = "";
	// if (ms.getElementaryServices() != null) {
	// // st += "MacroService ID: " + ms.getIdCode() +
	// // "\nElementary Service: "
	// // + ms.printElementaryService() + "\nOperandList:\n"
	// // + ms.printOperandList();
	// st += ms.printMacroService();
	// st += "\nOperandList:\n" + ms.printOperandList();
	// } else
	// st += "MacroService ID: " + ms.getIdCode() + "\nOperandList:\n"
	// + ms.printOperandList();
	//
	// return st;
	// }

}
