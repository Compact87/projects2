package model;

import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class Tasks {
	private List<Task> tasks;
	
	public void addTasks(Task t) {tasks.add(t);}
	public int getTasksSize() {return this.tasks.size();}
	@XmlElement(name="taskData")
	public Collection<Task> getTasks(){return this.tasks;}
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

}
