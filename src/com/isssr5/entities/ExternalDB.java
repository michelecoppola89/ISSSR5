package com.isssr5.entities;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="externalDB")
public class ExternalDB {
	private String address;
	private String username;
	private String pwd;
	private String query;
	
	public ExternalDB() {
		this.address = "";
		this.username = "";
		this.pwd = "";
		this.query = "";
	}
	
	public ExternalDB(String address, String username, String pwd, String query) {
		this.address = address;
		this.username = username;
		this.pwd = pwd;
		this.query = query;
	}

	public String getAddress() {
		return address;
	}

	@XmlElement
	public void setAddress(String address) {
		this.address = address;
	}

	public String getUsername() {
		return username;
	}

	@XmlElement
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPwd() {
		return pwd;
	}

	@XmlElement
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getQuery() {
		return query;
	}

	@XmlElement
	public void setQuery(String query) {
		this.query = query;
	}

	
	

}
