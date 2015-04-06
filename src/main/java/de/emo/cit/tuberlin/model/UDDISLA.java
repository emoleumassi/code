package de.emo.cit.tuberlin.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author emoleumassi
 *
 */
@Entity
@Table
public class UDDISLA {

	@Id
	@Column(columnDefinition = "VARCHAR(50)", nullable = false)
	private String uddislaId;

	@Column(columnDefinition = "VARCHAR(100)", nullable = false)
	private String name;

	@Column(columnDefinition = "TEXT")
	private String description;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date startTime;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date endTime;

	@Column(columnDefinition = "VARCHAR(50)", nullable = false)
	private String email;

	@Column(columnDefinition = "VARCHAR(20)", nullable = false)
	private String phone;

	@Column(columnDefinition = "CHAR(5)", nullable = false)
	private String version;

	@OneToOne(mappedBy = "uddisla", targetEntity = UDDI.class, cascade = CascadeType.ALL)
	private UDDI uddi;

	@OneToOne(mappedBy = "uddisla", targetEntity = SLA.class, cascade = CascadeType.ALL)
	private SLA sla;

	public String getUddislaId() {
		return uddislaId;
	}

	public void setUddislaId(String uddislaId) {
		this.uddislaId = uddislaId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public UDDI getUddi() {
		return uddi;
	}

	public void setUddi(UDDI uddi) {
		this.uddi = uddi;
	}

	public SLA getSla() {
		return sla;
	}

	public void setSla(SLA sla) {
		this.sla = sla;
	}
}
