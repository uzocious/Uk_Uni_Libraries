package app.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Database {

	// Database variables
	public Connection connection;		
	public Statement statement;
	public ResultSet resultSet;
	
	public static int STUDENT_ID = 0;
	public static String STUDENT_NAME = null;
	public static String UNIVERSITY = null;
	public static int BOOK_ID = 0;
	public static String SEARCH_TEXT = null;
	
	// Database connection details
	private final String DRIVER = "com.mysql.jdbc.Driver"; // JDBC driver
	private final String DATABASE = "uk_uni_libraries"; // the database name
	private final String HOST = "localhost"; // the database host
	
	// Disable SSL and suppress the SSL errors
	private final String DisableSSL = "?autoReconnect=true&useSSL=false";
	
	// Database full URL
	private final String DATABASE_URL = "jdbc:mysql://" + HOST + "/" + DATABASE + DisableSSL;
	
	// Database username and password
	private final String USERNAME = "root"; // the database login username
	private final String PASSWORD = ""; // the database login password 

	
	// Constructor
	public Database(){}
	
	
	// Database Connection
	public void DBConnection()
	{
		try{
			
			// Loads driver
			Class.forName(DRIVER);
			
			// Connects to Database
			connection = DriverManager.getConnection(DATABASE_URL,USERNAME,PASSWORD);
			
			// Creates SQL statement
			statement = connection.createStatement();
								
		}
		catch(Exception e)
		{
			System.out.println("Connection Denied!");
		}
	}
	
	public static void main(String[] args) {
		
		new Database().DBConnection();
	}

}
