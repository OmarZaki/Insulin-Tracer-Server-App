package DataModel;

import java.sql.Connection;
import java.sql.*;
/**
 * Manage the connection with MySql Database 
 * 
 * @author OMAR
 *
 */
public class DatabaseConnection {
	
	// JDBC driver name and database URL
	static final String JDBC_DRIVER="com.mysql.jdbc.Driver"; 
	static final String DB_URI="jdbc:mysql://localhost:3306/DiabetesDB"; 
	//Database credentials
	static final String USER_NAME= "root"; 
	static final String USER_PASSWORD="root"; 
	
	/**
	 * Database connection object. 
	 */
	Connection conn ; 
	/**
	 * Constructor 
	 */
	public DatabaseConnection(){
		this.conn=null; 
	}
	
	/**
	 * Open connection object to database . 
	 * @return true: connection is open successfully, false: problem related to connection. 
	 */
	public Boolean Open() {
		try {
			// Register JDBC driver
			Class.forName(JDBC_DRIVER);
			// Open a connection
			this.conn = DriverManager.getConnection(DB_URI, USER_NAME, USER_PASSWORD);
			if (this.conn != null) {
				return true;
			} else {
				return false;
			}
		} catch (Exception ex) {
			System.out.println("Error in Opening the connection to database \n");
			System.out.println(ex.getMessage());
			return false;
		}
	}
	
	/**
	 * close the connection database object. 
	 * @return true : success , false: fails. 
	 */
	public Boolean Close() {
		try {
			if (this.conn != null) {
				System.out.println("Closing the java conneciton .. \n");
				conn.close();
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			System.out.println("Error in Closing the connecion to database");
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	
	
	
}
