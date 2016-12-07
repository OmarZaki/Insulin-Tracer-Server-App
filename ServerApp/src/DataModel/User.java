package DataModel;

import java.sql.Date;

public class Users {
	// User Columns names 
	public final static String _ID = "id"; 
	public final static String _FIRST_NAME="fname";
	public final static String _LAST_NAME="fname";
	public final static String _EMAIL="email";
	public final static String _PASSWORD="password";
	public final static String _PHONE_NUMBER="phone_number";
	public final static String _TOKEN="token";
	public final static String _ADMIN="admin"; 
	public final static String _TYPE="type";
	public final static String _CREATION_DATE="creation_date"; 
	
	private int	id ;
	private String firstName; 
	private String lastName;
	private String phoneNumber; 
	private String email; 
	private String password; 
	private Boolean admin; 
	private Boolean type; 
	private String token; 
	private Date creationDate; 
	
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
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
