package de.emo.cit.tuberlin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class OverviewDoc {

	@Id
	private int overviewDocId;

	@Column(columnDefinition = "VARCHAR(255)", nullable = false)
	private String description;
	
	@Column(columnDefinition = "VARCHAR(255)", nullable = false)
	private String overviewurl;

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

	public String getOverviewurl() {
		return overviewurl;
	}

	public void setOverviewurl(String overviewurl) {
		this.overviewurl = overviewurl;
	}
}
