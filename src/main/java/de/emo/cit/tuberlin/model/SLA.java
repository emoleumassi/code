package de.emo.cit.tuberlin.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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

	@OneToMany(mappedBy = "sla", targetEntity = GuaranteeTerms.class, cascade = CascadeType.ALL)
	private List<GuaranteeTerms> guaranteeTerms;

	@OneToMany(mappedBy = "sla", targetEntity = ServiceTerms.class, cascade = CascadeType.ALL)
	private List<ServiceTerms> serviceTerms;
	
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

	public List<GuaranteeTerms> getGuaranteeTerms() {
		return guaranteeTerms;
	}

	public void setGuaranteeTerms(List<GuaranteeTerms> guaranteeTerms) {
		this.guaranteeTerms = guaranteeTerms;
	}

	public List<ServiceTerms> getServiceTerms() {
		return serviceTerms;
	}

	public void setServiceTerms(List<ServiceTerms> serviceTerms) {
		this.serviceTerms = serviceTerms;
	}
}
