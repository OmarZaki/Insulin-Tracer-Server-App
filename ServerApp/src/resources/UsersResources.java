package resources;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes; 
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
 
import com.google.gson.JsonObject;
import com.sun.jersey.core.util.Base64;

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
			System.out.println(User.convertToString(foundUser));
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
	
	//Convert a Base64 string and create a file
    private byte[] convertFile(String file_string) throws IOException{
        byte[] bytes = Base64.decode(file_string);
        return bytes;
    }
	
	@POST
	@PermitAll
	@Path("/meal")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String submitMeal(String mealInput){
		//TODO: Authenticate
		Meal meal = Meal.convertToObject(mealInput);
		System.out.println("UId: "+meal.getUsers_id()+"\nTitle: "+meal.getType()+"\nDescription: "+meal.getDescription());
		SqlFunctions sql = new SqlFunctions();
		try{
			byte[] decoded = convertFile(meal.getImage());
			File f = new File("\\images\\"+meal.getUsers_id()+"_"+new SimpleDateFormat("yyyyMMdd_HHmmss").format(new java.util.Date())+".jpg");
			f.getParentFile().mkdirs();
			FileOutputStream fileOutputStream =
	                new FileOutputStream(f);
		    fileOutputStream.write(decoded);
		    fileOutputStream.flush();
		    fileOutputStream.close();
		    meal.setImage(f.getAbsolutePath());
			//TODO: Finish Decoding Image
			if(sql.insertMeal(meal)){
				JsonObject json = new JsonObject();
				json.addProperty("result", "success");
				return json.toString();
			}
		} catch (IOException e){
			e.printStackTrace();
		}
		JsonObject json = new JsonObject();
		return json.toString(); 		
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
		public String submitCategories(String categoriesInput){
			Categories categories = Categories.convertToObject(categoriesInput);
			System.out.println("UId: "+categories.getUsers_id()+"\nValue: "+categories.getValue()+"\nCategory Name Id: "+categories.getCategory_name_id());
			SqlFunctions sql = new SqlFunctions();
			if(sql.insertCategories(categories)){
				JsonObject json = new JsonObject();
				json.addProperty("result", "success");
				return json.toString();
			}
			JsonObject json = new JsonObject();
			return json.toString(); 
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
		System.out.println("All doses sync");
		User userObj = User.convertToObject(user); 
		SqlFunctions sql = new SqlFunctions();
		List<InsulinDose> insulinDose = sql.getAllInslinDoses(userObj);
		if(insulinDose !=null){
			listString=InsulinDose.convertListToJson(insulinDose);
			}
		else{
			JsonObject jsonObject= new JsonObject();
			jsonObject.addProperty("result", false);
			listString = jsonObject.toString();
		}
		System.out.println(listString);
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
	
	@POST
	@PermitAll
	@Path("/allMeals")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllMealsForUser(String user){
		String listString ="";
		System.out.println("All meals sync");
		User userObj = User.convertToObject(user); 
		SqlFunctions sql = new SqlFunctions();
		List<Meal> meals = sql.getAllMeals(userObj);
		if(meals !=null){
			listString=Meal.convertListToJson(meals);
			}
		else{
			JsonObject jsonObject= new JsonObject();
			jsonObject.addProperty("result", false);
			listString = jsonObject.toString();
		}
		System.out.println(listString);
		return listString; 		
	}
	
	@POST
	@PermitAll
	@Path("/allCategories")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllCategoriesForUser(String user){
		String listString ="";
		System.out.println("All meals sync");
		User userObj = User.convertToObject(user); 
		SqlFunctions sql = new SqlFunctions();
		List<Categories> categories = sql.getAllCategories(userObj);
		if(categories !=null){
			listString=Categories.convertListToJson(categories);
			}
		else{
			JsonObject jsonObject= new JsonObject();
			jsonObject.addProperty("result", false);
			listString = jsonObject.toString();
		}
		System.out.println(listString);
		return listString; 		
	}
	
}
