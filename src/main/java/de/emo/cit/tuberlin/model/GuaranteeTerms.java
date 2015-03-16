package de.emo.cit.tuberlin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

	@ManyToOne(optional = false, targetEntity = SLA.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "slaId", referencedColumnName = "slaId", insertable = true, updatable = true, nullable = false)
	private SLA sla;

	@ManyToOne(optional = false, targetEntity = KeyPerformanceIndicator.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "keyPerformanceIndicatorId", referencedColumnName = "keyPerformanceIndicatorId", insertable = true, updatable = true, nullable = false)
	private KeyPerformanceIndicator keyPerformanceIndicator;

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
