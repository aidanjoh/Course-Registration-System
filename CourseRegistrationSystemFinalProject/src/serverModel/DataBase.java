package serverModel;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;

public class DataBase implements Credentials
{
	//Attributes
	//Object of type connection from the JDBC class that deals with connecting to the database
	private Connection conn;
	
	//Object of type statement from the JDBC class that enables the creation Query statements
	private Statement stmt;
	
	//Object of type ResultSet from the JDBC class that stores the result of the query
	private ResultSet rs;

	public void initializeConnection() 
	{
		try 
		{
			// Register JDBC driver
			Driver driver = new com.mysql.cj.jdbc.Driver();
			DriverManager.registerDriver(driver);
			
			// Open a connection
			conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}

	}

	public void close()
	{
		try
		{
			//rs.close();
			conn.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public void insertUser()
	{
		try
		{
			stmt = conn.createStatement();
			String insert = "INSERT INTO STUDENT (ID, first, last) values (12, 'Joe','Jones')";
			int rowCount = stmt.executeUpdate(insert);
			System.out.println("row Count = " + rowCount);
			stmt.close();
			
		} 
		catch (SQLException e) 
		{
			System.out.println("problem inserting user");
			e.printStackTrace();
		}
	}

	public void insertUserPreparedStatement(int id, String fName, String lName)
	{
		try
		{
			String query = "INSERT INTO STUDENT (ID, first, last) values(?,?,?)";
			PreparedStatement pStat = conn.prepareStatement(query);
			pStat.setInt(1, id);
			pStat.setString(2, fName);
			pStat.setString(3, lName);
			int rowCount = pStat.executeUpdate();
			System.out.println("row Count = " + rowCount);
			pStat.close();
		}
		catch (SQLException e)
		{
			System.out.println("problem inserting user");
			e.printStackTrace();
		}
	}

	public void createTable() 
	{
		String sql = "CREATE TABLE STUDENT " + "(id INTEGER not NULL, " + " first VARCHAR(255), "
				+ " last VARCHAR(255), " + " PRIMARY KEY ( id ))";

		try
		{
			stmt = conn.createStatement(); // construct a statement
			stmt.executeUpdate(sql); // execute my query (i.e. sql)
			stmt.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.out.println("Table can NOT be created!");
		}
		System.out.println("Created table in given database...");
	}
	
	public void createStudentTable()
	{
		String sql = "CREATE TABLE STUDENT " + "(id INTEGER not NULL, " + " first VARCHAR(255), "
				+ " last VARCHAR(255), " + " PRIMARY KEY ( id ))";
		try
		{
			stmt = conn.createStatement(); // construct a statement
			stmt.executeUpdate(sql); // execute my query (i.e. sql)
			stmt.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.out.println("Table can NOT be created!");
		}
		System.out.println("Created table in given database...");
	}

	public static void main(String[] args0)
	{
		DataBase myApp = new DataBase();
		myApp.initializeConnection();
		//myApp.createTable();  //only call this function once then comment out.
		//myApp.insertUser();
		//myApp.insertUserPreparedStatement(5, "Sam", "Smith");
		//myApp.close();
	}
}