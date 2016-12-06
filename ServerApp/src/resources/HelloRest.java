package resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class HelloRest {
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayHtmlHello() {
		return "Hello from Jersey";
	}

}
