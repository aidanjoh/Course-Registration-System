package serverModel;

/**
 * 
 * @author Aidan Johnson and Michele Piperni
 * @version 1.0
 * @since April 15, 2020
 *
 */
public interface Credentials 
{
	   //JDBC driver name and database URL
	   static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost/mydb";

	   //Database credentials
	   static final String USERNAME = "root";
	   static final String PASSWORD = "Electronics44";
}