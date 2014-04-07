package com.isssr5.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.eclipse.persistence.oxm.annotations.XmlInverseReference;
import org.hibernate.annotations.Cascade;

@XmlRootElement(name = "scale")
@Entity
@Table(name = "scale")

public class Scale {
	
	@Column(name = "scaleType")
	private String type;
	
	@OneToOne(mappedBy="scale",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	
	private Domain dom;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "user_user",nullable=false)
	private ServiceUser user;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idScale", nullable = false)
	private long id;

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

	public ServiceUser getUser() {
		return user;
	}
	
	@XmlTransient
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

}
