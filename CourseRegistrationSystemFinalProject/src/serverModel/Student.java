package serverModel;

import java.util.ArrayList;

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
	
	public void removeRegistration(Course course)
	{
		for (Registration registration : studentRegList)
		{
			if(registration.getTheOffering().getTheCourse() == course)
			{
				registration.removeRegistration();
				studentRegList.remove(registration);
				//System.out.printf("\n%s was removed.\n", course.getCourseName() + " " + course.getCourseNum());
				return;
			}
		}
		//System.out.printf("\n%s is not enrolled in %s\n", studentName , course.getCourseName() + " " + course.getCourseNum());
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
