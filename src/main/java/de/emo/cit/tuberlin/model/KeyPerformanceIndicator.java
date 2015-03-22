package de.emo.cit.tuberlin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class KeyPerformanceIndicator {

	@Id
	@Column(columnDefinition = "VARCHAR(50)", nullable = false)
	private String keyPerformanceIndicatorId;

	@Column(columnDefinition = "VARCHAR(100)", nullable = false)
	private String designation;

	@Column(columnDefinition = "VARCHAR(5)", nullable = false)
	private String targetValue;

	@Column(columnDefinition = "VARCHAR(5)", nullable = false)
	private String qualifyingCondiction;

	// @OneToOne(optional = false, targetEntity = Method.class, fetch =
	// FetchType.LAZY)
	// @JoinColumn(name = "methodId", columnDefinition =
	// "VARCHAR(50) default 'xxxxx'", referencedColumnName = "methodId",
	// insertable = true, updatable = true, nullable = true)
	// private Method method;

	@ManyToOne(optional = false, targetEntity = GuaranteeTerms.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "guaranteeTermId", columnDefinition = "VARCHAR(50) default 'xxxxx'", referencedColumnName = "guaranteeTermId", insertable = true, updatable = true, nullable = true)
	private GuaranteeTerms guaranteeTerms;

	public String getKeyPerformanceIndicatorId() {
		return keyPerformanceIndicatorId;
	}

	public void setKeyPerformanceIndicatorId(String keyPerformanceIndicatorId) {
		this.keyPerformanceIndicatorId = keyPerformanceIndicatorId;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getTargetValue() {
		return targetValue;
	}

	public void setTargetValue(String targetValue) {
		this.targetValue = targetValue;
	}

	public String getQualifyingCondiction() {
		return qualifyingCondiction;
	}

	public void setQualifyingCondiction(String qualifyingCondiction) {
		this.qualifyingCondiction = qualifyingCondiction;
	}

	// public Method getMethod() {
	// return method;
	// }
	//
	// public void setMethod(Method method) {
	// this.method = method;
	// }

	// public GuaranteeTerms getGuaranteeTerms() {
	// return guaranteeTerms;
	// }
	//
	// public void setGuaranteeTerms(GuaranteeTerms guaranteeTerms) {
	// this.guaranteeTerms = guaranteeTerms;
	// }
}
