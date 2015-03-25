package de.emo.cit.tuberlin.model;

import javax.persistence.CascadeType;
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
	@Column(columnDefinition = "VARCHAR(50)", nullable = false)
	private String uddiId;

	@Column(columnDefinition = "TEXT", nullable = false)
	private String description;

	@OneToOne(targetEntity = UDDISLA.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "uddislaId", columnDefinition = "VARCHAR(50) default 'xxxxx'", referencedColumnName = "uddislaId", insertable = true, updatable = true, nullable = true)
	private UDDISLA uddisla;

	@OneToOne(mappedBy = "uddi", targetEntity = OverviewDoc.class, cascade = CascadeType.ALL)
	private OverviewDoc overviewDoc;

	public String getUddiId() {
		return uddiId;
	}

	public void setUddiId(String uddiId) {
		this.uddiId = uddiId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public OverviewDoc getOverviewDoc() {
		return overviewDoc;
	}

	public void setOverviewDoc(OverviewDoc overviewDoc) {
		this.overviewDoc = overviewDoc;
	}
}
