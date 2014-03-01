package com.isssr5.entities;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="intervalScale")
public class IntervalScale {
	
	private String type;
	private IntervalDomain scaleInterval;
	
	public IntervalScale(){
		
	}
	public IntervalScale(String type, IntervalDomain scaleInterval){
		this.type=type;
		this.scaleInterval=scaleInterval;
	}
	
	public String getType() {
		return type;
	}
	@XmlElement
	public void setType(String type) {
		this.type = type;
	}
	public IntervalDomain getScaleInterval() {
		return scaleInterval;
	}
	@XmlElement
	public void setScaleInterval(IntervalDomain scaleInterval) {
		this.scaleInterval = scaleInterval;
	}
	
	
	
}
