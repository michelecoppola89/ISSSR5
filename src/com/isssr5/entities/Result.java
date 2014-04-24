package com.isssr5.entities;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.property.MapAccessor;
@XmlRootElement(name = "Result")
public class Result {
	private MacroService macroService;
	private List<Long> operandIds = new ArrayList<Long>();
	private List<ResultValue> resultValueList = new ArrayList<ResultValue>();
	public Result() {
		
	}
	
public Result( MacroService ms, List<Long> idO, List<ResultValue> rV) {
	macroService=ms;
	 operandIds=idO;
	resultValueList=rV;
		
	}
	public MacroService getMacroService() {
		return macroService;
	}
	public void setMacroService(MacroService macroService) {
		this.macroService = macroService;
	}
	public List<Long> getIdOperand() {
		return operandIds;
	}
	@XmlElement
	public void setIdOperand(List<Long> operandIds) {
		this.operandIds = operandIds;
	}
	public List<ResultValue> getResultValueList() {
		return resultValueList;
	}
	@XmlElement
	public void setResultValueList(List<ResultValue> resultValueList) {
		this.resultValueList = resultValueList;
	}


}
