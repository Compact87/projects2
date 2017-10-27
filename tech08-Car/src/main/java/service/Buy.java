package service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.ResponseBuilder;

import model.Car;

@Path("/cars/single/{id}/buy")
public class Buy {
	@PersistenceContext(name = "tech08-Car")
	  private EntityManager em;
	 @Context
	  private UriInfo uriInfo;
	
	 
	 
	 @PUT
	 public Response buyCar(@PathParam("id") String id,
			 				@Context Request request) {
		 Car car=em.find(Car.class, id);
		 EntityTag etag=new EntityTag(Integer.toString(car.hashCode()));
		 CacheControl cc=new CacheControl();
		 cc.setMaxAge(1000);
		 ResponseBuilder builder=request.evaluatePreconditions(etag);
		 if(builder!=null) {
		 return builder.build();}
		 car.setSold(true);
		 em.merge(car);
		 
		 return Response.noContent().build();
	 }
}
