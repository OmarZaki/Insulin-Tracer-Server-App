package resources;

import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.util.JSONPObject;

import controllers.SqlFunctions;
import dataModel.Meal;
import dataModel.User;

/**
 * This class contains all User resources 
 * 
 * @author OMAR
 *
 */ 
@Path("/users")
public class UsersResources {

	private SqlFunctions sql;
	/**
	 * 
	 * @param user
	 * @return
	 */
	@POST
	@PermitAll
	@Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String regisrationRequest(String user){
		System.out.println("Registration is here ! ");
		System.out.println(user); 
		User userObj = User.convertToObject(user); 
		sql = new SqlFunctions();
		User registeredUser= sql.registratNewUser(userObj);
		return User.convertToString(registeredUser) ;
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
			if(foundUser.getPassword().equals(user.getPassword()))
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
	
	@POST
	@PermitAll
	@Path("/meal")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean submitMeal(Meal meal){
		SqlFunctions sql = new SqlFunctions();
		if(meal.validate()){
			if(sql.submitMeal(meal))
				return true;
		}
		return false; 		
	}
	// user insulin dose submit request 
		
	
	
	
}
