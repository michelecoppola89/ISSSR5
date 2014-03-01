package com.isssr5.controllers;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.isssr5.entities.EnumerateDomain;
import com.isssr5.entities.NominalScale;

@Controller
@RequestMapping("/scale")
public class ScaleController {
	@RequestMapping(value="/echo", method=RequestMethod.GET)
	public @ResponseBody NominalScale echoMethod(ModelMap model){
		
		ArrayList<String> sp= new ArrayList<String>();
		sp.add("Rosso");
		sp.add("Verde");
		sp.add("Bianco");
		
		EnumerateDomain ed= new EnumerateDomain(sp);
		
		NominalScale ns = new NominalScale("Nominal", ed);	
		
		return ns;
	}

	

}
