package dataModel;
 
import java.util.Date;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.xml.bind.annotation.XmlRootElement;
 
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


@XmlRootElement
public class User {

	public final static String _USER_TABLE = "Users";

	// User Columns names
	public final static String _ID = "id";
	public final static String _FIRST_NAME = "fname";
	public final static String _LAST_NAME = "lname";
	public final static String _EMAIL = "email";
	public final static String _PASSWORD = "password";
	public final static String _PHONE_NUMBER = "phone_number";
	public final static String _TOKEN = "token";
	public final static String _ADMIN = "admin";
	public final static String _TYPE = "type";
	public final static String _CREATION_DATE = "creation_date";
	public final static String _ADDRESS = "address";
	 public final static String _BIRTH_DATE= "birth_date";
	/**
	 * User's fields
	 */
	private int id;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String email;
	private String password;
	private Boolean admin;
	private Boolean type;
	private String token; 
	private Date creationDate;
	private String address; 
	private Date birthDate;

	public User() {
	}

	public Date getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(java.util.Date birthDate) {
//		this.birthDate = new java.sql.Date(birthDate.getTime());
		this.birthDate = birthDate; 
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	public Boolean getType() {
		return type;
	}

	public void setType(Boolean type) {
		this.type = type;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(java.util.Date creationDate) {
		this.creationDate = creationDate;
		;
	}

	/**
	 * OMAR: To validate the user fields, You can add more rule as its needed.
	 * 
	 * @return
	 */
	public Boolean validate() {
		Boolean result = false;
		if (this.firstName != "" && this.lastName != "" && validateEmailFormat(this.email) && this.password != "") {
			result = true;
		}
		return result;
	}

	/**
	 * To validate the email address i used JavaMail package . see more
	 * https://mvnrepository.com/artifact/javax.mail/mail/1.4 The pakacke in
	 * Javax.mail.jar
	 * 
	 * @param email
	 * @return
	 */
	public Boolean validateEmailFormat(String email) {
		Boolean result = true;
		try {

			InternetAddress emailAddress = new InternetAddress(email);
			emailAddress.validate();

		} catch (AddressException ex) {
			result = false;
		}

		return result;
	}
	/**
	 * Convert to User Json String to User Object.
	 * @param jsonObject
	 * @return
	 */
	public static User convertToObject(String jsonObject) {
		Gson gson = new  GsonBuilder()
	               .setDateFormat("yyyy-MM-dd HH:mm:ss.S")
	               .create();
		User user = gson.fromJson(jsonObject, User.class);
		return user;

	}
	
	/**
	 * Convert User object to String JSON format. 
	 * 
	 * @param user 
	 * @return String JSON format it return null if fails.
	 */
	public static String convertToString(User user){
		Gson gson = new GsonBuilder()
	               .setDateFormat("yyyy-MM-dd HH:mm:ss.S")
	               .create();
		String userStringObject= gson.toJson(user); 
		return userStringObject;
	}

}
