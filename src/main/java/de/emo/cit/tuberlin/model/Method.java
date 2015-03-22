package de.emo.cit.tuberlin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

//@Entity
//@Table(uniqueConstraints = { @UniqueConstraint(columnNames = {
//		"methodId", "name", "url" }) })
public class Method {

	@Id
	@Column(columnDefinition = "VARCHAR(50)", nullable = false)
	private String methodId;

	@Column(columnDefinition = "VARCHAR(50)", nullable = false)
	private String name;

	@Column(columnDefinition = "VARCHAR(255)", nullable = false)
	private String url;

	@OneToOne(mappedBy = "method", targetEntity = KeyPerformanceIndicator.class, fetch = FetchType.LAZY)
	private KeyPerformanceIndicator keyPerformanceIndicator;

	public String getMethodId() {
		return methodId;
	}

	public void setMethodId(String methodId) {
		this.methodId = methodId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
