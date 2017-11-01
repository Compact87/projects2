package service;

import javax.annotation.sql.DataSourceDefinition;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import model.SecurityGroup;
import model.User;
import model.Users;

@Path("/users")
@DataSourceDefinition(name = "java:global/jdbc/freel02DS",
className = "com.mysql.jdbc.jdbc2.optional.MysqlDataSource",
serverName="localhost",
portNumber=3306,
user="root",
password="",
databaseName="freel02",
properties={"connectionAttributes=;create=true"}
)
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
 @PUT
 @Produces("application/json")
 public Response updateUser(User u) {
	 user=em.find(User.class, u.getId());
	 user.setUsername(u.getUsername());
	 user.setPassword(u.getPassword());
	 user.setFirstName(u.getFirstName());
	 user.setLastName(u.getLastName());
	 user.setEmail(u.getEmail());
	 user.setSecGroup(u.getSecGroup());
	 em.merge(user);
	 return Response.noContent().build();
 }
 @POST
 @Path("signup")
 public Response signup(@FormParam("username") String username,
		 				@FormParam("password") String password,
		 				@FormParam("firstname") String firstname,
		 				@FormParam("lastname") String lastname,
		 				@FormParam("email") String email) {
	 user.setEmail(email);
	 user.setUsername(username);
	 user.setFirstName(firstname);
	 user.setLastName(lastname);
	 user.setPassword(password);
	 user.setSecGroup(SecurityGroup.OPS);
	 return Response.ok().build();}
}
