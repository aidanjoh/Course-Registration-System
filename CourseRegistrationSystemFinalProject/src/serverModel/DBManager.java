package serverModel;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * This class is simulating a database for our program
 * 
 * @author Aidan Johnson and Michele Piperni
 *
 */
public class DBManager implements Credentials
{
	
	private ArrayList<Course> courseList;
	private ArrayList<Student> studentList;
	
	/**
	 * The Connection object conn deals with the connection to the data base.
	 */
	private Connection conn;
	
	/**
	 * The Statement object stmt enables the creation of Query statements.
	 */
	private Statement stmt;
	
	/**
	 * The ResultSet object rs stores the results of the query.
	 */
	private ResultSet rs;

	/**
	 * 
	 */
	public DBManager() 
	{
		courseList = new ArrayList<Course>();
		studentList = new ArrayList<Student>();
		initializeConnection();
	}

	/**
	 * Initializes the connection for the driver and opens the connection.
	 */
	public void initializeConnection() 
	{
		try 
		{
			//Register JDBC driver
			Driver driver = new com.mysql.cj.jdbc.Driver();
			DriverManager.registerDriver(driver);
			
			//Open a connection
			conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Closes the connection and the resultSet object.
	 */
	public void close()
	{
		try
		{
			rs.close();
			conn.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public void insertStudentPreparedStatement(int id, String name, String password)
	{
		try
		{
			String query = "INSERT INTO STUDENT (ID, name, password) values(?,?,?)";
			PreparedStatement pStat = conn.prepareStatement(query);
			pStat.setInt(1, id);
			pStat.setString(2, name);
			pStat.setString(3, password);
			pStat.executeUpdate();
			pStat.close();
		}
		catch (SQLException e)
		{
			System.out.println("Problem inserting Student!");
			e.printStackTrace();
		}
	}
	
	public void insertAdminPreparedStatement(int id, String name, String password)
	{
		try
		{
			String query = "INSERT INTO ADMIN (ID, name, password) values(?,?,?)";
			PreparedStatement pStat = conn.prepareStatement(query);
			pStat.setInt(1, id);
			pStat.setString(2, name);
			pStat.setString(3, password);
			pStat.executeUpdate();
			pStat.close();
		}
		catch (SQLException e)
		{
			System.out.println("Problem inserting Admin!");
			e.printStackTrace();
		}
	}
	
	public void insertCoursePreparedStatement(int id, String name)
	{
		try
		{
			String query = "INSERT INTO COURSE (ID, courseName) values(?,?)";
			PreparedStatement pStat = conn.prepareStatement(query);
			pStat.setInt(1, id);
			pStat.setString(2, name);
			pStat.executeUpdate();
			pStat.close();
		}
		catch (SQLException e)
		{
			System.out.println("Problem inserting course!");
			e.printStackTrace();
		}
	}

	/**
	 * 
	 */
	public void createStudentTable()
	{
		String sql = "CREATE TABLE STUDENT " + "(id INTEGER not NULL, " + " name VARCHAR(255), " + " password VARCHAR(255), " + " PRIMARY KEY (id))";
		
		try
		{
			stmt = conn.createStatement(); // construct a statement
			stmt.executeUpdate(sql); // execute my query (i.e. sql)
			stmt.close();
			System.out.println("Created Student table in the data base!");
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.out.println("Student table can NOT be created!");
		}
	}
	
	/**
	 * 
	 */
	public void createCourseTable()
	{
		String sql = "CREATE TABLE COURSE " + "(id INTEGER not NULL, " + " courseName VARCHAR(255), " + " PRIMARY KEY (id))";
		
		try 
		{
			stmt = conn.createStatement(); // construct a statement
			stmt.executeUpdate(sql); // execute my query (i.e. sql)
			stmt.close();
			System.out.println("Created Course table in the database!");
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.out.println("Course table can NOT be created!");
		}
	}
	
	/**
	 * 
	 */
	public void createAdminTable()
	{
		String sql = "CREATE TABLE ADMIN " + "(id INTEGER not NULL, " + " name VARCHAR(255), " + " password VARCHAR(255), " + " PRIMARY KEY (id))";
		
		try
		{
			stmt = conn.createStatement(); // construct a statement
			stmt.executeUpdate(sql); // execute my query (i.e. sql)
			stmt.close();
			System.out.println("Created Student table in the data base!");
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.out.println("Student table can NOT be created!");
		}
	}
	
	public String validateStudentLogin(int id, String password)
	{
		try
		{
			stmt = conn.createStatement();
			String query = "SELECT * FROM STUDENT WHERE id = '" + id + "' and password ='" + password + "'";
			rs = stmt.executeQuery(query);
			if(rs.next()) 
			{
				System.out.println("Student logged in");
				return "true";
			}
			else
			{
				System.out.println("Invalid id or password");
				return "false";
			}
		}
		catch(SQLException error)
		{
			System.err.println("Error in validating login.");
		}
		return "false";
	}
	
	public String validateAdminLogin(int id, String password)
	{
		try
		{
			stmt = conn.createStatement();
			String query = "SELECT * FROM ADMIN WHERE id = '" + id + "' and password ='" + password + "'";
			rs = stmt.executeQuery(query);
			if(rs.next()) 
			{
				System.out.println("Admin logged in");
				return "true";
			}
			else
			{
				System.out.println("Invalid id or password");
				return "false";
			}
		}
		catch(SQLException error)
		{
			System.err.println("Error in validating login.");
		}
		return "false";
	}
	
	public void readStudentsFromFile()
	{
		try 
		{
			//Note: StudentList.txt needs to be in the project folder
			FileReader fileRead = new FileReader("StudentList.txt");
			BufferedReader bufferRead = new BufferedReader(fileRead);
			
			String line = "";
			while((line = bufferRead.readLine()) != null)
			{
				String[] temp = line.split(" ");
				insertStudentPreparedStatement(Integer.parseInt(temp[0]), temp[1], temp[2]);
			}
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
		}
	}
	
	public void readAdminsFromFile()
	{
		try 
		{
			//Note: Admins.txt needs to be in the project folder
			FileReader fileRead = new FileReader("Admins.txt");
			BufferedReader bufferRead = new BufferedReader(fileRead);
			
			String line = "";
			while((line = bufferRead.readLine()) != null)
			{
				String[] temp = line.split(" ");
				insertAdminPreparedStatement(Integer.parseInt(temp[0]), temp[1], temp[2]);
			}
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
		}
	}
	
	public void readCoursesFromFile()
	{
		try 
		{
			//Note: courses.txt needs to be in the project folder
			FileReader fileRead = new FileReader("courses.txt");
			BufferedReader bufferRead = new BufferedReader(fileRead);
			
			String line = "";
			while((line = bufferRead.readLine()) != null)
			{
				String[] temp = line.split(" ");
				insertCoursePreparedStatement(Integer.parseInt(temp[1]), temp[0]);
			}
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
		}
	}
	
	public ArrayList<Course> readCoursesFromDB() 
	{
		String query = "SELECT * FROM course";
		ArrayList<Course> courseList = new ArrayList<Course>();
		
		try
		{
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			
			while(rs.next())
			{
				int courseID = rs.getInt("id");
				String courseName = rs.getString("courseName");
				courseList.add(new Course(courseName, courseID));
			}
		}
		catch(SQLException error)
		{
			error.printStackTrace();
		}
		
		return courseList;
	}
	
	public ArrayList<Student> readStudentsFromDB()
	{
		String query = "SELECT * FROM student";
		ArrayList<Student> studentList = new ArrayList<Student>();
		
		try
		{
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			
			while(rs.next())
			{
				int studentID = rs.getInt("id");
				String studentName = rs.getString("name");
				studentList.add(new Student(studentName, studentID));
			}
		}
		catch(SQLException error)
		{
			error.printStackTrace();
		}
		
		return studentList;
	}

	/**
	 * 
	 * @param args Default.
	 */
	public static void main(String[] args)
	{
		DBManager myApp = new DBManager();
		myApp.createStudentTable();
		myApp.createCourseTable();
		myApp.createAdminTable();
		
		myApp.readStudentsFromFile();
		myApp.readCoursesFromFile();
		myApp.readAdminsFromFile();
		
		myApp.validateStudentLogin(1, "gxng");
		myApp.validateAdminLogin(1, "ensf409");
		myApp.close();
	}
}