package client;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import model.Car;
import model.Cars;

public class ClientTest {

	public static void main(String[] args) throws JAXBException{
		Car car=new Car("bmw","320d","blue","2003");
		StringWriter writer=new StringWriter();
		JAXBContext context=JAXBContext.newInstance(Car.class);
		Marshaller m=context.createMarshaller();
		m.marshal(car, writer);
		System.out.println(writer.toString());
		
		Cars cars=new Cars();
		cars.add(new Car("mercedes","220","red","2002"));
		cars.add(new Car("ford","fiesta","white","2006"));
		
	    context=JAXBContext.newInstance(Cars.class);
		m=context.createMarshaller();
		m.marshal(cars, writer);
		System.out.println(writer.toString());
		
	}

}
