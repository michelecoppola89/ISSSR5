package com.isssr5.entities;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="domain")
public abstract class Domain {
	private String domType;
	public Domain(){
		
	}
	public String getDomType() {
		return domType;
	}
	@XmlElement
	public void setDomType(String domType) {
		this.domType = domType;
	}
	public Domain(String domType){
		this.domType=domType;
				
	}
	
	


}
