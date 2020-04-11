package serverModel;
import java.util.ArrayList;

//This class is simulating a database for our program
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
		courseList.add(new Course ("ENGG", 233));
		courseList.add(new Course ("ENSF", 409));
		courseList.add(new Course ("PHYS", 259));
		courseList.add(new Course ("ENCM", 511));
		courseList.add(new Course ("ENCM", 467));
		courseList.add(new Course ("ENEL", 400));
		courseList.add(new Course ("ENGG", 225));
		return courseList;
	}
	
	public ArrayList<Student> readStudentsFromDB()
	{
		studentList.add(new Student("Katie", 1));
		studentList.add(new Student("Eric", 2));
		studentList.add(new Student("Mike", 3));
		studentList.add(new Student("George", 4));
		studentList.add(new Student("Alex", 5));
		studentList.add(new Student("James", 6));
		studentList.add(new Student("Barney", 7));
		studentList.add(new Student("Steven", 8));
		studentList.add(new Student("Kyle", 9));
		return studentList;
	}

}
