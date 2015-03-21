package de.emo.cit.tuberlin.json;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "uddisla"/*, propOrder = { "name", "description", "state",
		"email", "phone", "version", "uddi", "sla" }*/)
public class UDDISLAJson {

	@XmlElement(required = true)
	private String name;

	@XmlElement(required = true)
	private String description;

	@XmlElement(required = true)
	private String state;

	@XmlElement(required = true)
	private String email;

	@XmlElement(required = true)
	private String phone;

	@XmlElement(required = true)
	private String version;

	@XmlElement(name = "uddi", required = true)
	private UDDIJson uddi;

	@XmlElement(name = "sla", required = true)
	private SLAJson sla;

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

	public UDDIJson getUddiJson() {
		return uddi;
	}

	public void setUddiJson(UDDIJson uddiJson) {
		this.uddi = uddiJson;
	}

	public SLAJson getSlaJson() {
		return sla;
	}

	public void setSlaJson(SLAJson slaJson) {
		this.sla = slaJson;
	}
}
