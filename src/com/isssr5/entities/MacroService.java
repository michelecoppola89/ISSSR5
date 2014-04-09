package com.isssr5.entities;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import com.isssr5.exceptions.NotExistingMacroServiceException;

@XmlRootElement(name = "macroService")
public class MacroService {

	private String idCode;
	private ArrayList<Operand> operandList;
	private ArrayList<String> elementaryServices;
	private ArrayList<ParameterList> operationOrder;
	private int numOperand;

	public MacroService() {
		numOperand = 0;
	}

	public MacroService(String idCode, ArrayList<Operand> operandList,
			ArrayList<String> elementaryServices) {
		this.idCode = idCode;
		this.operandList = operandList;
		this.elementaryServices = elementaryServices;
		numOperand = 0;

	}

	public ArrayList<ParameterList> getOperationOrder() {
		return operationOrder;
	}

	@XmlElement
	public void setOperationOrder(ArrayList<ParameterList> operationOrder) {
		this.operationOrder = operationOrder;
	}

	public int getNumOperand() {
		return numOperand;
	}

	@XmlElement
	public void setNumOperand(int numOperand) {
		this.numOperand = numOperand;
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

	public void decodeMacroService(DefaultServicesTable table)
			throws NotExistingMacroServiceException {
		MacroService m = table.getTable().get(idCode);
		if (m == null) {
			throw new NotExistingMacroServiceException();
		} else {
			if (m.elementaryServices != null) {
				this.elementaryServices = new ArrayList<String>(
						m.elementaryServices);
				this.operationOrder = new ArrayList<ParameterList>(
						m.operationOrder);
				this.numOperand = m.getNumOperand();
			}
		}

	}

	public String printElementaryService() {
		String output = "";
		for (int i = 0; i < elementaryServices.size(); i++)
			output += elementaryServices.get(i) + " ";
		return output;

	}

	public String printOperandList() {
		String output = "";
		for (int i = 0; i < operandList.size(); i++) {
			output += "Operand " + (i + 1) + ": "
					+ operandList.get(i).PrintOperand() + "\n";
		}

		return output;
	}

	public String printMacroService() {

		String output = "";
		output += "MacroService ID: " + idCode + "\n";

		if (elementaryServices != null) {
			output += "MacroService/Elementary Service list:\n";
			for (int i = 0; i < elementaryServices.size(); i++) {
				output += "MacroService ID: " + elementaryServices.get(i)+ "\n";
			}
		}

		output += "Num operand: " + numOperand + "\n";

		if (operationOrder != null) {
			for (int i = 0; i < operationOrder.size(); i++) {
				output += "MacroService " + (i + 1) + " input parameters: ";
				output += operationOrder.get(i).printParameterList();
				output += "\n";
			}
		} else {
			output += "This MacroService is an elementary service\n";
		}
		return output;
	}

}
