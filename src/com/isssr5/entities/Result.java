package com.isssr5.entities;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;




@XmlRootElement(name = "Result")
public class Result {

	private List<Long> idOperand= new ArrayList<Long>();

	private List<ResultValue> resultValueList = new ArrayList<ResultValue>();

	
	public Result() {

	}


	public Result(List<Long> list_id, List<ResultValue> rV) {

		idOperand = list_id;
		resultValueList = rV;

	}


	public List<Long> getIdOperand() {
		return idOperand;

	}

	@XmlElement
	public void setIdOperand(List<Long> idOperand) {
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
