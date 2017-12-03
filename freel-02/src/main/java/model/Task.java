package model;

import javax.annotation.PostConstruct;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
	@JoinColumn(name="CURRENT_OWNER")
    private User user;
	@Column(name="DEV_USER")
	private Long devUserId;
	@Column(name="OPS_USER")
	private Long opsUserId;
	@Column(name="INV_USER")
	private Long invUserId;
	@Column(name="EXV_USER")
	private Long exvUserId;
	private Severity severity;
	private Status status;
	@JoinColumn(name="SEVERITY_LEVEL")
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
		case "DEVELOPER"         : status.setStatus("NEW");this.invUserId=user.getId();
		break;
		case "OPS"               : status.setStatus("PENDING_ON_OPS");this.opsUserId = user.getId();this.opsStep=true;
		break;
		case "INTERNAL_VALIDATOR": status.setStatus("PENDING_INTERNAL_VALIDATION");this.invUserId = user.getId();
		break;
		case "EXTERNAL_VALIDATOR": status.setStatus("PENDING_EXTERNAL_VALIDATION");this.exvUserId = user.getId();
		break;
		case "LEAD"              : status.setStatus("BUSINESS_VALIDATION_DONE");
		break;
		case "MANAGER"           : status.setStatus("LIVE_IN_PRODUCTION");
		break;
		default                  : throw new IllegalArgumentException("invalid user group");
		};
		this.processId = this.task_id;
		this.procesName = "proces name"+ this.processId.toString();
	 }



public Long getProcessId() {
	return processId;
}






public String getProcesName() {
	return procesName;
}






public Long getDevUser() {
	return devUserId;
}






public Long getOpsUser() {
	return opsUserId;
}





public Long getInvUser() {
	return invUserId;
}



	
	




public Long getExvUser() {
	return exvUserId;
}



	

public boolean getOpsStep() {return this.opsStep;}
public Task(User user, Severity severity) {
	super();
	this.user = user;
	this.severity = severity;
}



	



}
