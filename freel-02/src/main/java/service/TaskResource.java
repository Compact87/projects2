package service;

import java.util.List;

import javax.ejb.ApplicationException;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import model.Task;
import model.Tasks;

@Path("tasks")
public class TaskResource {
  
	@Inject
	private EntityManager em;
	@Inject
	private Tasks tasks;
	@Inject 
	private Task task;
	
	
	
	
	@GET
	@Path("/available/{id}")
	@Produces("application/json")
	public Response getTaskByUser(@PathParam("id") Long id) {
		TypedQuery<Task> query=em.createQuery("SELECT t FROM Task t WHERE t.userId=:id", Task.class);
		query.setParameter("id", id);
		tasks.setTasks(query.getResultList());
		
		if(tasks==null) {return Response.status(Status.NOT_FOUND).build();}		
		
		return Response.ok(tasks).build();
		}
	@GET@Path("/mybin/{id}")
	@Produces("application/json")
	public Response getTaskByUserBin(@PathParam("id") Long id) {
		TypedQuery<Task> query=em.createQuery("SELECT t FROM Task t WHERE t.userId=:id", Task.class);
		query.setParameter("id", id);
		tasks.setTasks(query.getResultList());
		
		if(tasks==null) {return Response.status(Status.NOT_FOUND).build();}		
		
		return Response.ok(tasks).build();
		}
	@GET
	@Path("/assigned")
	@Produces("application/json")
	public Response gettAssigned() {
		TypedQuery<Task> query=em.createQuery("SELECT t FROM Task t WHERE t.status.status<>Live in Production ", Task.class);
		tasks.setTasks(query.getResultList());
		if(tasks==null) {return Response.status(Status.NOT_FOUND).build();}
		return Response.ok(tasks).build();
	   }
	@GET
	@Path("/completed")
	@Produces("application/json")
	public Response gettCompleted() {
		TypedQuery<Task> query=em.createQuery("SELECT t FROM Task t WHERE t.status.status=Live in Production ", Task.class);
		tasks.setTasks(query.getResultList());
		if(tasks==null) {return Response.status(Status.NOT_FOUND).build();}
		return Response.ok(tasks).build();
       }
	@PUT
	@Path("{id}")
	@Consumes("application/json")
	public Response newTask(@PathParam("id") Long id, Task t) {
		task=new Task();
		task.setUser(t.getUser());
		task.setSeverity_level(t.getSeverity_level());
		
		return Response.ok(task).build();
	}
	
}
