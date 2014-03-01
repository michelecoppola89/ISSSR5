package com.isssr5.controllers;

import java.io.IOException;
import java.util.ArrayList;


import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.isssr5.entities.EnumerateDomain;
import com.isssr5.entities.IntervalDomain;
import com.isssr5.entities.IntervalScale;
import com.isssr5.entities.NominalScale;
import com.isssr5.entities.OrdinalScale;
import com.isssr5.entities.RatioScale;

@Controller
@RequestMapping("/scale")
public class ScaleController {
	@RequestMapping(value = "/echo1", method = RequestMethod.GET)
	public @ResponseBody
	NominalScale echoMethod1(ModelMap model) {

		ArrayList<String> sp = new ArrayList<String>();
		sp.add("Rosso");
		sp.add("Verde");
		sp.add("Bianco");

		EnumerateDomain ed = new EnumerateDomain(sp);

		NominalScale ns = new NominalScale("Nominal", ed);

		return ns;
	}

	@RequestMapping(value = "/echo2", method = RequestMethod.GET)
	public @ResponseBody
	IntervalScale echoMethod2(ModelMap model) {

		IntervalScale ns;

		IntervalDomain d = new IntervalDomain(1.0, 2.0);
		ns = new IntervalScale("interval", d);

		return ns;
	}

	@RequestMapping(value = "/nominalScale", method = RequestMethod.POST)
	public @ResponseBody
	String nominalScale(@RequestBody NominalScale ns) {

		String r = "tipo: " + ns.getType() + "\n" + "scalePoint: "
				+ ns.getScalePointList().printScalePointElement();

		return r;
	}

	@RequestMapping(value = "/ordinalScale", method = RequestMethod.POST)
	public @ResponseBody
	String ordinalScale(@RequestBody OrdinalScale ns) {

		String r = "tipo: " + ns.getType() + "\n"
				+ "scalePoint(ordine crescente): "
				+ ns.getScalePointList().printScalePointElement();

		return r;
	}
	
	@RequestMapping(value = "/intervalScale", method = RequestMethod.POST)
	public @ResponseBody
	String intervalScale(@RequestBody IntervalScale ns, HttpServletResponse response) throws IOException {
		
	
			
		if (ns.getScaleInterval().getMax() <= ns.getScaleInterval().getMin()) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return "min >= max. Bad Input!!!";
		}
		
		String r = "tipo: " + ns.getType() + "\n" + "interval: "
				+ ns.getScaleInterval().getMin() + " - "
				+ ns.getScaleInterval().getMax();

		return r;
		

	}

	@RequestMapping(value = "/ratioScale", method = RequestMethod.POST)
	public @ResponseBody
	String ratioScale(@RequestBody RatioScale ns) {

		String r = "tipo: " + ns.getType() + "\n" + "interval(ratio): "
				+ ns.getScaleInterval().getMin() + " - "
				+ ns.getScaleInterval().getMax();

		return r;
	}

}
