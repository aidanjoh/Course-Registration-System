package serverModel;

import java.util.ArrayList;

/**
 * The class Student has attributes and methods to represent a student.
 * 
 * @author Aidan Johnson and Michele Piperni
 * @version 1.0
 * @since April 15, 2020
 */
public class Student 
{
	/**
	 * The student's name represented as a string.
	 */
	private String studentName;
	
	/**
	 * The students ID represented as an integer.
	 */
	private int studentId;
	
	/**
	 * The student's registration of courses list.
	 */
	private ArrayList<Registration> studentRegList;
	
	/**
	 * Constructs a Student object by assigning a name and id. Also creates a new registration list.
	 * 
	 * @param studentName the name of the student.
	 * @param studentId the id of the student.
	 */
	public Student (String studentName, int studentId) 
	{
		this.setStudentName(studentName);
		this.setStudentId(studentId);
		studentRegList = new ArrayList<Registration>();
	}
	
	/**
	 * Adds a registration object for a course to a student's registration list.
	 * 
	 * @param registration the registration object to be added to the student's registration list.
	 */
	public void addRegistration(Registration registration) 
	{
		studentRegList.add(registration);
	}
	
	/**
	 * Removes a registration of a course from a student's registration list.
	 * 
	 * @param course the course to be removed from the student's registration list.
	 * @return a String displaying if the course was removed successfully or not.
	 */
	public String removeRegistration(Course course)
	{
		for (Registration registration : studentRegList)
		{
			if(registration.getTheOffering().getTheCourse() == course)
			{
				registration.removeRegistration();
				studentRegList.remove(registration);
				String messageToBeSent;
				messageToBeSent = course.getCourseName() + " " + course.getCourseNum() + " was removed.\0";
				return messageToBeSent;
			}
		}
		return "\0" + studentName +" is not enrolled in " + course.getCourseName() + " " + course.getCourseNum() + "\0";
	}
	
	/**
	 * Prints all the student's courses into one string. If the student has no courses a message is returned
	 * saying that the student does not have any courses.
	 * 
	 * @return a string holding all the student's courses in a String format.
	 */
	public String printTheStudentsCourses()
	{
		if(studentRegList.size() == 0)
		{
			return "The student does not have any courses! \0";
		}
		else
		{
			String courses = "";
			for(Registration registration : studentRegList)
			{
				courses += registration.getTheOffering().toString();
			}
			return courses;
		}
	}

	/**
	 * Generates a String representation of a Student object.
	 * 
	 * @return a string representation of the student object.
	 */
	@Override
	public String toString () 
	{
		String st = "Student Name: " + getStudentName() + "\0" +
				"Student Id: " + getStudentId() + "\0\0";
		return st;
	}

	//----------------Getters and Setters--------------------------//
	public ArrayList<Registration> getStudentRegList() 
	{
		return studentRegList;
	}
	
	public String getStudentName()
	{
		return studentName;
	}

	public void setStudentName(String studentName)
	{
		this.studentName = studentName;
	}

	public int getStudentId()
	{
		return studentId;
	}

	public void setStudentId(int studentId) 
	{
		this.studentId = studentId;
	}
}