package de.emo.cit.tuberlin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//@Entity
//@Table
public class OverviewDoc {

	@Id
	private int overviewDocId;

	@Column(columnDefinition = "TEXT", nullable = false)
	private String description;
	
	@Column(columnDefinition = "VARCHAR(255)", nullable = false)
	private String overviewUrl;
	
//	@OneToOne(optional = false, targetEntity = UDDI.class, fetch = FetchType.LAZY)
//	@JoinColumn(name = "uddiId", referencedColumnName = "uddiId", insertable = true, updatable = true, nullable = false)
//	private UDDI uddi;

	public int getOverviewDocId() {
		return overviewDocId;
	}

	public void setOverviewDocId(int overviewDocId) {
		this.overviewDocId = overviewDocId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOverviewUrl() {
		return overviewUrl;
	}

	public void setOverviewurl(String overviewurl) {
		this.overviewUrl = overviewurl;
	}
}
