package de.emo.cit.tuberlin.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table
public class SLA {

	@Id
	@Column(columnDefinition = "VARCHAR(50)", nullable = false)
	private String slaId;

	@Column(columnDefinition = "TEXT", nullable = false)
	private String description;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date startTime;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date endTime;

	@OneToOne(targetEntity = UDDISLA.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "uddislaId", columnDefinition = "VARCHAR(50) default 'xxxxx'", referencedColumnName = "uddislaId", insertable = true, updatable = true, nullable = true)
	private UDDISLA uddisla;

	public String getSlaId() {
		return slaId;
	}

	public void setSlaId(String slaId) {
		this.slaId = slaId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
}
