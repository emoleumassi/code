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
public class UDDI {

	@Id
	private int uddiId;

	@Column(columnDefinition = "TEXT", nullable = false)
	private String description;

	@OneToOne(optional = false, targetEntity = UDDISLA.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "uddislaId", referencedColumnName = "uddislaId", insertable = true, updatable = true, nullable = false)
	private UDDISLA uddisla;
	
	public int getUddiId() {
		return uddiId;
	}

	public void setUddiId(int uddiId) {
		this.uddiId = uddiId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
