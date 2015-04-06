package de.emo.cit.tuberlin.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * @author emoleumassi
 *
 */
@Entity
@Table
public class ServiceTerms {

	@Id
	@Column(columnDefinition = "VARCHAR(50)", nullable = false)
	private String serviceTermId;

	@Column(columnDefinition = "VARCHAR(100)", nullable = false)
	private String name;
	
	@Column(columnDefinition = "VARCHAR(50)", nullable = false)
	private String serviceName;

	@Column(columnDefinition = "TEXT", nullable = false)
	private String description;

	@ManyToOne(targetEntity = SLA.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "slaId", columnDefinition = "VARCHAR(50) default 'xxxxx'", referencedColumnName = "slaId", insertable = true, updatable = true, nullable = true)
	private SLA sla;

	public String getServiceTermId() {
		return serviceTermId;
	}

	public void setServiceTermId(String serviceTermId) {
		this.serviceTermId = serviceTermId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
