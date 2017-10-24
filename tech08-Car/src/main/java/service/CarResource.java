package service;



import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.sql.DataSourceDefinition;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.PathSegment;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.persistence.*;

import service.dto.CarDTO;
import model.Car;
import model.Cars;



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
databaseName="car",
properties={"connectionAttributes=;create=true"}
)
public class CarResource {
	public static enum Color{
		red,
		white,
		blue,
		black
	}
	@PersistenceContext(name = "tech08-Car")
	  private EntityManager em;
	 @Context
	  private UriInfo uriInfo;
	 
	 /*
	  * GET MESSAGE FROM SERVICE
	  */
	 @GET
		@Path("message")
		@Produces({MediaType.TEXT_PLAIN,MediaType.APPLICATION_JSON})
		public Response sayText(){
			String hello="Hello World!";
			return Response.ok(hello).build() ;
		}
	 /*
	  * GET A SPECIFIC CAR
	  */
	 
   @GET
   @Path("/matrix/{make}/{model}/{year}")
   @Produces("application/json")
    public Response getFromMatrixParam(
       @PathParam("make")String make,
       @PathParam("model")PathSegment car,
       @MatrixParam("color")Color color,
       @PathParam("year")String year) {
	   
	 String carColor=car.getMatrixParameters().getFirst("color");
	 TypedQuery<Car> query=em.createQuery("SELECT DISTINCT c FROM Car c WHERE c.color=:color", Car.class);
	 query.setParameter("color", carColor);
	 Cars cars=new Cars();
	 cars.setCars(query.getResultList());
	 return Response.ok(cars).build();

	 
    }
  
   /*
    * POST A CAR
    */
   
    @POST
      public Response setCar(Car car) {
	    if (car == null)
	      throw new BadRequestException();

	    em.persist(car);
	   
	    URI carUri = uriInfo.getAbsolutePathBuilder().path(car.getId()).build();
	    return Response.created(carUri).build();
    }
    /*
     * GET CAR BY ID
     */
    @GET
    @Path("single/{id}")
    @Produces("application/json")
    public Response getById(@PathParam("id") String id) {
    	Car car=em.find(Car.class, id);
    	if (car==null) throw new WebApplicationException(Response.Status.NOT_FOUND);
    	Response.ResponseBuilder builder = Response.ok(car);
    	if (!car.isSold()) addBuyHeader(uriInfo, builder);
    	return builder.build();
    }
    
    /*
     * GET ALL CARS
     */
    @GET
    @Produces("application/xml")
    public Response getAllCars() {
    	TypedQuery<Car> query = em.createNamedQuery(Car.FIND_ALL, Car.class);
    	Cars cars=new Cars();
    	cars.setCars(query.getResultList());
    	return Response.ok(cars).build();
    }
    
    /*
     *  GET ALL CARS IN BATCHES
     */
    @GET
    @Path("/ids")
    @Produces("text/plain")
    public String listAll(@QueryParam("start")  Integer start,
 		   						@QueryParam("size") Integer size,
 		   							@Context UriInfo uriInfo)
    {
 	   UriBuilder builder = uriInfo.getAbsolutePathBuilder();
 	   builder.queryParam("start", "{start}");
 	   builder.queryParam("size", "{size}"); 
 	  TypedQuery<Car> query=em.createQuery("SELECT DISTINCT c FROM Car c  ORDER BY c.id", Car.class);
      if (start != null)
      {
         query.setFirstResult(start);
      }
      if (size != null)
           {
         query.setMaxResults(size);
      }
      int next=start+size;
      int previous=start-size;
      
      Cars cars=new Cars();
      cars.setCars(query.getResultList());
      URI nextUri = builder.clone().build(next, size);
          Link nextLink = Link.fromUri(nextUri).rel("next").type("application/xml").build();
         
		
      
      // previous link
      if (start > 0)
      {
      
      if (previous < 0) previous = 0;
      URI previousUri = builder.clone().build(previous, size);
      Link previousLink = Link.fromUri(previousUri)
      .rel("previous")
      .type("application/xml").build();
    
      }
      
 	Link link= Link.fromUriBuilder(builder).type("text/plain").build();
 	
 	   String string=link.toString();
     return string;
    }
  /*
   * ADD LINK IN HEADER
   */
    protected void addBuyHeader(UriInfo uriInfo,
    		Response.ResponseBuilder builder)
    		{
          		UriBuilder absolute = uriInfo.getAbsolutePathBuilder();
    		    URI buyUrl = absolute.clone().path("buy").build();
    		    builder.links(Link.fromUri(buyUrl).rel("buy").build());
    		}
    /*
     * UPDATE
     */
    
}

