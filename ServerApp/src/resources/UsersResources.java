package resources;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes; 
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
 
import com.google.gson.JsonObject;

import controllers.SqlFunctions;
import dataModel.Categories;
import dataModel.InsulinDose;
import dataModel.Meal;
import dataModel.Messages;
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
	@POST
	@PermitAll
	@Path("/IsEmailExist")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String CheckIfEmailIsExist(String user){
		System.out.println("Check if the email is exist! ");
//		System.out.println(user); 
		User userObj = User.convertToObject(user); 
		sql = new SqlFunctions();
		Boolean IsExsist= sql.IsUserExsist(userObj);
		// set JSON as String object.
		JsonObject jsonObject = new JsonObject(); 
		jsonObject.addProperty("result", IsExsist);
		System.out.println(jsonObject.toString());
		return jsonObject.toString();
		
	}
	
	// user registration request
	// user logging in request 
	
	@POST
	@PermitAll
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String login(String user){
		System.out.println(user);
		SqlFunctions sql = new SqlFunctions();
		User foundUser = sql.findUserByEmail(User.convertToObject(user));
		if(foundUser!=null){
			return User.convertToString(foundUser); 
		}
		JsonObject jsonObject= new JsonObject();
		jsonObject.addProperty("result", false);
		return jsonObject.toString(); 		
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
		if(sql.insertMeal(meal))
			return true;
		return false; 		
	}
	// user insulin dose submit request 
	@POST
	@PermitAll
	@Path("/insulin")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean submitInsulin(InsulinDose insulinDose){
		SqlFunctions sql = new SqlFunctions();
		if(sql.submitInsulinDose(insulinDose)){
			return true;
		}
		return false;
	}
	
	//user categories request
		@POST
		@PermitAll
		@Path("/categories")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public boolean submitCategories(Categories categories){
			SqlFunctions sql = new SqlFunctions();
			if(sql.insertCategories(categories)){
				return true;
			}
			return false;
		}
	
	// user messages request 
	@POST
	@PermitAll
	@Path("/messages")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean submitMessages(Messages messages){
		SqlFunctions sql = new SqlFunctions();
		if(sql.insertMessages(messages))
			return true;
		return false; 		
	} 
		
	//-------> InsulinDoses Resources <-------------//
	/**
	 * 
	 * @param user
	 * @return
	 */
	@POST
	@PermitAll
	@Path("/allDoses")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllInsulinDosesForUser(String user){
		String listString ="";
		User userObj = User.convertToObject(user); 
		SqlFunctions sql = new SqlFunctions();
		List<InsulinDose> insulinDose = sql.getAllInslinDoses(userObj);
		if(insulinDose !=null){
			listString=InsulinDose.convertListToJson(insulinDose);
			}
		return listString; 		
	}  
	
	
	@POST
	@PermitAll
	@Path("/setTakenTrue")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String setTakenTrue(String dose){
		JsonObject jsonObject= new JsonObject(); // returned object ; 
		InsulinDose doseObj = InsulinDose.convertStringToObject(dose); 
		SqlFunctions sql = new SqlFunctions();
		boolean taken = sql.UpdateInsulinDoseTakenFlag(doseObj);
		if(taken !=false){	
			jsonObject.addProperty("result", true);
		}
		return jsonObject.toString(); 		
	}  
	
	
}
