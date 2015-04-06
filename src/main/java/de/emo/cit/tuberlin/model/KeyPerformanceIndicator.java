package de.emo.cit.tuberlin.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Check;

@Entity
@Check(constraints = "designation in('availability', 'MTTR', 'MTBF', 'response time', 'latency')")
@Table
public class KeyPerformanceIndicator {

	@Id
	@Column(columnDefinition = "VARCHAR(50)", nullable = false)
	private String keyPerformanceIndicatorId;

	@Column(columnDefinition = "VARCHAR(100)", nullable = false)
	private String name;

	@Column(columnDefinition = "VARCHAR(5)", nullable = false)
	private String targetValue;

	@Column(columnDefinition = "VARCHAR(5)", nullable = false)
	private String qualifyingCondiction;

	@Column(columnDefinition = "TEXT", nullable = false)
	private String description;

	@ManyToOne(targetEntity = GuaranteeTerms.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "guaranteeTermId", columnDefinition = "VARCHAR(50) default 'xxxxx'", referencedColumnName = "guaranteeTermId", insertable = true, updatable = true, nullable = true)
	private GuaranteeTerms guaranteeTerms;

	public String getKeyPerformanceIndicatorId() {
		return keyPerformanceIndicatorId;
	}

	public void setKeyPerformanceIndicatorId(String keyPerformanceIndicatorId) {
		this.keyPerformanceIndicatorId = keyPerformanceIndicatorId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
