package model;

import javax.annotation.PostConstruct;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PostUpdate;
import javax.xml.bind.annotation.XmlRootElement;
@Entity
@XmlRootElement
@NamedQueries(
		@NamedQuery(name="find all Tasks", query="SELECT t FROM Task t ORDER BY t.task_id DESC"))
public class Task {
	@Id@GeneratedValue
    private Long task_id;
	@Column(name="PROCESS_ID")
	private Long processId;
	private String procesName;
	@Column(name="CURRENT_OWNER")
    private User user;
	@Column(name="DEV_USER")
	private User devUser;
	@Column(name="OPS_USER")
	private User opsUser;
	@Column(name="INV_USER")
	private User invUser;
	@Column(name="EXV_USER")
	private User exvUser;
	private Severity severity;
	private Status status;
	@Column(name="SEVERITY_LEVEL")
	private Severity severityLevel;
    private boolean opsStep= false;
    private Long userId;
    
 
    /*
     * CONSTRUCTORS
     */
 
 public void setUserID() {
	     this.userId=user.getId();
		
	}
public Long getUserId() {return this.userId;}


public Task() {}
 
 

	/*
	 * GETTERS, SETTERS
	 */
 
 public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}
	
    public int getSeverity_level() {
		return severity.getSeverity_level();
 	 }
	



	public String getSeverityName() {
		return severity.getSeverity_name();
	 }
	public void setSeverity_level(int severity_level) {
		this.severity.setSeverity_level(severity_level);
		this.severity.setSeverityName();
	 }

	public Status getStatus() {
		return status;
	 }
 @PostConstruct
 @PostUpdate
	public void setStatus() {
		switch(user.getSecGroup().toString()) {
		case "DEVELOPER"         : status.setStatus("NEW");
		break;
		case "OPS"               : status.setStatus("PENDING_ON_OPS");
		break;
		case "INTERNAL_VALIDATOR": status.setStatus("PENDING_INTERNAL_VALIDATION");
		break;
		case "EXTERNAL_VALIDATOR": status.setStatus("PENDING_EXTERNAL_VALIDATION");
		break;
		case "LEAD"              : status.setStatus("BUSINESS_VALIDATION_DONE");
		break;
		case "MANAGER"           : status.setStatus("LIVE_IN_PRODUCTION");
		break;
		default                  : throw new IllegalArgumentException("invalid user group");
		};
	 }



public Long getProcessId() {
	return processId;
}


@PostConstruct
public void setProcessId(Long processId) {
	this.processId = this.task_id;
}



public String getProcesName() {
	return procesName;
}


@PostConstruct
public void setProcesName() {
	this.procesName = "proces name"+ this.processId.toString();
}



public User getDevUser() {
	return devUser;
}


@PostConstruct
@PostUpdate
public void setDevUser(User devUser) {
	if(this.user.getSecGroup().toString()=="DEVELOPER") {
	this.devUser = devUser;}
}



public User getOpsUser() {
	return opsUser;
}


@PostConstruct
@PostUpdate
public void setOpsUser(User opsUser) {
	if(this.user.getSecGroup().toString()=="OPS") {
		this.opsUser = opsUser;}
	
}



public User getInvUser() {
	return invUser;
}


@PostConstruct
@PostUpdate
public void setInvUser(User invUser) {
	if(this.user.getSecGroup().toString()=="INTERNAL_VALIDATOR") {
		this.invUser = invUser;}
	
	
}



public User getExvUser() {
	return exvUser;
}


@PostConstruct
@PostUpdate
public void setExvUser(User exvUser) {
	if(this.user.getSecGroup().toString()=="EXTERNAL_VALIDATOR") {
		this.exvUser = exvUser;}
	
}
@PostUpdate
public void setOpsStep() {
	if(this.user.getSecGroup().toString()=="OPS") {this.opsStep=true;}
	
}
public boolean getOpsStep() {return this.opsStep;}
public Task(User user, Severity severity) {
	super();
	this.user = user;
	this.severity = severity;
}



	



}
