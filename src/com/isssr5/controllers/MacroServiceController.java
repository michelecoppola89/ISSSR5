package com.isssr5.controllers;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.isssr5.entities.MacroService;
import com.isssr5.entities.Operand;

@Controller
@RequestMapping("/macroService")
public class MacroServiceController {

	@RequestMapping(value = "/testMacroService", method = RequestMethod.GET)
	public @ResponseBody
	MacroService testMacroservice() {
		String idCode = "MEAN";
		Operand op1 = new Operand("Double", null, "E");
		ArrayList<String> al = new ArrayList<String>();
		ArrayList<Operand> opList = new ArrayList<Operand>();
		opList.add(op1);
		al.add("MEAN");
		MacroService ms = new MacroService(idCode, opList, al);
		return ms;

	}

	@RequestMapping(value = "/createMacroService", method = RequestMethod.POST)
	public @ResponseBody
	String createMacroservice(@RequestBody MacroService ms) {
		String st = "";
		st += "MacroService ID: " + ms.getIdCode() + "\nElementary Service: "
				+ ms.printElementaryService() + "\nOperandList:\n"
				+ ms.printOperandList();

		return st;
	}

}
