package de.emo.cit.tuberlin.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class GuaranteeTerms {

	@Id
	@Column(columnDefinition = "VARCHAR(50)", nullable = false)
	private String guaranteeTermId;

	@Column(columnDefinition = "TEXT")
	private String description;

	@Column(columnDefinition = "VARCHAR(100)", nullable = false)
	private String serviceName;

	@ManyToOne(targetEntity = SLA.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "slaId", columnDefinition = "VARCHAR(50) default 'xxxxx'", referencedColumnName = "slaId", insertable = true, updatable = true, nullable = true)
	private SLA sla;

	@OneToOne(mappedBy = "guaranteeTerms", targetEntity = Reward.class, cascade = CascadeType.ALL)
	private Reward reward;

	@OneToOne(mappedBy = "guaranteeTerms", targetEntity = KeyPerformanceIndicator.class, cascade = CascadeType.ALL)
	// @LazyCollection(LazyCollectionOption.FALSE)
	private KeyPerformanceIndicator keyPerformanceIndicator;

	public String getGuaranteeTermId() {
		return guaranteeTermId;
	}

	public void setGuaranteeTermId(String guaranteeTermId) {
		this.guaranteeTermId = guaranteeTermId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public Reward getReward() {
		return reward;
	}

	public void setReward(Reward reward) {
		this.reward = reward;
	}

	public KeyPerformanceIndicator getKeyPerformanceIndicator() {
		return keyPerformanceIndicator;
	}

	public void setKeyPerformanceIndicator(
			KeyPerformanceIndicator keyPerformanceIndicator) {
		this.keyPerformanceIndicator = keyPerformanceIndicator;
	}
}
