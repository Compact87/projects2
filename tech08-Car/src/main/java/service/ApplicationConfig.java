package service;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
@ApplicationPath("services")
public class ApplicationConfig extends Application {

	  // ======================================
	  // =             Attributes             =
	  // ======================================

	  private final Set<Class<?>> classes;

	  // ======================================
	  // =            Constructors            =
	  // ======================================

	  public ApplicationConfig() {
	    HashSet<Class<?>> c = new HashSet<>();
	    c.add(CarResource.class);
	    c.add(CarEndpoint.class);
	    c.add(ShopResource.class);
	    c.add(Buy.class);

	   
	    classes = Collections.unmodifiableSet(c);
	  }

	  // ======================================
	  // =          Getters & Setters         =
	  // ======================================

	  @Override
	  public Set<Class<?>> getClasses() {
	    return classes;
	  }
	}