package controllers;
import java.sql.*;

import dataModel.Meal;
import dataModel.User;
/**
 * This that contains 
 * @author OMAR
 *
 */
public class SqlFunctions {
	DatabaseConnection DBConn; 
	
	public SqlFunctions(){
		this.DBConn = new DatabaseConnection();
		
	}
	
	/**
	 * User SQL Registration Query to the database. 
	 * 
	 * @param user
	 * @return returned null User if get it fails to execute the query. 
	 * 
	 */
	public User registratNewUser(User user){
		User registredUser = null;
			if(user.validate()){
			String SQL_Statment = "INSERT INTO " + User._USER_TABLE+"(" 
					+ User._FIRST_NAME + ","
					+ User._LAST_NAME + "," 
					+ User._EMAIL+ "," 
					+ User._PASSWORD+ ","
					+ User._PHONE_NUMBER + "," 
					+ User._TYPE+","
					+ User._ADMIN+","
					+ User._CREATION_DATE +","
					+ User._TOKEN+","
					+ User._ADDRESS
					+ ") VALUES(?,?,?,?,?,?,?,?,?,?) ";
			try {
				if (this.DBConn.Open()) { 
					// ** create statement
					PreparedStatement preparedStatement = this.DBConn.conn.prepareStatement(SQL_Statment);
					preparedStatement.setString(1, user.getFirstName());
					preparedStatement.setString(2, user.getLastName());
					preparedStatement.setString(3, user.getEmail());
					preparedStatement.setString(4, user.getPassword());
					preparedStatement.setString(5, user.getPhoneNumber());
					preparedStatement.setBoolean(6, user.getType()); 
					preparedStatement.setBoolean(7, user.getAdmin());
					preparedStatement.setDate(8, user.getCreationDate());
					preparedStatement.setString(9, user.getToken());
					preparedStatement.setString(10, user.getAddress());
					int result = preparedStatement.executeUpdate();
					
					// ** close the connection
					DBConn.Close();
					if (result == 1) {
						registredUser = new User();
						registredUser.setFirstName(user.getFirstName());
						registredUser.setLastName(user.getLastName());
						registredUser.setEmail(user.getEmail());
						registredUser.setPassword(user.getPassword()); // should be hased before
					}
				}
			} catch (SQLException SQL_ex) {
				System.out.println("Error Inserting new User .. ");
				System.out.println(SQL_ex.getMessage());
			}
		} else {
			// 
			System.out.println("DATA_VALIATION_FORMAT_ERROR");
		}
		return registredUser;
	}

	
	/**
	 * Find a user by E-Mail
	 * @param email
	 * @return
	 */
	public User findUserByEmail(String email){
		User user = new User();
		if(user.validateEmailFormat(email)){
			String sqlStatement = "SELECT * FROM "+User._USER_TABLE+" WHERE ("
					+User._EMAIL+"= ?)";
			try{
				if(this.DBConn.Open()){
					PreparedStatement prepStatement = this.DBConn.conn.prepareStatement(sqlStatement);
					prepStatement.setString(1, email);
					ResultSet result = prepStatement.executeQuery();
					result.next();
					System.out.println(result.getString(5));
					if(result.getString(5).equals(email)){
						user.setEmail(email);
						return user;
					}
					return null;
				}
			} catch(SQLException e){
				System.out.println("Error finding user.");
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/***
	 * Submit meal to the Database
	 * @param meal
	 * @return
	 */
	public Boolean submitMeal(Meal meal){
		Meal newMeal = null;
			if(meal.validate()){
			String SQL_Statment = "INSERT INTO " + Meal._Meal_TABLE+"(" 
					+ Meal._TYPE + ","
					+ Meal._DESCRIPTION + "," 
					+ Meal._IMAGE+ "," 
					+ Meal._USERS_ID+ ","
					+ ") VALUES(?,?,?,?) ";
			try {
				if (this.DBConn.Open()) { 
					// ** create statement
					PreparedStatement preparedStatement = this.DBConn.conn.prepareStatement(SQL_Statment);
					preparedStatement.setString(1, meal.getType());
					preparedStatement.setString(2, meal.getDescription());
					preparedStatement.setString(3, meal.getImage());
					preparedStatement.setInt(4, meal.getUsers_id());
					int result = preparedStatement.executeUpdate();
					
					// ** close the connection
					DBConn.Close();
					if (result == 1) {
						return true;
					}
				}
			} catch (SQLException SQL_ex) {
				System.out.println("[SqlFunctions.submitMeal] Error Inserting new Meal");
				System.out.println(SQL_ex.getMessage());
			}
		} else {
			// 
			System.out.println("DATA_VALIATION_FORMAT_ERROR");
		}
		return false;
	}

}
