package de.emo.cit.tuberlin.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 
 * @author emoleumassi
 * 
 */
@Entity
@Table
public class Reward {

	@Id
	@Column(columnDefinition = "VARCHAR(50)", nullable = false)
	private String rewardId;

	@Column(columnDefinition = "INTEGER", nullable = false)
	private int count;

	@Column(columnDefinition = "TEXT")
	private String description;

	@Column(columnDefinition = "DECIMAL(10, 2)", nullable = false)
	private short valueUnit;

	@Column(columnDefinition = "VARCHAR(10)", nullable = false)
	private String timeInterval;

	@OneToOne(targetEntity = GuaranteeTerms.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "guaranteeTermId", columnDefinition = "VARCHAR(50) default 'xxxxx'", referencedColumnName = "guaranteeTermId", insertable = true, updatable = true, nullable = true)
	private GuaranteeTerms guaranteeTerms;

	public String getRewardId() {
		return rewardId;
	}

	public void setRewardId(String rewardId) {
		this.rewardId = rewardId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public short getValueUnit() {
		return valueUnit;
	}

	public void setValueUnit(short valueUnit) {
		this.valueUnit = valueUnit;
	}

	public String getTimeInterval() {
		return timeInterval;
	}

	public void setTimeInterval(String timeInterval) {
		this.timeInterval = timeInterval;
	}
}
