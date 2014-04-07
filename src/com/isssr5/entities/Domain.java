package com.isssr5.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@XmlRootElement(name = "domain")
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="discr_dom", discriminatorType=DiscriminatorType.STRING)
@Table(name = "domain")
public class Domain {

	@Column(name = "domType")
	private String domType;

	@Id
	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "scale"))
	@GeneratedValue(generator="generator")
	@Column(name = "idScale")
	private long id;

	@OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private Scale scale;

	public Domain() {
		scale = new Scale();
	}

	public Domain(String domType) {
		this.domType = domType;

	}

	public Domain(String domType, Scale scale) {
		super();
		this.domType = domType;
		this.scale = scale;
	}

	public String getDomType() {
		return domType;
	}

	@XmlElement
	public void setDomType(String domType) {
		this.domType = domType;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Scale getScale() {
		return scale;
	}

	public void setScale(Scale scale) {
		this.scale = scale;
	}

}
