package service;

import java.net.URI;

import javax.ws.rs.HEAD;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import model.Car;

@Path("/shop")
public class ShopResource {
	@HEAD
	public Response head(@Context UriInfo uriInfo) {
		
		UriBuilder absolute=uriInfo.getBaseUriBuilder();
		URI carUrl=absolute.clone().path(CarResource.class).build();
		Response.ResponseBuilder builder=Response.ok();
		
		Link cars=Link.fromUri(carUrl).rel("cars").type("application/json").build();
		builder.links(cars);builder.header("shop", "resources");
		
		return builder.build();
	}

}
