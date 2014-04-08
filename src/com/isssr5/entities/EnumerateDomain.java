package com.isssr5.entities;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="enumerateDomain")
@Entity
@Table(name="domain")
@DiscriminatorValue("E")
public class EnumerateDomain extends Domain {
	
	@Column(name="scalePoint")
	private ArrayList<String> scalePoints;
	
	public EnumerateDomain(){
		
	}
	public ArrayList<String> getScalePoints() {
		return scalePoints;
	}
	@XmlElement
	public void setScalePoints(ArrayList<String> scalePoints) {
		this.scalePoints = scalePoints;
	}
	public EnumerateDomain(String type, ArrayList<String> scalePoints)
	{
		super(type);
		this.scalePoints=scalePoints;
	}
	public String printScalePoint(){
		
		String r="";
		for(int i=0;i<scalePoints.size();i++){
			r+=scalePoints.get(i)+"\n";
		}
		
		return r;
	}

}
