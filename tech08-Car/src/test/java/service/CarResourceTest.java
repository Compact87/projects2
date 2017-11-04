package service;

import static org.junit.Assert.*;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.junit.AfterClass;
import org.junit.Ignore;
import org.junit.Test;

public class CarResourceTest {
	
	
	private static URI uri = UriBuilder.fromUri("http://localhost/tech08-Car-0.0.1-SNAPSHOT/services/cars").port(8080).build();
	  private static Client client = ClientBuilder.newClient();

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
   @Ignore
	@Test
	public void testSetCar() {
		fail("Not yet implemented");
	}

	@Test
	public void shouldNotFindCar() {
		Response response=client.target(uri).path("1000").request().get();
		assertEquals(Response.Status.NOT_FOUND, response.getStatusInfo());
		
	}

}
