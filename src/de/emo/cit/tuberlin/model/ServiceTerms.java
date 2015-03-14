package de.emo.cit.tuberlin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class ServiceTerms {

	@Id
	@Column(name = "serviceId")
	private int serviceId;
	
	@Column(columnDefinition = "VARCHAR(100)", nullable = false)
	private String name;
	
	@Column(columnDefinition = "VARCHAR(100)", nullable = false)
	private String designation;
	
	@Column(columnDefinition = "TEXT", nullable = false)
	private String description;
	
	@Column(columnDefinition = "DECIMAL(10, 2)", nullable = false)
	private String costPerUnitOfAccount;
	
	@Column(columnDefinition = "INTEGER", nullable = false)
	private int unitOfAccount;
	
	@ManyToOne(optional = false, targetEntity = SLA.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "slaId", referencedColumnName = "slaId", insertable = true, updatable = true, nullable = false)
	private SLA sla;

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCostPerUnitOfAccount() {
		return costPerUnitOfAccount;
	}

	public void setCostPerUnitOfAccount(String costPerUnitOfAccount) {
		this.costPerUnitOfAccount = costPerUnitOfAccount;
	}

	public int getUnitOfAccount() {
		return unitOfAccount;
	}

	public void setUnitOfAccount(int unitOfAccount) {
		this.unitOfAccount = unitOfAccount;
	}
}
