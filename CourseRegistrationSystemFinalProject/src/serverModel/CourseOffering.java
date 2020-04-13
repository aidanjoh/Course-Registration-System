package serverModel;
import java.util.ArrayList;

/**
 * 
 * @author Aidan Johnson and Michele Piperni
 *
 */
public class CourseOffering 
{
	
	private int secNum;
	
	private int secCap;
	
	private Course theCourse;
	
	//private ArrayList<Student> studentList;
	private ArrayList <Registration> offeringRegList;
	
	public CourseOffering (int secNum, int secCap) 
	{
		this.setSecNum(secNum);
		this.setSecCap(secCap);
		offeringRegList = new ArrayList <Registration>();
	}
	
	public int getSecNum() 
	{
		return secNum;
	}
	
	public void setSecNum(int secNum) 
	{
		this.secNum = secNum;
	}
	
	public int getSecCap() 
	{
		return secCap;
	}
	
	public void setSecCap(int secCap) 
	{
		this.secCap = secCap;
	}
	
	public Course getTheCourse() 
	{
		return theCourse;
	}
	
	public void setTheCourse(Course theCourse) 
	{
		this.theCourse = theCourse;
	}
	@Override
	public String toString () 
	{
		String st = "\0";
		st += getTheCourse().getCourseName() + " " + getTheCourse().getCourseNum() + "\0";
		st += "Section Num: " + getSecNum() + ", section cap: "+ getSecCap() +"\0";
		//We also want to print the names of all students in the section
		return st;
	}
	
	public void addRegistration(Registration registration) 
	{
		offeringRegList.add(registration);
		if(offeringRegList.size() > 7)
		{
			//System.out.printf("There are %d students registered in the course so it can run!\n", offeringRegList.size());
			theCourse.setValid(true);
		}
		else
		{
			//System.out.printf("There needs to be %d more students registered in this course for it to run!\n" , (8-offeringRegList.size()));
			theCourse.setValid(false);
		}
	}
	
	public void removeRegistration(Registration registration)
	{
		offeringRegList.remove(registration);
		if(offeringRegList.size() > 7)
		{
			//System.out.printf("There are still %d students registered in this course so it can run!\n", offeringRegList.size());
			theCourse.setValid(true);
		}
		else
		{
			//System.out.printf("There needs to be %d more students registered in this course for it to run!\n" , (8-offeringRegList.size()));
			theCourse.setValid(false);
		}
	}
}
