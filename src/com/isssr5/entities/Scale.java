package com.isssr5.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name = "scale")
@Entity
@Table(name = "scale")
public class Scale {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idScale", nullable = false)
	private long id;

	@Column(name = "scaleType")
	private String type;

	@OneToOne(mappedBy = "scale", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Domain dom;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "user_user", nullable = false)
	private ServiceUser user;

	@OneToMany(mappedBy = "scale", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Operand> operandList = new ArrayList<Operand>();

	public Scale() {

	}

	public Scale(String type, Domain dom) {
		this.type = type;
		this.dom = dom;
	}

	public Scale(String type, Domain dom, ServiceUser user) {
		this.type = type;
		this.dom = dom;
		this.user = user;
	}

	@XmlTransient
	public ServiceUser getUser() {
		return user;
	}

	
	public void setUser(ServiceUser user) {
		this.user = user;
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

	public long getId() {
		return id;
	}

	@XmlElement
	public void setId(long id) {
		this.id = id;
	}
	
	@XmlTransient
	public List<Operand> getOperandList() {
		return operandList;
	}

	public void setOperandList(List<Operand> operandList) {
		this.operandList = operandList;
	}

}
