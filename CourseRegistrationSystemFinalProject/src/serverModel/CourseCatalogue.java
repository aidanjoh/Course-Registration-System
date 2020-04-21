package serverModel;

import java.util.ArrayList;

/**
 * The class CourseCatalogue holds all the different courses for the course registration system and has methods to create a course
 * offering for a specific course, load the courses from the SQL database and search the course catalogue for a specific course.
 * 
 * @author Aidan Johnson and Michele Piperni
 * @version 1.0
 * @since April 10, 2020
 */
public class CourseCatalogue 
{
	/**
	 * The array list holding all of the courses in the course registration application.
	 */
	private ArrayList <Course> courseList;
	
	/**
	 * Constructs the course catalogue by loading all the courses from the data base and also adding 
	 * their respective course offerings.
	 */
	public CourseCatalogue() 
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
	
	/**
	 * Loads the courses from the data base and places them into the array list holding all of the courses.
	 */
	private void loadFromDataBase() 
	{
		DBManager db = new DBManager();
		setCourseList(db.readCoursesFromDB());
	}
	
	/**
	 * Creates a course offering for a specific course.
	 * 
	 * @param c the course having an offering created for.
	 * @param secNum the section number of the course offering.
	 * @param secCap the section capacity of the course offering.
	 */
	public void createCourseOffering (Course c, int secNum, int secCap) 
	{
		if (c!= null) 
		{
			CourseOffering theOffering = new CourseOffering (secNum, secCap);
			c.addOffering(theOffering);
		}
	}
	
	/**
	 * Search the catalogue for a specific course by checking for the course name and number in the 
	 * course catalogue.
	 * 
	 * @param courseName the name of the course being searched for.
	 * @param courseNum the number of the course being searched for.
	 * @returns the Course if found in the course catalogue, otherwise null is returned.
	 */
	public Course searchCat (String courseName, int courseNum) 
	{
		for (Course c : courseList) 
		{
			if (courseName.equals(c.getCourseName()) && courseNum == c.getCourseNum()) 
			{
				return c;
			}	
		}
		return null;
	}
	
	/**
	 * Creates a String representing the course catalogue information.
	 * 
	 * @returns a String representation of the course catalogue.
	 */
	@Override
	public String toString () 
	{
		String st = "All courses in the catalogue: \0";
		for (Course c : courseList) 
		{
			st += c;  //This line invokes the toString() method of Course
			st += "\0";
		}
		return st;
	}
	
	//--------------------Getters and Setters--------------------------------//
	public ArrayList <Course> getCourseList() 
	{
		return courseList;
	}

	public void setCourseList(ArrayList<Course> courseList) 
	{
		this.courseList = courseList;
	}
}