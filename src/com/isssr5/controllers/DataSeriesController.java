package com.isssr5.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.isssr5.entities.Operand;
import com.isssr5.entities.Scale;
import com.isssr5.entities.ServiceUser;
import com.isssr5.exceptions.BadDataSeriesUrlException;
import com.isssr5.exceptions.BadOperandInput;
import com.isssr5.exceptions.NotExistingOperandException;
import com.isssr5.exceptions.NotExistingScaleException;
import com.isssr5.exceptions.NotExistingUserException;
import com.isssr5.exceptions.NullDataSeriesException;
import com.isssr5.exceptions.NullOperandModeException;
import com.isssr5.exceptions.NullOperandTypeException;
import com.isssr5.service.OperandTransaction;
import com.isssr5.service.ScaleTransaction;
import com.isssr5.service.ServiceUserTransaction;

@Controller
@RequestMapping("/dataSeries")
public class DataSeriesController {

	private ServiceUserTransaction servireUserTransaction;
	private OperandTransaction operandTransaction;
	private ScaleTransaction scaleTransaction;

	@Autowired
	public DataSeriesController(ServiceUserTransaction servireUserTransaction,
			OperandTransaction operandTransaction,
			ScaleTransaction scaleTransaction) {
		this.servireUserTransaction = servireUserTransaction;
		this.operandTransaction = operandTransaction;
		this.scaleTransaction = scaleTransaction;
	}

	@RequestMapping(value = "/testDataSeries1", method = RequestMethod.GET)
	public @ResponseBody
	Operand testDataSeries1() {

		ArrayList<String> series = new ArrayList<String>();
		series.add("3.1");
		series.add("4");
		series.add("5.5");
		series.add("8");

		Operand op = new Operand("Double", series, "E");

		return op;

	}

	@RequestMapping(value = "/testDataSeries2", method = RequestMethod.GET)
	public @ResponseBody
	Operand testDataSeries2() {

		Operand op = new Operand("Double", null, "F");

		op.setUrl("http://www.ilfattoquotidiano.it/");

		return op;

	}

	@RequestMapping(value = "/{user}/getOperandById/{operandId}", method = RequestMethod.GET)
	public @ResponseBody
	Operand getOperandById(@PathVariable String user,
			@PathVariable long operandId) throws NotExistingUserException,
			NotExistingOperandException {
		ServiceUser u = servireUserTransaction.getUserById(user);
		if (u == null)
			throw new NotExistingUserException();
		Operand o = operandTransaction.findOperandById(operandId);
		if (o == null)
			throw new NotExistingOperandException();
		if (!o.getUser().getUserid().equals(user))
			throw new NotExistingOperandException();
		return o;

	}

	@RequestMapping(value = "/echoAcquisition", method = RequestMethod.POST)
	public @ResponseBody
	String dataSeriesEchoAcquisition(@RequestBody Operand op)
			throws NullOperandTypeException, NullOperandModeException,
			NullDataSeriesException, BadDataSeriesUrlException,
			BadOperandInput, IOException, ClassNotFoundException, SQLException {

		checkDataSeries(op);
		if (op.getOperandMode().equals("F")) {
			op.acquisitionFromfile(op.getUrl());
		} else if (op.getOperandMode().equals("D")) {
			op.acqusitionFromExternalDB();
		}

		return op.PrintOperand();
	}

	@RequestMapping(value = "/{user}/{idscale}/acquisition", method = RequestMethod.POST)
	public @ResponseBody
	String dataSeriesAcquisition(@RequestBody Operand op,
			@PathVariable String user, @PathVariable long idscale,HttpServletResponse response)
			throws NullOperandTypeException, NullOperandModeException,
			NullDataSeriesException, BadDataSeriesUrlException,
			BadOperandInput, IOException, ClassNotFoundException, SQLException,
			NotExistingUserException, NotExistingScaleException {

		checkDataSeries(op);
		if (op.getOperandMode().equals("F")) {
			op.acquisitionFromfile(op.getUrl());
		} else if (op.getOperandMode().equals("D")) {
			op.acqusitionFromExternalDB();
		}
		ServiceUser u = servireUserTransaction.getUserById(user);
		if (u == null)
			throw new NotExistingUserException();
		op.setUser(u);
		Scale s = scaleTransaction.findScaleById(idscale);
		if (s == null)
			throw new NotExistingScaleException();
		if (!s.getUser().getUserid().equals(user))
			throw new NotExistingScaleException();
		op.setScale(s);
		operandTransaction.addOperand(op);
		response.setHeader("Location", "/dataSeries/" + u.getUserid()
				+ "/getOperandById/" + op.getId());
		return op.PrintOperand();
	}

	private void checkDataSeries(Operand op) throws NullOperandTypeException,
			NullOperandModeException, NullDataSeriesException,
			BadDataSeriesUrlException, BadOperandInput, NumberFormatException {

		if ((op.getDataType() == null)
				|| (!(op.getDataType().equals("String")) && !(op.getDataType()
						.equals("Double")))) {

			throw new NullOperandTypeException();

		}

		if ((op.getOperandMode() == null)
				|| (op.getOperandMode().isEmpty())
				|| (!(op.getOperandMode().equals("E"))
						&& !(op.getOperandMode().equals("F")) && !(op
							.getOperandMode().equals("D")))) {

			throw new NullOperandModeException();

		}

		if (op.getOperandMode().equals("E")) {

			if ((op.getDataSeries() == null) || (op.getDataSeries().isEmpty())) {

				throw new NullDataSeriesException();

			}

			if ((op.getUrl() != null) || (op.geteDB() != null)) {

				throw new BadOperandInput();

			}
			if (op.getDataType().equals("Double")) {
				for (int i = 0; i < op.getDataSeries().size(); i++) {

					String temp = op.getDataSeries().get(i);
					Double.parseDouble(temp);
				}
			}

		}

		if (op.getOperandMode().equals("F")) {
			if ((op.getUrl() == null) || (op.getUrl().isEmpty())) {

				throw new BadDataSeriesUrlException();

			}

			if ((op.getDataSeries() != null) || (op.geteDB() != null)) {
				throw new BadOperandInput();
			}
		}

		// check for database path!!!

		if (op.getOperandMode().equals("D")) {
			if (op.geteDB() == null) {

				throw new BadDataSeriesUrlException();

			}

			if ((op.getDataSeries() != null) || (op.getUrl() != null)) {
				throw new BadOperandInput();
			}
		}

	}

}
