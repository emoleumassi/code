package de.emo.cit.tuberlin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Check;

//@Entity
//@Check(constraints = "state in('pending', 'observed', 'rejected', 'completed')")
//@Table
public class UDDISLA {

	@Id
	private int uddislaId;

	@Column(columnDefinition = "VARCHAR(100)", nullable = false)
	private String name;

	@Column(columnDefinition = "TEXT", nullable = false)
	private String description;

	@Column(columnDefinition = "VARCHAR(10)", nullable = false)
	private String state;

	@Column(columnDefinition = "VARCHAR(50)", nullable = false)
	private String email;

	@Column(columnDefinition = "VARCHAR(20)", nullable = false)
	private String phone;

	@Column(columnDefinition = "CHAR(5)", nullable = false)
	private String version;

	public int getUddislaId() {
		return uddislaId;
	}

	public void setUddislaId(int uddislaId) {
		this.uddislaId = uddislaId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
}