package com.isssr5.entities;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "parameterList")
public class ParameterList {

	private ArrayList<Integer> parList;

	public ParameterList() {
	}

	public ParameterList(ArrayList<Integer> parList) {
		this.parList = parList;
	}

	public ArrayList<Integer> getParList() {
		return parList;
	}

	@XmlElement
	public void setParList(ArrayList<Integer> parList) {
		this.parList = parList;
	}

	public String printParameterList() {

		String output = "";
		for (int j = 0; j < parList.size(); j++) {
			output += parList.get(j) + " ";
		}
		return output;
	}

	public boolean checkParameterList(int totalNumParameter) {

		boolean ret = true;
		for (int i = 0; i < parList.size() && ret; i++) {
			if (parList.get(i) > totalNumParameter)
				ret = false;
		}
		return ret;
	}

	public boolean checkParameterListSize(int acceptedNumParameter) {

		if(parList.size()!=acceptedNumParameter)
			return false;
		else 
			return true;
	}

}
