package service.dto;

import java.io.Serializable;
import model.Car;
import javax.persistence.EntityManager;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CarDTO implements Serializable
{

   private String color;
   private String year;
   private String model;
   private String id;
   private String make;

   public CarDTO()
   {
   }

   public CarDTO(final Car entity)
   {
      if (entity != null)
      {
         this.color = entity.getColor();
         this.year = entity.getYear();
         this.model = entity.getModel();
         this.id = entity.getId();
         this.make = entity.getMake();
      }
   }

   public Car fromDTO(Car entity, EntityManager em)
   {
      if (entity == null)
      {
         entity = new Car();
      }
      entity.setColor(this.color);
      entity.setYear(this.year);
      entity.setModel(this.model);
      entity.setMake(this.make);
      entity = em.merge(entity);
      return entity;
   }

   public String getColor()
   {
      return this.color;
   }

   public void setColor(final String color)
   {
      this.color = color;
   }

   public String getYear()
   {
      return this.year;
   }

   public void setYear(final String year)
   {
      this.year = year;
   }

   public String getModel()
   {
      return this.model;
   }

   public void setModel(final String model)
   {
      this.model = model;
   }

   public String getId()
   {
      return this.id;
   }

   public void setId(final String id)
   {
      this.id = id;
   }

   public String getMake()
   {
      return this.make;
   }

   public void setMake(final String make)
   {
      this.make = make;
   }
}