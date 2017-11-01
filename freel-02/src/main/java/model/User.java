package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class User {
	@Id@GeneratedValue
	private Long id;
	private String Username;
	private String Password;
	private String firstName;
	private String lastName;
	private String email;
	private SecurityGroup secGroup;
	
	public User () {}
	
	
	public User(String username, String password, String firstName,
			String lastName, String email, SecurityGroup secGroup) {
		super();
		Username = username;
		Password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.secGroup = secGroup;
	}


	/*
	 * GETTERS SETTERS
	 */
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public SecurityGroup getSecGroup() {
		return secGroup;
	}

	public void setSecGroup(SecurityGroup secGroup) {
		this.secGroup = secGroup;
	}
	
	
     
}
