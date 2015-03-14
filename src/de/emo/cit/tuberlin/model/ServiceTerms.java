package de.emo.cit.tuberlin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SLAService")
public class ServiceTerms {

	@Id
	@Column(name = "serviceId")
	private int serviceId;
	
	@Column(columnDefinition = "VARCHAR(100)", nullable = false)
	private String name;
	
	@Column(columnDefinition = "VARCHAR(100)", nullable = false)
	private String designation;
	
	@Column(columnDefinition = "VARCHAR(255)", nullable = false)
	private String description;
	
	@Column(columnDefinition = "VARCHAR(10)", nullable = false)
	private String costPerUnitOfAccount;
	
	@Column(columnDefinition = "INTEGER", nullable = false)
	private int unitOfAccount;

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
