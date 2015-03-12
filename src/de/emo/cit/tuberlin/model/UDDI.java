package de.emo.cit.tuberlin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class UDDI {

	@Id
	private int uddiId;

	@Column(columnDefinition = "VARCHAR(255)", nullable = false)
	private String description;

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
