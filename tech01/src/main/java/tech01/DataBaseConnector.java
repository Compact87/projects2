package tech01;

import javax.annotation.PostConstruct;
import javax.annotation.sql.DataSourceDefinition;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
@Singleton
@Startup
@DataSourceDefinition(name = "java:global/jdbc/tech01DS",
className = "com.mysql.jdbc.jdbc2.optional.MysqlDataSource",
serverName="localhost",
portNumber=3306,
user="root",
password="",
databaseName="tech01DB",
properties={"connectionAttributes=;create=true"})
public class DataBaseConnector {
/* 
 * Attributes
 */
@Inject
private ItemEJB itemEJB;

/* 
 * Populate db on start
 */
@PostConstruct
public void populateStartData() {
	itemEJB.createItem(new Item("Olovka", 23.99f));
	itemEJB.createBook(new Book("Hobbit", 20.55f,"Vulkan", 200, false));
	itemEJB.createMovie(new Movie("Matrix", 10.99f, "Universal", 120f));
}
}
