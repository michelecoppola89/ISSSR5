package com.isssr5.controllers;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.isssr5.entities.DefaultServicesTable;
import com.isssr5.entities.MacroService;
import com.isssr5.entities.Operand;
import com.isssr5.exceptions.NotExistingMacroServiceException;
import com.isssr5.exceptions.NullElementaryServiceListException;
import com.isssr5.exceptions.NullOperandException;
import com.isssr5.exceptions.NullOperandModeException;
import com.isssr5.exceptions.NullOperandTypeException;

@Controller
@RequestMapping("/macroService")
public class MacroServiceController {
	private static DefaultServicesTable dataTable=new DefaultServicesTable();
	

	private void checkForMacroServiceAcquisition(MacroService ms)
			throws NullOperandException, NullOperandTypeException,
			NullOperandModeException {
		if ((ms.getOperandList() == null) || (ms.getOperandList().size() == 0)) {

			throw new NullOperandException();
		}

		for (int i = 0; i < ms.getOperandList().size(); i++) {
			if ((ms.getOperandList().get(i).getDataType() == null)
					|| (!(ms.getOperandList().get(i).getDataType()
							.equals("String")) && !(ms.getOperandList().get(i)
							.getDataType().equals("Double")))) {
				throw new NullOperandTypeException();

			}

			if ((!(ms.getOperandList().get(i).getOperandMode().equals("F"))
					&& !(ms.getOperandList().get(i).getOperandMode()
							.equals("D")) && !(ms.getOperandList().get(i)
					.getOperandMode().equals("E")))) {
				throw new NullOperandModeException();

			}

		}

		// if((ms.getElementaryServices()==null)||(ms.getElementaryServices().size()==0)){
		// throw new NullElementaryServiceListException();
		//
		// }

	}

	@RequestMapping(value = "/testMacroService", method = RequestMethod.GET)
	public @ResponseBody
	MacroService testMacroservice() {
		String idCode = "MEAN";
		Operand op1 = new Operand("Double", null, "E");
		ArrayList<MacroService> al = new ArrayList<MacroService>();
		ArrayList<Operand> opList = new ArrayList<Operand>();
		opList.add(op1);
		MacroService ms = new MacroService(idCode, opList, al);
		ms.setNumOperand(1);
		return ms;

	}

	@RequestMapping(value = "/createMacroService", method = RequestMethod.POST)
	public @ResponseBody
	String createMacroservice(@RequestBody MacroService ms)
			throws NullOperandException, NullOperandTypeException,
			NullOperandModeException, NullElementaryServiceListException,
			NotExistingMacroServiceException {

		checkForMacroServiceAcquisition(ms);
		
		ms.decodeMacroService(dataTable);
		String st = "";
		if(ms.getElementaryServices()!=null)
		st += "MacroService ID: " + ms.getIdCode() + "\nElementary Service: "
				+ ms.printElementaryService() + "\nOperandList:\n"
				+ ms.printOperandList();
		else
			st += "MacroService ID: " + ms.getIdCode() + "\nOperandList:\n"
					+ ms.printOperandList();

		return st;
	}

}
