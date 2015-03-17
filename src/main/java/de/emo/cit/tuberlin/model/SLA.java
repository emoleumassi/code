package de.emo.cit.tuberlin.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table
public class SLA {

	@Id
	private int slaId;

	@Column(columnDefinition = "TEXT", nullable = false)
	private String description;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date startTime;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private short endTime;

//	@OneToOne(optional = false, targetEntity = UDDISLA.class, fetch = FetchType.LAZY)
//	@JoinColumn(name = "uddislaId", referencedColumnName = "uddislaId", insertable = true, updatable = true, nullable = false)
//	private UDDISLA uddisla;

	public int getSlaId() {
		return slaId;
	}

	public void setSlaId(int slaId) {
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

	public short getEndTime() {
		return endTime;
	}

	public void setEndTime(short endTime) {
		this.endTime = endTime;
	}
}
