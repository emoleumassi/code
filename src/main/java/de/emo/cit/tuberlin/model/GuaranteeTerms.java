package de.emo.cit.tuberlin.model;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

//@Entity
//@Check(constraints = "obligated in('provide', 'customer')")
//@Table
public class GuaranteeTerms {

	@Id
	@Column(columnDefinition = "VARCHAR(50)", nullable = false)
	private String guaranteeTermId;

	@Column(columnDefinition = "VARCHAR(10)", nullable = false)
	private String obligated;

	@Column(columnDefinition = "VARCHAR(100)", nullable = false)
	private String serviceName;

	@ManyToOne(optional = false, targetEntity = SLA.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "slaId", columnDefinition = "VARCHAR(50) default 'xxxxx'", referencedColumnName = "slaId", insertable = true, updatable = true, nullable = true)
	private SLA sla;

	@OneToMany(mappedBy = "guaranteeTerms", targetEntity = KeyPerformanceIndicator.class, fetch = FetchType.LAZY)
	private KeyPerformanceIndicator keyPerformanceIndicator;

	public String getGuaranteeTermId() {
		return guaranteeTermId;
	}

	public void setGuaranteeTermId(String guaranteeTermId) {
		this.guaranteeTermId = guaranteeTermId;
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

	public SLA getSla() {
		return sla;
	}

	public void setSla(SLA sla) {
		this.sla = sla;
	}
}
