package service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import model.Car;
@XmlRootElement
@XmlSeeAlso(Car.class)
public class Cars extends ArrayList<Car> {
	
	public Cars() {super();}
	
	public Cars(Collection<? extends Car> c) {
		super (c);
	}
	@XmlElement(name="car")
	public List<Car> getCars(){
		return this;
	}
    public void setCars(List<Car> cars) {
    	this.addAll(cars);
    }
}
