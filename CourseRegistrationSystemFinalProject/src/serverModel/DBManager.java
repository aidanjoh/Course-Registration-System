package serverModel;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * This class is simulating a database for our program
 * 
 * @author Aidan Johnson and Michele Piperni
 *
 */
public class DBManager 
{
	
	private ArrayList<Course> courseList;
	private ArrayList<Student> studentList;

	public DBManager () 
	{
		courseList = new ArrayList<Course>();
		studentList = new ArrayList<Student>();
	}

	public ArrayList<Course> readCoursesFromDB() 
	{
		return readCoursesFromFile();
	}
	
	public ArrayList<Course> readCoursesFromFile()
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
				courseList.add(new Course(temp[0], Integer.parseInt(temp[1])));
			}
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
		}
		return courseList;
	}
	
	public ArrayList<Student> readStudentsFromFile()
	{
		try 
		{
			//Note: students.txt needs to be in the project folder
			FileReader fileRead = new FileReader("students.txt");
			BufferedReader bufferRead = new BufferedReader(fileRead);
			
			String line = "";
			while((line = bufferRead.readLine()) != null)
			{
				String[] temp = line.split(" ");
				studentList.add(new Student(temp[0], Integer.parseInt(temp[1])));
			}
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
		}
		return studentList;
	}
	
	public ArrayList<Student> readStudentsFromDB()
	{
		return readStudentsFromFile();
	}
}
