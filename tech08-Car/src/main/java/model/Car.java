package model;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity implementation class for Entity: Car
 *
 */

@Entity
@XmlRootElement(name="car")
@NamedQuery(name = Car.FIND_ALL, query = "SELECT c FROM Car c")
public class Car implements Serializable {

	 public static final String FIND_ALL = "Car.findAll";
	
	@Id@GeneratedValue
	private String id;
	private String make;
	private String model;
	private String color;
	private String year;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	private static final long serialVersionUID = 1L;

	public Car() {
		super();
	}

	public Car(String make, String model, String color, String year) {
		super();
		
		this.make = make;
		this.model = model;
		this.color = color;
		this.year = year;
	}

	@Override
	public String toString() {
		return "Car [id=" + id + ", make=" + make + ", model=" + model
				+ ", color=" + color + ", year=" + year + "]";
	}
   
}
