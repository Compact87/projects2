package model;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@XmlRootElement
public class Status {
	@Id@GeneratedValue
	private Long Id;
	
	String status;
	String status_name;
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String s) {
		this.status = s;
	}
	public String getStatus_name() {
		return status_name;
	}
	public void setStatus_name() {
	  switch(this.status.toString()) {
	  case "NEW"                         : this.status_name="New";
	  break;
	  case "PENDING_ON_OPS"              : this.status_name="Pending OPS Step";
	  break;
	  case "PENDING_INTERNAL_VALIDATION" : this.status_name="Pending Internal Validation";
	  break;
	  case "PENDING_EXTERNAL_VALIDATION" : this.status_name="Pending External Validation";
	  break;
	  case "BUSINESS_VALIDATION_DONE"    : this.status_name="Business Validation Done";
	  break;
	  case "LIVE_IN_PRODUCTION"          : this.status_name="Live in Production ";
	  break;
	  default                            : throw new IllegalArgumentException("This should not have happened");
	  
		  }
	}
	
}
