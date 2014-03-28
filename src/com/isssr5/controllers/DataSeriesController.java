package com.isssr5.controllers;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.isssr5.entities.Operand;
import com.isssr5.exceptions.BadDataSeriesUrlException;
import com.isssr5.exceptions.BadOperandInput;
import com.isssr5.exceptions.NullDataSeriesException;
import com.isssr5.exceptions.NullOperandModeException;
import com.isssr5.exceptions.NullOperandTypeException;

@Controller
@RequestMapping("/dataSeries")
public class DataSeriesController {

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

	@RequestMapping(value = "/acquisition", method = RequestMethod.POST)
	public @ResponseBody
	String dataSeriesAcquisition(@RequestBody Operand op)
			throws NullOperandTypeException, NullOperandModeException,
			NullDataSeriesException, BadDataSeriesUrlException, BadOperandInput {

		checkDataSeries(op);

		return op.PrintOperand();
	}

	private void checkDataSeries(Operand op) throws NullOperandTypeException,
			NullOperandModeException, NullDataSeriesException,
			BadDataSeriesUrlException, BadOperandInput {

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

			if (op.getUrl() != null) {

				throw new BadOperandInput();

			}

		}

		if (op.getOperandMode().equals("F")) {
			if ((op.getUrl() == null) || (op.getUrl().isEmpty())) {

				throw new BadDataSeriesUrlException();

			}

			if (op.getDataSeries() != null) {
				throw new BadOperandInput();
			}
		}

		// check for databasepath!!!

	}
}
