package resources;

import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import DataModel.SqlFunctions;
import DataModel.User;

/**
 * This class contains all User resources 
 * 
 * @author OMAR
 *
 */
@Path("/users")
public class UsersResources {

	private SqlFunctions sql;
	
	@POST
	@PermitAll
	@Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public User regisrationRequest(User user){
		User result = new User();
		sql = new SqlFunctions();
		result= sql.registratNewUser(user);
		if (result!= null) {
			return result; 
		}
		return result; 
	}
	
	// user registration request
	// user logging in request 
	
	@POST
	@PermitAll
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Boolean login(User user){
		SqlFunctions sql = new SqlFunctions();
		User foundUser = sql.findUserByEmail(user.getEmail());
		if(foundUser!=null){
			if(foundUser.getPassword()==user.getPassword())
				return true;
		}
		return false; 		
	}
	
	/***
	 * This method is meant to test the Login functionality,
	 * which is why it is currently commented.
	 * @return
	 */
	/*
	@GET
	@PermitAll
	@Path("/loginTest")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String loginTest(){
		SqlFunctions sql = new SqlFunctions();
		if(sql.findUserByEmail("this@email.com")!=null)
			return "Found it";
		return "Nothing";
	}
	*/
	
	// user meal submit request
	// user insulin dose submit request 
	// user blood sugar request 
	// user mediation request 
	// user Data synchronize data
	
	
	
}
