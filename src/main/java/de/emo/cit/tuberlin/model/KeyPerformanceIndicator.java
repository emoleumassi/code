package de.emo.cit.tuberlin.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Check;

@Entity
@Check(constraints = "designation in('availability', 'mttr', 'mtbf', 'responseTime', 'latency')")
@Table
public class KeyPerformanceIndicator {

	@Id
	@Column(columnDefinition = "VARCHAR(50)", nullable = false)
	private String keyPerformanceIndicatorId;

//	@Column(columnDefinition = "VARCHAR(100)", nullable = false)
//	private String name;
//
//	@Column(columnDefinition = "DECIMAL(10, 2)", nullable = false)
//	private short targetValue;

	@Column(columnDefinition = "DECIMAL(10, 2)", nullable = false)
	private short availability;

	@Column(columnDefinition = "DECIMAL(10, 2)", nullable = false)
	private short mtbf;
	
	@Column(columnDefinition = "DECIMAL(10, 2)", nullable = false)
	private short mttr;
	
	@Column(columnDefinition = "DECIMAL(10, 2)", nullable = false)
	private short responseTime;
	
	@Column(columnDefinition = "DECIMAL(10, 2)", nullable = false)
	private short latency;
	
	@Column(columnDefinition = "TEXT", nullable = false)
	private String description;

	@OneToOne(targetEntity = GuaranteeTerms.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "guaranteeTermId", columnDefinition = "VARCHAR(50) default 'xxxxx'", referencedColumnName = "guaranteeTermId", insertable = true, updatable = true, nullable = true)
	private GuaranteeTerms guaranteeTerms;

	public String getKeyPerformanceIndicatorId() {
		return keyPerformanceIndicatorId;
	}

	public void setKeyPerformanceIndicatorId(String keyPerformanceIndicatorId) {
		this.keyPerformanceIndicatorId = keyPerformanceIndicatorId;
	}

	public short getAvailability() {
		return availability;
	}

	public void setAvailability(short availability) {
		this.availability = availability;
	}

	public short getMtbf() {
		return mtbf;
	}

	public void setMtbf(short mtbf) {
		this.mtbf = mtbf;
	}

	public short getMttr() {
		return mttr;
	}

	public void setMttr(short mttr) {
		this.mttr = mttr;
	}

	public short getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(short responseTime) {
		this.responseTime = responseTime;
	}

	public short getLatency() {
		return latency;
	}

	public void setLatency(short latency) {
		this.latency = latency;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
