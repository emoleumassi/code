package de.emo.cit.tuberlin.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * 
 * @author emoleumassi
 * 
 */
@Entity
@Table
public class SLA {

	@Id
	@Column(columnDefinition = "VARCHAR(50)", nullable = false)
	private String slaId;

	@Column(columnDefinition = "VARCHAR(100)")
	private String name;
	
	@Column(columnDefinition = "TEXT")
	private String description;

	@Column(columnDefinition = "VARCHAR(50)")
	private String templateId;
	
	@Column(columnDefinition = "VARCHAR(50)")
	private String templateName;

	@OneToOne(targetEntity = UDDISLA.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "uddislaId", columnDefinition = "VARCHAR(50) default 'xxxxx'", referencedColumnName = "uddislaId", insertable = true, updatable = true, nullable = true)
	private UDDISLA uddisla;

	@OneToMany(mappedBy = "sla", targetEntity = GuaranteeTerms.class, cascade = CascadeType.ALL, orphanRemoval = true)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<GuaranteeTerms> guaranteeTerms = new ArrayList<>();

	@OneToMany(mappedBy = "sla", targetEntity = ServiceTerms.class, orphanRemoval = true, cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<ServiceTerms> serviceTerms = new ArrayList<>();

	public String getSlaId() {
		return slaId;
	}

	public void setSlaId(String slaId) {
		this.slaId = slaId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public List<GuaranteeTerms> getGuaranteeTerms() {
		return guaranteeTerms;
	}

	public void setGuaranteeTerms(List<GuaranteeTerms> guaranteeTerms) {
		this.guaranteeTerms = guaranteeTerms;
	}

	public List<ServiceTerms> getServiceTerms() {
		return serviceTerms;
	}

	public void setServiceTerms(List<ServiceTerms> serviceTerms) {
		this.serviceTerms = serviceTerms;
	}
}
