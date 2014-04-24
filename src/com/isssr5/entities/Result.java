package com.isssr5.entities;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.property.MapAccessor;


@XmlRootElement(name = "Result")
public class Result {
	private MacroService macroService;
	private long idOperand;
	private List<ResultValue> resultValueList = new ArrayList<ResultValue>();

	public Result() {

	}

	public Result(MacroService ms, long idO, List<ResultValue> rV) {
		macroService = ms;
		idOperand = idO;
		resultValueList = rV;

	}

	public MacroService getMacroService() {
		return macroService;
	}

	public void setMacroService(MacroService macroService) {
		this.macroService = macroService;
	}

	public long getIdOperand() {
		return idOperand;
	}

	@XmlElement
	public void setIdOperand(long idOperand) {
		this.idOperand = idOperand;
	}

	public List<ResultValue> getResultValueList() {
		return resultValueList;
	}

	@XmlElement
	public void setResultValueList(List<ResultValue> resultValueList) {
		this.resultValueList = resultValueList;
	}

}
