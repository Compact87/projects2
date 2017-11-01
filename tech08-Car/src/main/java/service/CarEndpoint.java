package service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;





import model.Car;
import service.dto.CarDTO;

/**
 * 
 */
@Stateless
@Path("forge/cars")
public class CarEndpoint
{
   @PersistenceContext(unitName = "tech08-Car")
   private EntityManager em;

   @POST
   @Consumes("application/json")
   public Response create(CarDTO dto)
   {
      Car entity = dto.fromDTO(null, em);
      em.persist(entity);
      return Response.created(UriBuilder.fromResource(CarEndpoint.class).path(String.valueOf(entity.getId())).build()).build();
   }

   @DELETE
   @Path("/{id:[0-9][0-9]*}")
   public Response deleteById(@PathParam("id") String id)
   {
      Car entity = em.find(Car.class, id);
      if (entity == null)
      {
         return Response.status(Status.NOT_FOUND).build();
      }
      em.remove(entity);
      return Response.noContent().build();
   }

   @GET
   @Path("/{id:[0-9][0-9]*}")
   @Produces("application/json")
   public Response findById(@PathParam("id") String id)
   {
      TypedQuery<Car> findByIdQuery = em.createQuery("SELECT DISTINCT c FROM Car c WHERE c.id = :entityId ORDER BY c.id", Car.class);
      findByIdQuery.setParameter("entityId", id);
      Car entity;
      try
      {
         entity = findByIdQuery.getSingleResult();
      }
      catch (NoResultException nre)
      {
         entity = null;
      }
      if (entity == null)
      {
         return Response.status(Status.NOT_FOUND).build();
      }
      CarDTO dto = new CarDTO(entity);
      return Response.ok(dto).build();
   }

   @GET
   @Produces("application/json")
   @Path("/query")
   public List<CarDTO> listAll()
   {
	
	   Query query=em.createQuery("SELECT c FROM Car WHERE  c.id>2", Car.class);
	   
	
	   
	   
      final List<Car> searchResults = query.getResultList();
      final List<CarDTO> results = new ArrayList<CarDTO>();
      for (Car searchResult : searchResults)
      {
         CarDTO dto = new CarDTO(searchResult);
         results.add(dto);
      }
      return results;
   }

   @PUT
   @Path("/{id:[0-9][0-9]*}")
   @Consumes("application/json")
   public Response update(@PathParam("id") String id, CarDTO dto)
   {
      TypedQuery<Car> findByIdQuery = em.createQuery("SELECT DISTINCT c FROM Car c WHERE c.id = :entityId ORDER BY c.id", Car.class);
      findByIdQuery.setParameter("entityId", id);
      Car entity;
      try
      {
         entity = findByIdQuery.getSingleResult();
      }
      catch (NoResultException nre)
      {
         entity = null;
      }
      entity = dto.fromDTO(entity, em);
      entity = em.merge(entity);
      return Response.noContent().build();
   }
}