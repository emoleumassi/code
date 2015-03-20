package de.emo.cit.tuberlin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class UDDI {

	@Id
	@Column(columnDefinition = "VARCHAR(50)", nullable = false)
	private String uddiId;

	@Column(columnDefinition = "TEXT", nullable = false)
	private String description;

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

	@Override
	public String toString() {
		return "UDDI [uddiId=" + uddiId + ", description=" + description + "]";
	}
}
