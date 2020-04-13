package serverModel;

import java.util.ArrayList;

public class Course 
{

	private String courseName;
	private int courseNum;
	private ArrayList<Course> preReq;
	private ArrayList<CourseOffering> offeringList;
	private boolean isValid;

	public Course(String courseName, int courseNum) 
	{
		this.setCourseName(courseName);
		this.setCourseNum(courseNum);
		
		// Both of the following are only association
		preReq = new ArrayList<Course>();
		offeringList = new ArrayList<CourseOffering>();
		setValid(false);
	}

	public void addOffering(CourseOffering offering) 
	{
		if (offering != null && offering.getTheCourse() == null) 
		{
			offering.setTheCourse(this);
			if (!offering.getTheCourse().getCourseName().equals(courseName)
					|| offering.getTheCourse().getCourseNum() != courseNum) 
			{
				System.err.println("Error! This section belongs to another course!");
				return;
			}
			
			offeringList.add(offering);
		}
	}

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

	public CourseOffering getCourseOfferingAt(int i) 
	{
		if (i < 0 || i >= offeringList.size())
			return null;
		else
			return offeringList.get(i);
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

}
