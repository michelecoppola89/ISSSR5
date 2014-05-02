package com.isssr5.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.isssr5.entities.Domain;
import com.isssr5.entities.EnumerateDomain;
import com.isssr5.entities.IntervalDomain;
import com.isssr5.entities.Scale;
import com.isssr5.entities.ServiceUser;
import com.isssr5.exceptions.DomainException;
import com.isssr5.exceptions.EnumerateDomainException;
import com.isssr5.exceptions.IntervalDomainException;
import com.isssr5.exceptions.NotExistingScaleException;
import com.isssr5.exceptions.NotExistingUserException;
import com.isssr5.exceptions.NullDomainException;
import com.isssr5.service.ScaleTransaction;
import com.isssr5.service.ServiceUserTransaction;

@Controller
@RequestMapping("/scale")
public class ScaleController {
	static String NOMINAL = "NominalScale";
	static String INTERVAL = "IntervalScale";
	static String RATIO = "RatioScale";
	static String ORDINAL = "OrdinalScale";
	static String ENUMDOM = "EnumeralDomain";
	static String RATIODOM = "RatioDomain";

	private ScaleTransaction scaleTransaction;
	private ServiceUserTransaction serviceUserTransaction;

	@Autowired
	public ScaleController(ScaleTransaction scaleTransaction,
			ServiceUserTransaction serviceUserTransaction) {
		this.scaleTransaction = scaleTransaction;
		this.serviceUserTransaction = serviceUserTransaction;
	}

	@RequestMapping(value = "/{user}/nominalScale", method = RequestMethod.POST)
	public @ResponseBody
	String createNominalScale(@RequestBody Scale scale,
			@PathVariable String user, HttpServletResponse response)
			throws DomainException, EnumerateDomainException,
			NullDomainException, NotExistingUserException {
		if (scale.getDom() == null)
			throw new NullDomainException();
		if (scale.getDom() instanceof IntervalDomain)
			throw new DomainException();
		String string = "";
		EnumerateDomain d = (EnumerateDomain) scale.getDom();
		if (d.getScalePoints() == null)
			throw new NullDomainException();
		if ((d.getScalePoints().size() == 1)
				&& (d.getScalePoints().get(0).equals("")))
			throw new EnumerateDomainException();
		ServiceUser u = serviceUserTransaction.getUserById(user);
		if (u == null)
			throw new NotExistingUserException();
		scale.setUser(u);
		scale.getDom().setScale(scale);
		scaleTransaction.addScale(scale);

		response.setHeader("Location", "/scale/" + u.getUserid()
				+ "/getScaleById/" + scale.getId());

		return string;

	}

	@RequestMapping(value = "/{user}/ordinalScale", method = RequestMethod.POST)
	public @ResponseBody
	String createOrdinalScale(@RequestBody Scale scale,
			@PathVariable String user, HttpServletResponse response)
			throws DomainException, NullDomainException,
			EnumerateDomainException, NotExistingUserException {
		if (scale.getDom() == null)
			throw new NullDomainException();
		if (scale.getDom() instanceof IntervalDomain)
			throw new DomainException();
		String string = "";
		EnumerateDomain d = (EnumerateDomain) scale.getDom();
		if (d.getScalePoints() == null)
			throw new NullDomainException();
		if ((d.getScalePoints().size() == 1)
				&& (d.getScalePoints().get(0).equals("")))
			throw new EnumerateDomainException();

		// string += "scaleType: " + scale.getType() + "\nscalePoint: "
		// + d.printScalePoint();

		ServiceUser u = serviceUserTransaction.getUserById(user);
		if (u == null)
			throw new NotExistingUserException();
		scale.setUser(u);
		scale.getDom().setScale(scale);
		scaleTransaction.addScale(scale);

		response.setHeader("Location", "/scale/" + u.getUserid()
				+ "/getScaleById/" + scale.getId());

		return string;

	}

