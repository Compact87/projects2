package controller;



import javax.annotation.PostConstruct;
import javax.annotation.sql.DataSourceDefinition;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import model.Car;




@Model
@DataSourceDefinition(name = "java:global/jdbc/kitchen",
className = "com.mysql.jdbc.jdbc2.optional.MysqlDataSource",
serverName="localhost",
portNumber=3306,
user="root",
password="",
databaseName="javatest",
properties={"connectionAttributes=;create=true"}
)
public class Controller {
	
	 @Produces
	 @Named
	    private Car newCar;
	 @PersistenceContext(name = "tech08-Car")
	  private EntityManager em;
	 
	 @PostConstruct
	 public void initNewCar() {newCar= new Car();}
    @Transactional
	 public void createCar() {
		 em.persist(this.newCar);
		 
	 }
}
