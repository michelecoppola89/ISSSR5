package com.isssr5.entities;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "operand")
public class Operand {
	private String dataType;// "Double" or "String"
	private ArrayList<String> dataSeries;
	private String operandMode; // can be E(enumerate),F(file),DB(database)
	private String url; // file path or DB path

	public Operand() {

		operandMode = "F";

	}

	public Operand(String dataType, ArrayList<String> dataSeries,
			String operandMode) {
		this.dataType = dataType;
		this.dataSeries = dataSeries;
		this.operandMode = operandMode;
	}

	public String getUrl() {
		return url;
	}

	@XmlElement
	public void setUrl(String url) {
		this.url = url;
	}

	public String getDataType() {
		return dataType;
	}

	@XmlElement
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public ArrayList<String> getDataSeries() {
		return dataSeries;
	}

	@XmlElement
	public void setDataSeries(ArrayList<String> dataSeries) {
		this.dataSeries = dataSeries;
	}

	public String getOperandMode() {
		return operandMode;
	}

	@XmlElement
	public void setOperandMode(String operandMode) {
		this.operandMode = operandMode;
	}

	public String PrintOperand() {

		String output = "";
		output += "Type: " + dataType + "\nOperandMode: " + operandMode+"\n";
		if (dataSeries != null) {
			output+="Data Series: ";
			for (int i = 0; i < dataSeries.size(); i++) {
				output += dataSeries.get(i).toString() + " ";

			}
		}
		return output;

	}
}
