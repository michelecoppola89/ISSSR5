package com.isssr5.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/manual")
public class ManController {

	@RequestMapping(value = "/man", method = RequestMethod.GET)
	public String getMan() {
		return "man";

	}

}
