package com.isssr5.entities;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "scale")
public class Scale {
	private String type;
	private Domain dom;

	public Scale() {

	}

	public Scale(String type, Domain dom) {
		this.type = type;
		this.dom = dom;
	}

	public String getType() {
		return type;
	}

	@XmlElement
	public void setType(String type) {
		this.type = type;
	}

	public Domain getDom() {
		return dom;
	}

	@XmlElements(value = {
			@XmlElement(name = "intervalDomain", type = IntervalDomain.class),
			@XmlElement(name = "enumerateDomain", type = EnumerateDomain.class) })
	public void setDom(Domain dom) {
		this.dom = dom;
	}

}
