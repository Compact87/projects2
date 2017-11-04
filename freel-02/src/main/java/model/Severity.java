package model;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.xml.bind.annotation.XmlRootElement;


@Entity
@XmlRootElement
public class Severity {
	@Id@GeneratedValue
	private Long Id;
	private int severity_level;
	private String severity_name;
	
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public int getSeverity_level() {
		return severity_level;
	}
	public void setSeverity_level(int severity_level) {
		this.severity_level = severity_level;
	}
	public void setSeverityName() {
		switch(severity_level) {
		case 0: this.severity_name="LOW";
		case 1: this.severity_name="MEDIUM";
		case 2: this.severity_name="HIGH";
		case 3: this.severity_name="CRITICAL";
		default: throw new IllegalArgumentException("Breached security level");
		}
	}
	public String getSeverity_name() {
		return severity_name;
	}
	public void setSeverity_name(String severity_name) {
		this.severity_name = severity_name;
	}
	
	
    


}
