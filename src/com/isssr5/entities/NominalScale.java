package com.isssr5.entities;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="nominalScale")
public class NominalScale {

	private String type;
	private EnumerateDomain scalePointList;

	public NominalScale() {

	}

	public NominalScale(String type, EnumerateDomain scalePointList){
		this.type=type;
		this.scalePointList=scalePointList;
		
	}

	public String getType() {
		return type;
	}
	
	@XmlElement
	public void setType(String type) {
		this.type = type;
	}

	public EnumerateDomain getScalePointList() {
		return scalePointList;
	}
	
	@XmlElement
	public void setScalePointList(EnumerateDomain scalePointList) {
		this.scalePointList = scalePointList;
	}

}
