package de.emo.cit.tuberlin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Check;

@Entity
@Check(constraints = "obligated in('provide', 'customer')")
@Table
public class GuaranteeTerms {

	@Id
	@Column(name = "guaranteeId")
	private int guaranteeId;

	@Column(columnDefinition = "VARCHAR(10)", nullable = false)
	private String obligated;

	@Column(columnDefinition = "VARCHAR(100)", nullable = false)
	private String serviceName;

	public int getGuaranteeId() {
		return guaranteeId;
	}

	public void setGuaranteeId(int guaranteeId) {
		this.guaranteeId = guaranteeId;
	}

	public String getObligated() {
		return obligated;
	}

	public void setObligated(String obligated) {
		this.obligated = obligated;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
}
