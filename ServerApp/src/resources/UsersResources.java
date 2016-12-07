package resources;

import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import DataModel.User;

/**
 * This class contains all User resources 
 * 
 * @author OMAR
 *
 */
@Path("/users")
public class UsersResources {
	@POST
	@PermitAll
	@Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public User registerNewUser(){
		// TODO get the object, 
		// TODO check the data 
		// TODO store the data 
		// TODO return the user object
		return new User();
	}
	
	// user registration request
	// user logging in request 
	
//	@PermitAll
//	@Path("/login")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public Boolean login(User user){
//		
//		// TODO get the object, 
//		// TODO check the data 
//		// TODO store the data 
//		// TODO return the user object
//		return new Boolean(false); 		
//	}
	
	// user meal submit request
	// user insulin dose submit request 
	// user blood sugar request 
	// user mediation request 
	// user Data synchronize data
	
	
	
}
