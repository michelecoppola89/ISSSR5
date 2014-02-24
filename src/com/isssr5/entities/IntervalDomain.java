package com.isssr5.entities;

public class IntervalDomain extends Domain {
private Double min;
private Double max;
public IntervalDomain(Double min, Double max){
	this.min=min;
	this.max=max;
	
}
	@Override
	public String getType() {
		return "intervalDomain";
		
	}
	public Double getMin() {
		return min;
	}
	public void setMin(Double min) {
		this.min = min;
	}
	public Double getMax() {
		return max;
	}
	public void setMax(Double max) {
		this.max = max;
	}

}
