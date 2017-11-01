package service;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import model.User;
import model.Users;

@Path("/users")
public class UserResource {
	
	@Inject
	private EntityManager em;
	@Inject
	private User user;
	@Inject 
	private Users users;
 @GET
 @Path("{id}")
 @Produces("application/json")
 public Response getUserById(@PathParam("id") Long id) {
	user= em.find(User.class, id);
	return Response.ok(user).build();
 }
 @GET@Path("/")
 @Produces("application/json")
 public Response getAllUsers() {
	 TypedQuery<User> query=em.createQuery("SELECT t FROM User t", User.class);
	 users.setUsers(query.getResultList());
	 return Response.ok(users).build();
 }
}
