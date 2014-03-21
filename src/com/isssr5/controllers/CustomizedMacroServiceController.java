package com.isssr5.controllers;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.isssr5.entities.MacroService;
import com.isssr5.entities.ParameterList;
import com.isssr5.exceptions.NullElementaryServiceListException;
import com.isssr5.exceptions.NullMacroServiceIdException;
import com.isssr5.exceptions.NullOperationOrderException;
import com.isssr5.exceptions.WrongOperandNumberException;
import com.isssr5.exceptions.WrongOperationOrderException;

@Controller
@RequestMapping("/customizedMacroService")
public class CustomizedMacroServiceController {

	private void checkForCustMacroService(MacroService ms)
			throws NullMacroServiceIdException,
			NullElementaryServiceListException, WrongOperandNumberException,
			NullOperationOrderException, WrongOperationOrderException {

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
			MacroService el = ms.getElementaryServices().get(i);
			if (ms.getOperationOrder().get(i)
					.checkParameterListSize(el.getNumOperand()) == false)
				throw new WrongOperandNumberException();
		}

	}

	@RequestMapping(value = "/testCustomizedMacroService", method = RequestMethod.GET)
	public @ResponseBody
	MacroService testMacroservice() {
		String idCode = "CUST1";

		MacroService mean = new MacroService("MEAN", null,
				new ArrayList<MacroService>());
		MacroService var = new MacroService("VAR", null,
				new ArrayList<MacroService>());
		ArrayList<MacroService> al = new ArrayList<MacroService>();
		al.add(mean);
		al.add(var);

		MacroService ms = new MacroService(idCode, null, al);
		ms.setNumOperand(2);

		ArrayList<Integer> op1 = new ArrayList<Integer>();
		op1.add(1);
		ArrayList<Integer> op2 = new ArrayList<Integer>();
		op2.add(2);
		ParameterList l1 = new ParameterList(op1);
		ParameterList l2 = new ParameterList(op2);
		ArrayList<ParameterList> opList = new ArrayList<ParameterList>();
		opList.add(l1);
		opList.add(l2);

		ms.setOperationOrder(opList);

		return ms;

	}

	@RequestMapping(value = "/createCustomMacroService", method = RequestMethod.POST)
	public @ResponseBody
	String createMacroservice(@RequestBody MacroService ms)
			throws NullMacroServiceIdException,
			NullElementaryServiceListException, WrongOperandNumberException,
			NullOperationOrderException, WrongOperationOrderException {

		
		checkForCustMacroService(ms);
		String st = ms.printMacroService();

		return st;
	}

}
