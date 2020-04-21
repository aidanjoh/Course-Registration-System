package serverModel;
import java.util.ArrayList;

/**
 * The class CourseOffering has the course offering, which has a section number and max capacity of each course.
 * 
 * @author Aidan Johnson and Michele Piperni
 * @version 1.0
 * @since April 10, 2020
 *
 */
public class CourseOffering 
{
	/**
	 * The section number of the course.
	 */
	private int secNum;
	
	/**
	 * The section capacity of the course.
	 */
	private int secCap;
	
	/**
	 * The course object that will have an offering registration list.
	 */
	private Course theCourse;
	
	/**
	 * The array list of Registration objects holding the different course and student registration for different courses.
	 */
	private ArrayList <Registration> offeringRegList;
	
	/**
	 * Constructs a course offering object by assigning the section number and capacity for the course
	 * and adding a registration array list to the offering registration list.
	 * 
	 * @param secNum the section number of the course.
	 * @param secCap the section capacity of the course.
	 */
	public CourseOffering (int secNum, int secCap)
	{
		this.setSecNum(secNum);
		this.setSecCap(secCap);
		offeringRegList = new ArrayList <Registration>();
	}
	
	/**
	 * Creates a String representation of the course registration.
	 * 
	 * @return a string representing the course registration.
	 */
	@Override
	public String toString () 
	{
		String st = "\0";
		st += getTheCourse().getCourseName() + " " + getTheCourse().getCourseNum() + "\0";
		st += "Section Num: " + getSecNum() + ", section cap: "+ getSecCap() +"\0";
		return st;
	}
	
	/**
	 * Adds a registration object to the offering registration list.
	 * 
	 * @param registration the registration object to be added.
	 */
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
	
	/**
	 * Removes a registration object from the offering registration list.
	 * 
	 * @param registration the registration object to be removed
	 */
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
	
	//-------------------Getter and Setters------------------------------//
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
}
