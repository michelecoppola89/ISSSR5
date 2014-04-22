package com.isssr5.entities;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "resultValue")
public class ResultValue {
	private String operand;
	private String value;
	
	
	public ResultValue() {
	}
	
	public ResultValue(String op,String v) {
		operand=op;
		value=v;
		
	}
	public String getOperand() {
		return operand;
	}


	@XmlElement
	public void setOperand(String operand) {
		operand = operand;
	}



	public String getValue() {
		return value;
	}


	@XmlElement
	public void setValue(String value) {
		this.value = value;
	}



	

}
