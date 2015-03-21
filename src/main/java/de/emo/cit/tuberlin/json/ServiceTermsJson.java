package de.emo.cit.tuberlin.json;


public class ServiceTermsJson {

	private String name;
	private String designation;
	private String description;
	private String costPerUnitOfAccount;
	private int unitOfAccount;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCostPerUnitOfAccount() {
		return costPerUnitOfAccount;
	}

	public void setCostPerUnitOfAccount(String costPerUnitOfAccount) {
		this.costPerUnitOfAccount = costPerUnitOfAccount;
	}

	public int getUnitOfAccount() {
		return unitOfAccount;
	}

	public void setUnitOfAccount(int unitOfAccount) {
		this.unitOfAccount = unitOfAccount;
	}
}
