package service;

import static org.junit.Assert.*;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.StringWriter;
import java.net.InetSocketAddress;
import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.ext.RuntimeDelegate;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import service.Cars;
import model.Car;

import org.junit.Ignore;
import org.junit.Test;

import com.sun.jersey.api.container.httpserver.HttpServerFactory;

public class CarResourceTest {
	// ======================================
    // =             Attributes             =
    // ======================================
	private static final String XML = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><car><color>blue</color><make>bmw</make><model>320d</model><year>2003</year></car>";
    private static final String XML2=   "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><cars><car><color>red</color><make>mercedes</make><model>220</model><year>2002</year></car><car><color>white</color><make>ford</make><model>fiesta</model><year>2006</year></car></cars>"  ;                         
	 // ======================================
    // =                 Tests              =
    // ======================================
	@Test
	public void shouldMarshalCar()throws JAXBException {
		Car car=new Car("bmw","320d","blue","2003");
		StringWriter writer=new StringWriter();
		JAXBContext context=JAXBContext.newInstance(Car.class);
		Marshaller m=context.createMarshaller();
		m.marshal(car, writer);
		
		assertEquals(XML, writer.toString());
	}
	@Test
	public void shouldMarshalAllCars() throws JAXBException{
		Cars cars=new Cars();
		cars.add(new Car("mercedes","220","red","2002"));
		cars.add(new Car("ford","fiesta","white","2006"));
		StringWriter writer=new StringWriter();
		JAXBContext context=JAXBContext.newInstance(Cars.class);
		Marshaller m=context.createMarshaller();
		m.marshal(cars, writer);
		
		assertEquals(XML2, writer.toString());
	}
   @Test
   public void shouldCheckURIs() throws IOException{
	   
	   URI uri=UriBuilder.fromUri("http://localhost/").port(8282).build();
	   
	   HttpServer server= HttpServer.create(new InetSocketAddress(uri.getPort()), 0);
	   
	   HttpHandler handler = RuntimeDelegate.getInstance().createEndpoint(new ApplicationConfig(), HttpHandler.class);
	   
	   server.createContext(uri.getPath(),handler);
	   
	   server.start();
	   
	   Client client=ClientBuilder.newClient();
	   
	   assertEquals(200, client.target("http://localhost:8282/services/forge/cars/1").request().get().getStatus());
      
      

       // Invalid URIs
       assertEquals(404, client.target("http://localhost:8282/forge/cars/AGONCAL").request().get().getStatus());
       assertEquals(404, client.target("http://localhost:8282/forge/cars/1234").request().get().getStatus());

       // Stop HTTP server
       server.stop(0);
   }
}
