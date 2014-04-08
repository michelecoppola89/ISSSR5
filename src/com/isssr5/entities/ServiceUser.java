package com.isssr5.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.util.*;

@Entity
@Table(name = "user")
@XmlRootElement(name = "user")
public class ServiceUser {
	@Id
	@Column(name = "user", nullable = false)
	private String userid;
	@Column(name = "psw")
	private String psw;

	// @OneToMany(fetch=FetchType.LAZY,mappedBy="user")
	// private List <MacroService> serviceList= new ArrayList<MacroService>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Scale> scaleList = new ArrayList<Scale>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Operand> dataSeriesList = new ArrayList<Operand>();

	// public List<MacroService> getServiceList() {
	// return serviceList;
	// }
	//
	// @XmlElement
	// public void setServiceList(ArrayList<MacroService> serviceList) {
	// this.serviceList = serviceList;
	// }

	public ServiceUser() {

	}

	public ServiceUser(String userid, String psw) {
		this.userid = userid;
		this.psw = psw;
	}
	
	@XmlTransient
	public List<Operand> getDataSeriesList() {
		return dataSeriesList;
	}

	@XmlElement
	public void setDataSeriesList(ArrayList<Operand> dataSeriesList) {
		this.dataSeriesList = dataSeriesList;
	}

	public String getUserid() {
		return userid;
	}

	@XmlElement
	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPsw() {
		return psw;
	}

	@XmlElement
	public void setPsw(String psw) {
		this.psw = psw;
	}

	@XmlTransient
	public List<Scale> getScaleList() {
		return scaleList;
	}

	public void setScaleList(List<Scale> scaleList) {
		this.scaleList = scaleList;
	}

}
