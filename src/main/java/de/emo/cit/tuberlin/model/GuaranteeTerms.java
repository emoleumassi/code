package de.emo.cit.tuberlin.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

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

	@OneToMany(mappedBy = "guaranteeTerms", targetEntity = KeyPerformanceIndicator.class, cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<KeyPerformanceIndicator> keyPerformanceIndicator;

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

	public List<KeyPerformanceIndicator> getKeyPerformanceIndicator() {
		return keyPerformanceIndicator;
	}

	public void setKeyPerformanceIndicator(
			List<KeyPerformanceIndicator> keyPerformanceIndicator) {
		this.keyPerformanceIndicator = keyPerformanceIndicator;
	}
}
