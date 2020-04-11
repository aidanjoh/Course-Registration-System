package serverModel;

import java.util.ArrayList;

public class CourseCatalogue 
{
	
	private ArrayList <Course> courseList;
	
	public CourseCatalogue () 
	{
		loadFromDataBase();
		createCourseOffering(courseList.get(0), 1, 300);
		createCourseOffering(courseList.get(0), 2, 300);
		createCourseOffering(courseList.get(1), 1, 115);
		createCourseOffering(courseList.get(2), 1, 300);
		createCourseOffering(courseList.get(3), 1, 100);
		createCourseOffering(courseList.get(4), 1, 80);
		createCourseOffering(courseList.get(5), 1, 120);
		createCourseOffering(courseList.get(6), 1, 300);
		createCourseOffering(courseList.get(6), 2, 300);
	}
	
	private void loadFromDataBase() 
	{
		DBManager db = new DBManager();
		setCourseList(db.readCoursesFromDB());
	}
	
	
	public void createCourseOffering (Course c, int secNum, int secCap) 
	{
		if (c!= null) 
		{
			CourseOffering theOffering = new CourseOffering (secNum, secCap);
			c.addOffering(theOffering);
		}
	}
	public Course searchCat (String courseName, int courseNum) 
	{
		for (Course c : courseList) 
		{
			if (courseName.equals(c.getCourseName()) && courseNum == c.getCourseNum()) 
			{
				return c;
			}	
		}
		displayCourseNotFoundError();
		return null;
	}
	
	//Typically, methods that are called from other methods of the class
	//are private and are not exposed for use by other classes.
	//These methods are refereed to as helper methods or utility methods
	private void displayCourseNotFoundError() 
	{
		System.err.println("Course was not found!");
		
	}
	public ArrayList <Course> getCourseList() 
	{
		return courseList;
	}


	public void setCourseList(ArrayList<Course> courseList) 
	{
		this.courseList = courseList;
	}
	
	@Override
	public String toString () 
	{
		String st = "All courses in the catalogue: \n";
		for (Course c : courseList) 
		{
			st += c;  //This line invokes the toString() method of Course
			st += "\n";
		}
		return st;
	}

}
