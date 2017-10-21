package service;



import java.net.URI;

import javax.annotation.sql.DataSourceDefinition;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.PathSegment;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.persistence.*;

import model.Car;



@Path("/cars")
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Stateless
@DataSourceDefinition(name = "java:global/jdbc/tech08DS",
className = "com.mysql.jdbc.jdbc2.optional.MysqlDataSource",
serverName="localhost",
portNumber=3306,
user="root",
password="",
databaseName="tech08",
properties={"connectionAttributes=;create=true"}
)
public class CarResource {
	public static enum Color{
		red,
		white,
		blue,
		black
	}
	@PersistenceContext(unitName = "tech08-Car")
	  private EntityManager em;
	 @Context
	  private UriInfo uriInfo;
	 
	 @GET
		@Path("message")
		@Produces({MediaType.TEXT_PLAIN,MediaType.APPLICATION_JSON})
		public Response sayText(){
			String hello="Hello World!";
			return Response.ok(hello).build() ;
		}
	 
   @GET
   @Path("/matrix/{make}/{model}/{year}")
   @Produces("text/plain")
    public String getFromMatrixParam(
       @PathParam("make")String make,
       @PathParam("model")PathSegment car,
       @MatrixParam("color")Color color,
       @PathParam("year")String year) {
	   
	 String carColor=car.getMatrixParameters().getFirst("color");
	 return "A " + carColor + " " + year + " "+ make + " " + car.getPath();
	 
    }
  
   
   
    @POST
      public Response setCar(Car car) {
	    if (car == null)
	      throw new BadRequestException();

	    em.persist(car);
	   
	    URI carUri = uriInfo.getAbsolutePathBuilder().path(car.getId()).build();
	    return Response.created(carUri).build();
    }
    @GET
    @Path("{id}")
    @Produces("application/json")
    public Response getById(@PathParam("id") String id) {
    	Car car=em.find(Car.class, id);
    	if (car==null) Response.status(Status.NOT_FOUND).build();
    	return Response.ok(car).build();
    }
    @GET
    @Produces("application/xml")
    public Response getAllCars() {
    	TypedQuery<Car> query = em.createNamedQuery(Car.FIND_ALL, Car.class);
    	Cars cars=new Cars(query.getResultList());
    	return Response.ok(cars).build();
    }
  
}

