package de.emo.cit.tuberlin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "Method", uniqueConstraints = { @UniqueConstraint(columnNames = {
		"methodId", "name" }) })
public class Method {

	@Id
	@Column(name = "methodId")
	private int methodId;

	@Column(columnDefinition = "VARCHAR(100)", nullable = false)
	private String name;

	@Column(columnDefinition = "VARCHAR(255)", nullable = false)
	private String url;

	public int getMethodId() {
		return methodId;
	}

	public void setMethodId(int methodId) {
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
