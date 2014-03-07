package com.isssr5.entities;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "operand")
public class Operand {
	private String dataType;// "Double" or "String"
	private ArrayList<Object> dataSeries;
	private String operandMode; // can be E(enumerate),F(file),DB(database)

	public Operand() {

		operandMode = "F";

	}

	public Operand(String dataType, ArrayList<Object> dataSeries,
			String operandMode) {
		this.dataType = dataType;
		this.dataSeries = dataSeries;
		this.operandMode = operandMode;
	}

	public String getDataType() {
		return dataType;
	}

	@XmlElement
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public ArrayList<Object> getDataSeries() {
		return dataSeries;
	}

	@XmlElement
	public void setDataSeries(ArrayList<Object> dataSeries) {
		this.dataSeries = dataSeries;
	}

	public String getOperandMode() {
		return operandMode;
	}

	@XmlElement
	public void setOperandMode(String operandMode) {
		this.operandMode = operandMode;
	}

	String PrintOperand() {

		String output = "";
		output += "Type: " + dataType + "\nOperandMode: " + operandMode;
		if (dataSeries != null) {
			for (int i = 0; i < dataSeries.size(); i++) {
				output += dataSeries.get(i).toString() + " ";

			}
		}
		return output;

	}
}
