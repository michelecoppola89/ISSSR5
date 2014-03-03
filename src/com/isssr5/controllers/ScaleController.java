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

import com.isssr5.entities.Domain;
import com.isssr5.entities.EnumerateDomain;
import com.isssr5.entities.IntervalDomain;
import com.isssr5.entities.Scale;
import com.isssr5.exceptions.DomainException;
import com.isssr5.exceptions.EnumerateDomainException;
import com.isssr5.exceptions.IntervalDomainException;
import com.isssr5.exceptions.NullDomainException;

@Controller
@RequestMapping("/scale")
public class ScaleController {
	static String NOMINAL = "NominalScale";
	static String INTERVAL = "IntervalScale";
	static String RATIO = "RatioScale";
	static String ORDINAL = "OrdinalScale";
	static String ENUMDOM = "EnumeralDomain";
	static String RATIODOM = "RatioDomain";

	@RequestMapping(value = "/nominalScale", method = RequestMethod.POST)
	public @ResponseBody
	String createNominalScale(@RequestBody Scale scale) throws DomainException,
			EnumerateDomainException, NullDomainException {
		if (scale.getDom() == null)
			throw new NullDomainException();
		if (scale.getDom() instanceof IntervalDomain)
			throw new DomainException();
		String string = "";
		EnumerateDomain d = (EnumerateDomain) scale.getDom();
		if(d.getScalePoints()==null)
			throw new NullDomainException();
		if ((d.getScalePoints().size()==1) && (d.getScalePoints().get(0).equals("")))
			throw new EnumerateDomainException();
		string += "scaleType: " + scale.getType() + "\nscalePoint: "
				+ d.printScalePoint();
		return string;

	}

	@RequestMapping(value = "/ordinalScale", method = RequestMethod.POST)
	public @ResponseBody
	String createOrdinalScale(@RequestBody Scale scale) throws DomainException,
			NullDomainException, EnumerateDomainException {
		if (scale.getDom() == null)
			throw new NullDomainException();
		if (scale.getDom() instanceof IntervalDomain)
			throw new DomainException();
		String string = "";
		EnumerateDomain d = (EnumerateDomain) scale.getDom();
		if(d.getScalePoints()==null)
			throw new NullDomainException();
		if ((d.getScalePoints().size()==1) && (d.getScalePoints().get(0).equals("")) )
			throw new EnumerateDomainException();
		string += "scaleType: " + scale.getType() + "\nscalePoint: "
				+ d.printScalePoint();
		return string;

	}

	@RequestMapping(value = "/ratioScale", method = RequestMethod.POST)
	public @ResponseBody
	String createRatioScale(@RequestBody Scale scale) throws DomainException,
			IntervalDomainException, NullDomainException {
		if (scale.getDom() == null)
			throw new NullDomainException();
		if (scale.getDom() instanceof EnumerateDomain)
			throw new DomainException();
		String string = "";
		IntervalDomain d = (IntervalDomain) scale.getDom();
		if(d.getMin()==null || d.getMax()==null)
			throw new NullDomainException();
		if (d.getMax() <= d.getMin())
			throw new IntervalDomainException();
		string += "scaleType: " + scale.getType() + "\nmin: " + d.getMin()
				+ "\nmax: " + d.getMax();
		return string;

	}

	@RequestMapping(value = "/intervalScale", method = RequestMethod.POST)
	public @ResponseBody
	String createIntervalScale(@RequestBody Scale scale)
			throws NullDomainException, DomainException, IntervalDomainException {
		if (scale.getDom() == null)
			throw new NullDomainException();
		if (scale.getDom() instanceof EnumerateDomain)
			throw new DomainException();
		String string = "";
		IntervalDomain d = (IntervalDomain) scale.getDom();
		if(d.getMin()==null || d.getMax()==null)
			throw new NullDomainException();
		if (d.getMax() <= d.getMin())
			throw new IntervalDomainException();
		string += "scaleType: " + scale.getType() + "\nmin: " + d.getMin()
				+ "\nmax: " + d.getMax();
		return string;

	}

	@RequestMapping(value = "/testNominalScale", method = RequestMethod.GET)
	public @ResponseBody
	Scale testNominalScale() {
		ArrayList<String> array = new ArrayList<String>();
		array.add("Rosso");
		array.add("Verde");
		array.add("Amaranto");
		Domain dom = new EnumerateDomain(ENUMDOM, array);
		Scale s = new Scale(NOMINAL, dom);
		return s;
	}

	@RequestMapping(value = "/testRatioScale", method = RequestMethod.GET)
	public @ResponseBody
	Scale testRatioScale() {
		Domain dom = new IntervalDomain(RATIODOM, 1.0, 12.0);
		Scale s = new Scale(RATIO, dom);
		return s;
	}

}
