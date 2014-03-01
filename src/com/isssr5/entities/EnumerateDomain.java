package com.isssr5.entities;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="enumerateDomain")
public class EnumerateDomain {
	private ArrayList<String> scalePoint;

	public EnumerateDomain() {

	}

	public EnumerateDomain(ArrayList<String> scalePoint) {
		this.scalePoint = scalePoint;
	}

	public ArrayList<String> getScalePoint() {
		return scalePoint;
	}
	
	@XmlElement
	public void setScalePoint(ArrayList<String> scalePoint) {
		this.scalePoint = scalePoint;
	}

}
