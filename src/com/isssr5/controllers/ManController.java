package com.isssr5.controllers;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.isssr5.entities.MacroService;
import com.isssr5.entities.Operand;
@Controller
@RequestMapping("/man")
public class ManController {

	@RequestMapping(value = "/man", method = RequestMethod.GET)
	public String getMan() {
		return "WEB-INF/resources/man.html";

	}

}
