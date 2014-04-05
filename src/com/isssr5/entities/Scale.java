package com.isssr5.entities;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "scale")
@Entity
@Table(name="scale")

public class Scale {
	private String type;
	private Domain dom;
	@ManyToOne
	@JoinColumn(name="user_user")
	private ServiceUser user;
	public ServiceUser getUser() {
		return user;
	}

	public void setUser(ServiceUser user) {
		this.user = user;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idScale",nullable=false)
	private long id;

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

	@Embedded
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
