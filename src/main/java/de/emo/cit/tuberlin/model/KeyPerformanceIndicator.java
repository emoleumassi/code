package de.emo.cit.tuberlin.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

	@Column(columnDefinition = "TEXT", nullable = false)
	private String description;
	// @OneToOne(targetEntity = Method.class, fetch = FetchType.LAZY)
	// @JoinColumn(name = "methodId", columnDefinition =
	// "VARCHAR(50) default 'xxxxx'", referencedColumnName = "methodId",
	// insertable = true, updatable = true, nullable = true)
	// private Method method;

	@ManyToOne(targetEntity = GuaranteeTerms.class, cascade = CascadeType.ALL)
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
