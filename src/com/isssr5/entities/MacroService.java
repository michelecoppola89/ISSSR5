package com.isssr5.entities;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "macroService")
public class MacroService {
	private String idCode;
	private ArrayList<Operand> operandList;
	private ArrayList<String> elementaryServices;

	public MacroService() {

	}

	public String getIdCode() {
		return idCode;
	}

	@XmlElement
	public void setIdCode(String idCode) {
		this.idCode = idCode;
	}

	public ArrayList<Operand> getOperandList() {
		return operandList;
	}

	@XmlElement
	public void setOperandList(ArrayList<Operand> operandList) {
		this.operandList = operandList;
	}

	public ArrayList<String> getElementaryServices() {
		return elementaryServices;
	}

	@XmlElement
	public void setElementaryServices(ArrayList<String> elementaryServices) {
		this.elementaryServices = elementaryServices;
	}

	public MacroService(String idCode, ArrayList<Operand> operandList,
			ArrayList<String> elementaryServices) {
		this.idCode = idCode;
		this.operandList = operandList;
		this.elementaryServices = elementaryServices;

	}

	public String printElementaryService() {
		String output = "";
		for (int i = 0; i < elementaryServices.size(); i++)
			output += elementaryServices.get(i) + " ";
		return output;

	}
	public String printOperandList(){
		String output="";
		for(int i=0;i<operandList.size();i++){
			output+="Operand "+(i+1)+": "+operandList.get(i).PrintOperand()+"\n";
		}
		
		return output;
	}

}
