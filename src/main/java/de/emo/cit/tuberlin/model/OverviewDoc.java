package de.emo.cit.tuberlin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
	private String overviewUrl;

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

	public String getOverviewUrl() {
		return overviewUrl;
	}

	public void setOverviewurl(String overviewurl) {
		this.overviewUrl = overviewurl;
	}

	@Override
	public String toString() {
		return "OverviewDoc [overviewDocId=" + overviewDocId + ", description="
				+ description + ", overviewUrl=" + overviewUrl + "]";
	}
}
