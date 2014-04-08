package com.isssr5.entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="intervalDomain")
@Entity
@Table(name="domain")
@DiscriminatorValue("I")
public class IntervalDomain extends Domain {
	
	@Column(name="min")
	private Double min;
	@Column(name="max")
	private Double max;

	public IntervalDomain() {

	}

	public IntervalDomain(String type, Double min, Double max) {
		super(type);
		this.min = min;
		this.max = max;
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
