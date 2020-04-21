package serverModel;

import java.util.ArrayList;

/**
 * The class Course has data and attributes to represent a course.
 * 
 * @author Aidan Johnson and Michele Piperni
 * @version 1.0
 * @since April 15, 2020
 */
public class Course 
{
	/**
	 * The name of the course.
	 */
	private String courseName;
	
	/**
	 * The number of the course.
	 */
	private int courseNum;
	
	/**
	 * ArrayList holding the pre-requisite courses of the course.
	 */
	private ArrayList<Course> preReq;
	
	/**
	 * ArrayList holding the offerings for the course.
	 */
	private ArrayList<CourseOffering> offeringList;
	
	/**
	 * A boolean representing if the course can run or not based upon the number of students registered for the course.
	 */
	private boolean isValid;

	/**
	 * Constructs a course object by assigning a course name and number and creating a offering list for the course.
	 * 
	 * @param courseName the name of the course.
	 * @param courseNum the number of the course.
	 */
	public Course(String courseName, int courseNum) 
	{
		this.setCourseName(courseName);
		this.setCourseNum(courseNum);
		
		// Both of the following are only association
		preReq = new ArrayList<Course>();
		offeringList = new ArrayList<CourseOffering>();
		setValid(false);
	}

	/**
	 * Adds a course offering object to the courses offering list.
	 * 
	 * @param offering the offering object to be added.
	 */
	public void addOffering(CourseOffering offering) 
	{
		if (offering != null && offering.getTheCourse() == null) 
		{
			offering.setTheCourse(this);
			
			if (!offering.getTheCourse().getCourseName().equals(courseName) || offering.getTheCourse().getCourseNum() != courseNum) 
			{
				System.err.println("Error! This section belongs to another course!");
				return;
			}
			
			offeringList.add(offering);
		}
	}
	
	/**
	 * Gets the course offering at a specific index in the course's offering list.
	 * 
	 * @param i the index of the course offering that
	 * @returns the course offering at a given index i.
	 */
	public CourseOffering getCourseOfferingAt(int i) 
	{
		if (i < 0 || i >= offeringList.size())
			return null;
		else
			return offeringList.get(i);
	}
	
	/**
	 * Creates a String representation of the course object.
	 * 
	 * @returns a String representing the Course's information.
	 */
	@Override
	public String toString () 
	{
		String st = "\0";
		st += getCourseName() + " " + getCourseNum();
		st += "\0All course sections:\0";
		for (CourseOffering c : offeringList)
			st += c;
		st += "\0-------\0";
		return st;
	}
	
	//----------------Getters and Setters------------------------//
	public String getCourseName() 
	{
		return courseName;
	}

	public void setCourseName(String courseName) 
	{
		this.courseName = courseName;
	}

	public int getCourseNum() 
	{
		return courseNum;
	}

	public void setCourseNum(int courseNum) 
	{
		this.courseNum = courseNum;
	}

	public boolean isValid() 
	{
		return isValid;
	}

	public void setValid(boolean isValid) 
	{
		this.isValid = isValid;
	}
}