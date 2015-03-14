package de.emo.cit.tuberlin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class KeyPerformanceIndicator {

	@Id
	@Column(name = "keyPerformanceIndicatorId")
	private int keyPerformanceIndicatorId;

	@Column(columnDefinition = "VARCHAR(100)", nullable = false)
	private String designation;

	@Column(columnDefinition = "VARCHAR(5)", nullable = false)
	private String targetValue;

	@Column(columnDefinition = "VARCHAR(5)", nullable = false)
	private String qualifyingCondiction;
	
	@OneToOne(optional = false, targetEntity = Method.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "methodId", referencedColumnName = "methodId", insertable = true, updatable = true, nullable = false)
	private Method method;

	public int getKeyPerformanceIndicatorId() {
		return keyPerformanceIndicatorId;
	}

	public void setKeyPerformanceIndicatorId(int keyPerformanceIndicatorId) {
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
}
