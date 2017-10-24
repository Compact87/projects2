package model;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ws.rs.core.Link;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
@XmlRootElement
public class Cars  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Collection<Car> cars;
	private List<Link> links = new ArrayList<Link>();
	
	
	

    @XmlElement(name="link")
    @XmlJavaTypeAdapter(Link.JaxbAdapter.class)
	public List<Link> getLinks() {
		return links;
	}


	public void setLinks(List<Link> links) {
		this.links = links;
	}


	public void addcars(Car car) {cars.add(car);}
	
	
	@XmlElement(name="cars")
	public Collection<Car> getCars() {
		return cars;
	}
	public void setCars(List<Car> cars) {
		this.cars = cars;
	}
	
	public Cars() {
		super();
	}
	
}
