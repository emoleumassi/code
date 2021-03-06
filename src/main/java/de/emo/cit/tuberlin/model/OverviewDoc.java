package de.emo.cit.tuberlin.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class OverviewDoc {

	@Id
	@Column(columnDefinition = "VARCHAR(50)", nullable = false)
	private String overviewDocId;

	@Column(columnDefinition = "TEXT", nullable = false)
	private String description;
	
	@Column(columnDefinition = "VARCHAR(255)", nullable = false)
	private String overviewURL;

	@OneToOne(targetEntity = UDDI.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "uddiId", columnDefinition = "VARCHAR(50) default 'xxxxx'", referencedColumnName = "uddiId", insertable = true, updatable = true, nullable = true)
	private UDDI uddi;
	
	public String getOverviewDocId() {
		return overviewDocId;
	}

	public void setOverviewDocId(String overviewDocId) {
		this.overviewDocId = overviewDocId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOverviewURL() {
		return overviewURL;
	}

	public void setOverviewURL(String overviewURL) {
		this.overviewURL = overviewURL;
	}
}
