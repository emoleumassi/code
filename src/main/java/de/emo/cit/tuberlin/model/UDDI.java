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
	@Column(columnDefinition = "VARCHAR(50)", nullable = false)
	private String uddiId;

	@Column(columnDefinition = "TEXT", nullable = false)
	private String description;

	@OneToOne(optional = false, targetEntity = OverviewDoc.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "overviewDocId", referencedColumnName = "overviewDocId", insertable = true, updatable = true, nullable = false)
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

	@Override
	public String toString() {
		return "UDDI [uddiId=" + uddiId + ", description=" + description
				+ ", overviewDoc=" + overviewDoc + "]";
	}
}
