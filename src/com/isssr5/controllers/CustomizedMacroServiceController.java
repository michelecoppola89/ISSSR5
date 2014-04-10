package com.isssr5.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.isssr5.controllers.ScaleController.Wrapper;
import com.isssr5.entities.DefaultServicesTable;
import com.isssr5.entities.MacroService;
import com.isssr5.entities.ParameterList;
import com.isssr5.entities.Scale;
import com.isssr5.entities.ServiceUser;
import com.isssr5.exceptions.NotExistingMacroServiceException;
import com.isssr5.exceptions.NullElementaryServiceListException;
import com.isssr5.exceptions.NullMacroServiceIdException;
import com.isssr5.exceptions.NullOperationOrderException;
import com.isssr5.exceptions.WrongOperandNumberException;
import com.isssr5.exceptions.WrongOperationOrderException;
import com.isssr5.service.MacroServiceTransaction;
import com.isssr5.service.ServiceUserTransaction;

@Controller
@RequestMapping("/customizedMacroService")
public class CustomizedMacroServiceController {

	private DefaultServicesTable defaultServiceTable;
	private MacroServiceTransaction macroServiceTransaction;

	private ServiceUserTransaction serviceUserTransaction;

	@Autowired
	public CustomizedMacroServiceController(
			MacroServiceTransaction macroServiceTransaction,
			ServiceUserTransaction serviceUserTransaction) {
		this.defaultServiceTable = DefaultServicesTable.getInstance();
		this.macroServiceTransaction = macroServiceTransaction;
		this.serviceUserTransaction = serviceUserTransaction;
	}

	private void checkForCustMacroService(MacroService ms)
			throws NullMacroServiceIdException,
			NullElementaryServiceListException, WrongOperandNumberException,
			NullOperationOrderException, WrongOperationOrderException,
			NotExistingMacroServiceException {

		if (ms.getIdCode() == null || ms.getIdCode().isEmpty())
			throw new NullMacroServiceIdException();
		if (ms.getElementaryServices() == null
				|| ms.getElementaryServices().isEmpty())
			throw new NullElementaryServiceListException();
		if (ms.getNumOperand() == 0)
			throw new WrongOperandNumberException();
		if (ms.getOperationOrder() == null || ms.getOperationOrder().isEmpty())
			throw new NullOperationOrderException();
		if (ms.getOperationOrder().size() != ms.getNumOperand())
			throw new WrongOperandNumberException();

		for (int i = 0; i < ms.getOperationOrder().size(); i++) {
			if (ms.getOperationOrder().get(i)
					.checkParameterList(ms.getNumOperand()) == false
					|| ms.getOperationOrder().get(i).getParList() == null)
				throw new WrongOperationOrderException();
		}

		for (int i = 0; i < ms.getOperationOrder().size(); i++) {
			String el = ms.getElementaryServices().get(i);
			MacroService msTemp = defaultServiceTable.getTable().get(el);
			System.out.println("elementary service cercato" + el);

			if (msTemp == null) {
				msTemp = macroServiceTransaction.findMacroServiceById(el);
				if (msTemp == null)
					throw new NotExistingMacroServiceException();
			}

			if (ms.getOperationOrder().get(i)
					.checkParameterListSize(msTemp.getNumOperand()) == false)
				throw new WrongOperandNumberException();
		}

	}

	// @RequestMapping(value = "/testCustomizedMacroService", method =
	// RequestMethod.GET)
	// public @ResponseBody
	// MacroService testMacroservice() {
	// String idCode = "CUST1";
	//
	// MacroService mean = new MacroService("MEAN", null,
	// new ArrayList<MacroService>());
	// MacroService var = new MacroService("VAR", null,
	// new ArrayList<MacroService>());
	// ArrayList<MacroService> al = new ArrayList<MacroService>();
	// al.add(mean);
	// al.add(var);
	//
	// MacroService ms = new MacroService(idCode, null, al);
	// ms.setNumOperand(2);
	//
	// ArrayList<Integer> op1 = new ArrayList<Integer>();
	// op1.add(1);
	// ArrayList<Integer> op2 = new ArrayList<Integer>();
	// op2.add(2);
	// ParameterList l1 = new ParameterList(op1);
	// ParameterList l2 = new ParameterList(op2);
	// ArrayList<ParameterList> opList = new ArrayList<ParameterList>();
	// opList.add(l1);
	// opList.add(l2);
	//
	// ms.setOperationOrder(opList);
	//
	// return ms;
	//
	// }

	@RequestMapping(value = "/echoCreateCustomMacroService", method = RequestMethod.POST)
	public @ResponseBody
	String echoCreateMacroservice(@RequestBody MacroService ms)
			throws NullMacroServiceIdException,
			NullElementaryServiceListException, WrongOperandNumberException,
			NullOperationOrderException, WrongOperationOrderException,
			NotExistingMacroServiceException {

		checkForCustMacroService(ms);
		String st = ms.printMacroService();

		return st;
	}

	@RequestMapping(value = "/{user}/createCustomizedMacroService", method = RequestMethod.POST)
	public @ResponseBody
	String createMacroservice(@RequestBody MacroService ms,
			@PathVariable String user) throws NullMacroServiceIdException,
			NullElementaryServiceListException, WrongOperandNumberException,
			NullOperationOrderException, WrongOperationOrderException,
			NotExistingMacroServiceException {

		checkForCustMacroService(ms);
		ms.setUser(serviceUserTransaction.getUserById(user));
		macroServiceTransaction.addMacroService(ms);

		String st = ms.printMacroService();

		return st;
	}

	@RequestMapping(value = "/{user}/getCustomizeMacroServiceById/{macroServiceId}", method = RequestMethod.GET)
	public @ResponseBody
	MacroService getCustomizedMacroServiceById(@PathVariable String user,
			@PathVariable String macroServiceId) {
		return macroServiceTransaction.findMacroServiceById(macroServiceId);
	}
	
	@XmlRootElement(name = "resumeCustomizedMacroService")
	static class Wrapper {
		private List<MacroService> customizedMacroServiceList;

		public Wrapper(List<MacroService> customizedMacroServiceList) {
			this.customizedMacroServiceList = customizedMacroServiceList;

		}

		public Wrapper() {
		}

		public List<MacroService> getCustomizedMacroServiceList() {
			return customizedMacroServiceList;
		}

		public void setCustomizedMacroServiceList(List<MacroService> customizedMacroServiceList) {
			this.customizedMacroServiceList = customizedMacroServiceList;
		}
	}

	@RequestMapping(value = "/{user}/getAllCustomizedMacroService", method = RequestMethod.GET)
	public @ResponseBody
	Wrapper getAllScales(@PathVariable String user) {
		ServiceUser u = serviceUserTransaction.getUserById(user);

		return new Wrapper(u.getServiceList());
	}


}
