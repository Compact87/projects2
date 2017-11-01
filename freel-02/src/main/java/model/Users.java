package model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Users {
 private List<User> users;

 
 public void addUser(User u) {this.users.add(u);}
 @XmlElement(name="userData")
public List<User> getUsers() {
	return users;
}

public void setUsers(List<User> users) {
	this.users = users;
}
 
 
}