	@RequestMapping(value = "/{user}/ratioScale", method = RequestMethod.POST)
	public @ResponseBody
	String createRatioScale(@RequestBody Scale scale,
			@PathVariable String user, HttpServletResponse response)
			throws DomainException, IntervalDomainException,
			NullDomainException, NotExistingUserException {

		if (scale.getDom() == null)
			throw new NullDomainException();
		if (scale.getDom() instanceof EnumerateDomain)
			throw new DomainException();
		String string = "";
		IntervalDomain d = (IntervalDomain) scale.getDom();
		if (d.getMin() == null || d.getMax() == null)
			throw new NullDomainException();
		if (d.getMax() <= d.getMin())
			throw new IntervalDomainException();

		// string += "scaleType: " + scale.getType() + "\nmin: " + d.getMin()
		// + "\nmax: " + d.getMax();

		ServiceUser u = serviceUserTransaction.getUserById(user);
		if (u == null)
			throw new NotExistingUserException();
		scale.setUser(u);
		scale.getDom().setScale(scale);
		scaleTransaction.addScale(scale);

		response.setHeader("Location", "/scale/" + u.getUserid()
				+ "/getScaleById/" + scale.getId());

		return string;

	}

	@RequestMapping(value = "/{user}/intervalScale", method = RequestMethod.POST)
	public @ResponseBody
	String createIntervalScale(@RequestBody Scale scale,
			@PathVariable String user, HttpServletResponse response)
			throws NullDomainException, DomainException,
			IntervalDomainException, NotExistingUserException {
		if (scale.getDom() == null)
			throw new NullDomainException();
		if (scale.getDom() instanceof EnumerateDomain)
			throw new DomainException();
		String string = "";
		IntervalDomain d = (IntervalDomain) scale.getDom();
		if (d.getMin() == null || d.getMax() == null)
			throw new NullDomainException();
		if (d.getMax() <= d.getMin())
			throw new IntervalDomainException();

		// string += "scaleType: " + scale.getType() + "\nmin: " + d.getMin()
		// + "\nmax: " + d.getMax();

		ServiceUser u = serviceUserTransaction.getUserById(user);
		if (u == null)
			throw new NotExistingUserException();
		scale.setUser(u);
		scale.getDom().setScale(scale);
		scaleTransaction.addScale(scale);

		response.setHeader("Location", "/scale/" + u.getUserid()
				+ "/getScaleById/" + scale.getId());

		return string;

	}

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
		if (d.getScalePoints() == null)
			throw new NullDomainException();
		if ((d.getScalePoints().size() == 1)
				&& (d.getScalePoints().get(0).equals("")))
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
		if (d.getScalePoints() == null)
			throw new NullDomainException();
		if ((d.getScalePoints().size() == 1)
				&& (d.getScalePoints().get(0).equals("")))
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
		if (d.getMin() == null || d.getMax() == null)
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
			throws NullDomainException, DomainException,
			IntervalDomainException {
		if (scale.getDom() == null)
			throw new NullDomainException();
		if (scale.getDom() instanceof EnumerateDomain)
			throw new DomainException();
		String string = "";
		IntervalDomain d = (IntervalDomain) scale.getDom();
		if (d.getMin() == null || d.getMax() == null)
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

	@RequestMapping(value = "/{user}/getScaleById/{scaleId}", method = RequestMethod.GET)
	public @ResponseBody
	Scale getScaleById(@PathVariable String user, @PathVariable long scaleId)
			throws NotExistingUserException, NotExistingScaleException {
		ServiceUser u = serviceUserTransaction.getUserById(user);
		if (u == null)
			throw new NotExistingUserException();
		Scale s = scaleTransaction.findScaleById(scaleId);
		if (s == null) {
			throw new NotExistingScaleException();
		}
		if (!s.getUser().getUserid().equals(user))
			throw new NotExistingScaleException();
		return s;
	}

	@RequestMapping(value = "/testRatioScale", method = RequestMethod.GET)
	public @ResponseBody
	Scale testRatioScale() {
		Domain dom = new IntervalDomain(RATIODOM, 1.0, 12.0);
		Scale s = new Scale(RATIO, dom);
		return s;
	}

	@XmlRootElement(name = "resumeScale")
	static class Wrapper {
		private List<Scale> scaleList;

		public Wrapper(List<Scale> scaleList) {
			this.scaleList = scaleList;

		}

		public Wrapper() {
		}

		public List<Scale> getScaleList() {
			return scaleList;
		}

		public void setScaleList(List<Scale> scaleList) {
			this.scaleList = scaleList;
		}
	}

	@RequestMapping(value = "/{user}/getAllScales", method = RequestMethod.GET)
	public @ResponseBody
	Wrapper getAllScales(@PathVariable String user)
			throws NotExistingUserException {
		ServiceUser u = serviceUserTransaction.getUserById(user);
		if (u == null)
			throw new NotExistingUserException();
		System.out.println("grandezza list scale:" + u.getScaleList().size());

		return new Wrapper(u.getScaleList());
	}

}
