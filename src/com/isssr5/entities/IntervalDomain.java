package com.isssr5.entities;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="intervalDomain")
public class IntervalDomain extends Domain {
	
	private Double min;
	private Double max;
	
	public IntervalDomain (){
		
	}
	
	public IntervalDomain(Double min, Double max){
		this.min=min;
		this.max=max;
	}
	
	public Double getMin() {
		return min;
	}
	@XmlElement
	public void setMin(Double min) {
		this.min = min;
	}

	public Double getMax() {
		return max;
	}
	@XmlElement
	public void setMax(Double max) {
		this.max = max;
	}

	
}
