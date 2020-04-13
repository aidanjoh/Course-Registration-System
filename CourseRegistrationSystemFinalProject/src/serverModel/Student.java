package serverModel;

import java.util.ArrayList;

/**
 * 
 * @author Aidan Johnson and Michele Piperni
 *
 */
public class Student 
{
	
	private String studentName;
	
	private int studentId;
	
	//private ArrayList<CourseOffering> offeringList;
	private ArrayList<Registration> studentRegList;
	
	public ArrayList<Registration> getStudentRegList() 
	{
		return studentRegList;
	}

	public Student (String studentName, int studentId) 
	{
		this.setStudentName(studentName);
		this.setStudentId(studentId);
		studentRegList = new ArrayList<Registration>();
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
	

	public void addRegistration(Registration registration) 
	{
		studentRegList.add(registration);
	}
	
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

	
	@Override
	public String toString () 
	{
		String st = "Student Name: " + getStudentName() + "\0" +
				"Student Id: " + getStudentId() + "\0\0";
		return st;
	}

}
