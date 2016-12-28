package controllers;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import dataModel.Categories;
import dataModel.InsulinDose;
import dataModel.Meal;
import dataModel.Messages;
import dataModel.User;

/**
 * This that contains
 * 
 * @author OMAR
 *
 */
public class SqlFunctions {
	DatabaseConnection DBConn;

	public SqlFunctions() {
		this.DBConn = new DatabaseConnection();

	}

	/**
	 * User SQL Registration Query to the database.
	 * 
	 * @param user
	 * @return returned null User if get it fails to execute the query.
	 * 
	 */
	public User registratNewUser(User user) {

		if (user.validate()) {
			String SQL_Statment = "INSERT INTO " + User._USER_TABLE + "(" + User._FIRST_NAME + "," + User._LAST_NAME
					+ "," + User._EMAIL + "," + User._PASSWORD + "," + User._PHONE_NUMBER + "," + User._TYPE + ","
					+ User._ADMIN + "," + User._CREATION_DATE + "," + User._TOKEN + "," + User._ADDRESS + ","
					+ User._BIRTH_DATE + ") VALUES(?,?,?,?,?,?,?,?,?,?,?) ";
			try {
				if (this.DBConn.Open()) {
					// ** create statement
					PreparedStatement preparedStatement = this.DBConn.conn.prepareStatement(SQL_Statment,Statement.RETURN_GENERATED_KEYS);
					preparedStatement.setString(1, user.getFirstName());
					preparedStatement.setString(2, user.getLastName());
					preparedStatement.setString(3, user.getEmail());
					preparedStatement.setString(4, user.getPassword());
					preparedStatement.setString(5, user.getPhoneNumber());
					preparedStatement.setBoolean(6, user.getType());
					preparedStatement.setBoolean(7, user.getAdmin());
					preparedStatement.setDate(8, new java.sql.Date(user.getCreationDate().getTime()));
					preparedStatement.setString(9, user.getToken());
					preparedStatement.setString(10, user.getAddress());
					preparedStatement.setDate(11, new java.sql.Date(user.getBirthDate().getTime()));
					int result = preparedStatement.executeUpdate();

					// ** close the connection
					DBConn.Close();
					if (result != 0) {
						ResultSet rs = preparedStatement.getGeneratedKeys();
						rs.next();
						user.setId(rs.getInt(1));

					} else {
						user = null;
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
		return user;
	}

	/**
	 * Find a user by E-Mail
	 * 
	 * @param email
	 * @return
	 */
	public User findUserByEmail(User user) {

		if (user.validateEmailFormat(user.getEmail())) {
			String sqlStatement = "SELECT * FROM " + User._USER_TABLE + " WHERE (" + User._EMAIL + "= ? " + " AND "
					+ User._PASSWORD + "=?" + ")";
			try {
				if (this.DBConn.Open()) {
					PreparedStatement prepStatement = this.DBConn.conn.prepareStatement(sqlStatement);
					prepStatement.setString(1, user.getEmail());
					prepStatement.setString(2, user.getPassword());
					ResultSet result = prepStatement.executeQuery();
					if (result.next()) {
						User foundUser = new User();
						foundUser.setId(result.getInt(User._ID));
						foundUser.setFirstName(result.getString(User._FIRST_NAME));
						foundUser.setLastName(result.getString(User._LAST_NAME));
						foundUser.setEmail(result.getString(User._EMAIL));
						foundUser.setPassword(result.getString(User._PASSWORD));
						foundUser.setPhoneNumber(result.getString(User._PHONE_NUMBER));
						foundUser.setAddress(result.getString(User._ADDRESS));
						foundUser.setBirthDate(new Date(result.getDate(User._BIRTH_DATE).getTime()));
						foundUser.setToken(result.getString(User._TOKEN));
						foundUser.setCreationDate(new Date(result.getDate(User._CREATION_DATE).getTime()));
						foundUser.setAdmin(result.getBoolean(User._ADMIN));
						return foundUser;

					}
					this.DBConn.Close();

					return null;
				}
			} catch (SQLException e) {
				System.out.println("Error finding user.");
				e.printStackTrace();
			}
		}
		return null;
	}

	/***
	 * Insert meal into the Database
	 * 
	 * @param meal
	 * @return
	 */
	public Boolean insertMeal(Meal meal) {
		if (meal.validate()) {
			String SQL_Statment = "INSERT INTO " + Meal._Meal_TABLE + " (" + Meal._TYPE + "," + Meal._DESCRIPTION + ","
					+ Meal._IMAGE + ", " + Meal._USERS_ID + ", " + Meal._DATE_TIME + ") VALUES(?,?,?,?,?)";
			try {
				if (this.DBConn.Open()) {
					// ** create statement
					PreparedStatement preparedStatement = this.DBConn.conn.prepareStatement(SQL_Statment);
					preparedStatement.setString(1, meal.getType());
					preparedStatement.setString(2, meal.getDescription());
					preparedStatement.setString(3, meal.getImage());
					preparedStatement.setInt(4, meal.getUsers_id());
					preparedStatement.setTimestamp(5, new Timestamp(meal.getDate_time().getTime()));
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

	/***
	 * Mark InsulinDose as taken
	 * 
	 * @param insulinDose
	 * @return
	 */
	public Boolean submitInsulinDose(InsulinDose insulinDose) {
		if (insulinDose.validate()) {
			String SQL_Statment = "UPDATE " + InsulinDose._InsulinDose_TABLE + " SET " + InsulinDose._TAKEN + " = ?"
					+ " WHERE " + InsulinDose._ID + " = ?";
			try {
				if (this.DBConn.Open()) {
					// ** create statement
					PreparedStatement preparedStatement = this.DBConn.conn.prepareStatement(SQL_Statment);
					preparedStatement.setBoolean(1, true);
					preparedStatement.setInt(2, insulinDose.getId());
					int result = preparedStatement.executeUpdate();

					// ** close the connection
					DBConn.Close();
					if (result == 1) {
						return true;
					}
				}
			} catch (SQLException SQL_ex) {
				System.out.println("[SqlFunctions.submitInsulinDose] Error updating InsulinDose");
				System.out.println(SQL_ex.getMessage());
			}
		} else {
			//
			System.out.println("DATA_VALIATION_FORMAT_ERROR");
		}
		return false;
	}

	/***
	 * Insert Categories entry into the Database
	 * 
	 * @param meal
	 * @return
	 */
	public Boolean insertCategories(Categories categories) {
		if (categories.validate()) {
			String SQL_Statment = "INSERT INTO " + Categories._Categories_TABLE + " (" + Categories._VALUE + ","
					+ Categories._DATE_TIME + "," + Categories._USERS_ID + "," + Categories._CATEGORY_NAME_ID
					+ ") VALUES(?,?,?,?) ";
			try {
				if (this.DBConn.Open()) {
					// ** create statement
					PreparedStatement preparedStatement = this.DBConn.conn.prepareStatement(SQL_Statment);
					preparedStatement.setString(1, categories.getValue());
					preparedStatement.setTimestamp(2, new Timestamp(categories.getDate_time().getTime()));
					preparedStatement.setInt(3, categories.getUsers_id());
					preparedStatement.setInt(4, categories.getCategory_name_id());
					int result = preparedStatement.executeUpdate();

					// ** close the connection
					DBConn.Close();
					if (result == 1) {
						return true;
					}
				}
			} catch (SQLException SQL_ex) {
				System.out.println("[SqlFunctions.submitCategories] Error Inserting new Reminder");
				System.out.println(SQL_ex.getMessage());
			}
		} else {
			//
			System.out.println("DATA_VALIATION_FORMAT_ERROR");
		}
		return false;
	}

	/***
	 * Insert Categories entry into the Database
	 * 
	 * @param meal
	 * @return
	 */
	public Messages insertMessages(Messages messages) {
		Messages insertedMessage = null;
		if (messages.validate()) {
			String SQL_Statment = "INSERT INTO " + Messages._Messages_TABLE + " (" + Messages._TEXT + ","
					+ Messages._DATE_TIME + "," + Messages._USERS_ID  + ") VALUES(?,?,?) ";
			try {
				if (this.DBConn.Open()) {
					// ** create statement
					PreparedStatement preparedStatement = this.DBConn.conn.prepareStatement(SQL_Statment,Statement.RETURN_GENERATED_KEYS);
					preparedStatement.setString(1, messages.getText());
					preparedStatement.setTimestamp(2, new java.sql.Timestamp(messages.getDate_time().getTime()));
					preparedStatement.setInt(3, messages.getUsers_id());
					int result = preparedStatement.executeUpdate();
					if (result != 0) {
						// set and Message object to return ;
						ResultSet rs = preparedStatement.getGeneratedKeys();
						rs.next();
						
						insertedMessage = new Messages();
						insertedMessage.setId(rs.getInt(1));
						insertedMessage.setText(messages.getText());
						insertedMessage.setUsers_id(messages.getUsers_id());
						insertedMessage.setDate_time(messages.getDate_time());
						System.out.println("Inserting messages into database ! ");
					}

					// ** close the connection
					DBConn.Close();

				}
			} catch (SQLException SQL_ex) {
				System.out.println("[SqlFunctions.InsertMessage] Error Inserting new Message");
				System.out.println(SQL_ex.getMessage());
			}
		} else {
			//
			System.out.println("DATA_VALIATION_FORMAT_ERROR");
		}
		return insertedMessage;
		
	}

	public Boolean IsUserExsist(User userObj) {
		if (userObj.validate()) {
			String SQL_Statement = "SELECT * FROM " + User._USER_TABLE + " WHERE " + User._EMAIL + "= ?";
			try {
				if (this.DBConn.Open()) {
					// ** create statement
					PreparedStatement preparedStatement = this.DBConn.conn.prepareStatement(SQL_Statement);
					preparedStatement.setString(1, userObj.getEmail());
					ResultSet result = preparedStatement.executeQuery();
					if (result.next()) {
						return true;
					} else {
						return false;
					}
					
				}
				this.DBConn.Close();
			} catch (SQLException SQL_ex) {
				System.out.println("[SqlFunctions.submitMeal] Error Inserting new Reminder");
				System.out.println(SQL_ex.getMessage());
			}
		} else {
			System.out.println("DATA_VALIATION_FORMAT_ERROR");
		}

		return null;
	}

	/**
	 * get all insulin dose for a specific user.
	 * 
	 * @param user
	 * @return
	 */
	public List<InsulinDose> getAllInslinDoses(User user) {
		// TODO 1. Find User by its email, Use User's ID to retrieve user's dose
		// records
		List<InsulinDose> allDoses = null;
		User originalUser = findUserByEmail(user);
		if (originalUser != null) {
			String sqlStatementGetAllUserDoses = "SELECT * FROM " + InsulinDose._InsulinDose_TABLE + " WHERE "
					+ InsulinDose._USERS_ID + "=?";
			try {
				if (this.DBConn.Open()) {
					PreparedStatement preparedStatement = this.DBConn.conn
							.prepareStatement(sqlStatementGetAllUserDoses);
					preparedStatement.setInt(1, originalUser.getId());
					ResultSet result = preparedStatement.executeQuery();
					allDoses = new ArrayList<InsulinDose>();
					while (result.next()) {
						InsulinDose dose = new InsulinDose();
						dose.setId(result.getInt(InsulinDose._ID));
						dose.setQuantity(result.getInt(InsulinDose._QUANTITY));
						dose.setDate_time(result.getTimestamp(InsulinDose._DATE_TIME));
						dose.setTaken(result.getBoolean(InsulinDose._TAKEN));
						dose.setUsers_id(result.getInt(InsulinDose._USERS_ID));
						allDoses.add(dose);
					}
					this.DBConn.Close();
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}

		return allDoses;
	}

	/**
	 * change the Taken value of specific dose ;
	 * 
	 * @param doseObj
	 * @return
	 */
	public boolean UpdateInsulinDoseTakenFlag(InsulinDose doseObj) {

		boolean taken = false;
		String sqlStatement = "UPDATE " + InsulinDose._InsulinDose_TABLE + " SET " + InsulinDose._TAKEN + "=?"
				+ " WHERE " + InsulinDose._ID + "=?";
		try {
			if (this.DBConn.Open()) {
				PreparedStatement preparedStatement = this.DBConn.conn.prepareStatement(sqlStatement);
				preparedStatement.setBoolean(1, doseObj.getTaken());
				preparedStatement.setInt(2, doseObj.getId());
				long row = preparedStatement.executeUpdate();
				if (row != 0) {
					taken = true;
				}
				this.DBConn.Close();
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		
		return taken;
	}
	
	/**
	 * get all meals for a specific user. 
	 * @param user
	 * @return
	 */
	public List<Meal> getAllMeals(User user) {
		// TODO 1. Find User by its email, Use User's ID to retrieve user's dose records 
		List<Meal> allMeals = null;
		User originalUser=findUserByEmail(user); 
		if(originalUser!= null){
			String sqlStatementGetAllUserMeals = "SELECT * FROM "+ Meal._Meal_TABLE + " WHERE "+ InsulinDose._USERS_ID +"=?";
			try{
				if(this.DBConn.Open()){
					PreparedStatement preparedStatement = this.DBConn.conn.prepareStatement(sqlStatementGetAllUserMeals); 
					preparedStatement.setInt(1, originalUser.getId());
					ResultSet result = preparedStatement.executeQuery(); 
					allMeals = new ArrayList<Meal>();
					while(result.next()){
						Meal meal= new Meal();
						meal.setId(result.getInt(Meal._ID));
						meal.setType(result.getString(Meal._TYPE));
						meal.setDescription(result.getString(Meal._DESCRIPTION));
						meal.setDate_time(result.getTimestamp(Meal._DATE_TIME));
						meal.setImage(result.getString(Meal._IMAGE));
						meal.setUsers_id(result.getInt(Meal._USERS_ID));
						allMeals.add(meal);
					}
					this.DBConn.Close();
				}
			}catch(SQLException ex) {
					System.out.println(ex.getMessage());
			}
		}
		
		return allMeals;
	}
	
	public List<Categories> getAllCategories(User user) {
		// TODO 1. Find User by its email, Use User's ID to retrieve user's dose records 
		List<Categories> allCategories = null;
		User originalUser=findUserByEmail(user); 
		if(originalUser!= null){
			String sqlStatementGetAllUserCategories = "SELECT * FROM "+ Categories._Categories_TABLE + " WHERE "+ InsulinDose._USERS_ID +"=?";
			try{
				if(this.DBConn.Open()){
					PreparedStatement preparedStatement = this.DBConn.conn.prepareStatement(sqlStatementGetAllUserCategories); 
					preparedStatement.setInt(1, originalUser.getId());
					ResultSet result = preparedStatement.executeQuery(); 
					allCategories = new ArrayList<Categories>();
					while(result.next()){
						Categories categories= new Categories();
						categories.setId(result.getInt(Categories._ID));
						categories.setCategory_name_id(result.getInt(Categories._CATEGORY_NAME_ID));
						categories.setValue(result.getString(Categories._VALUE));
						categories.setDate_time(result.getTimestamp(Categories._DATE_TIME));
						categories.setUsers_id(result.getInt(Categories._USERS_ID));
						allCategories.add(categories);
					}
					this.DBConn.Close();
				}
			}catch(SQLException ex) {
					System.out.println(ex.getMessage());
			}
		}
		
		return allCategories;
	}
	
	
	public List<Messages> getAllMessages(User user) {
		// TODO 1. Find User by its email, Use User's ID to retrieve user's dose records 
		List<Messages> allMessages = null;
		User originalUser=findUserByEmail(user); 
		if(originalUser!= null){
			String sqlStatementGetAllUserMessages = "SELECT * FROM "+ Messages._Messages_TABLE + " WHERE "+ Messages._USERS_ID +"=?";
			try{
				if(this.DBConn.Open()){
					PreparedStatement preparedStatement = this.DBConn.conn.prepareStatement(sqlStatementGetAllUserMessages); 
					preparedStatement.setInt(1, originalUser.getId());
					ResultSet result = preparedStatement.executeQuery(); 
					allMessages = new ArrayList<Messages>();
					while(result.next()){
						Messages messages= new Messages();
						messages.setId(result.getInt(Messages._ID));
						messages.setText(result.getString(Messages._TEXT));
						messages.setDate_time(result.getDate(Messages._DATE_TIME));
						messages.setUsers_id(result.getInt(Messages._USERS_ID));
						allMessages.add(messages);
					}
					this.DBConn.Close();
				}
			}catch(SQLException ex) {
					System.out.println(ex.getMessage());
			}
		}
		
		return allMessages;
	}

}
