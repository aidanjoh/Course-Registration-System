package serverModel;

/**
 * The class Registration contains method and data related to registration of a course for a student.
 * 
 * @author Aidan Johnson and Michele Piperni
 * @version 1.0
 * @since April 10, 2020
 */
public class Registration 
{
	/**
	 * The student object registering for a course offering.
	 */
	private Student theStudent;
	
	/**
	 * The course offering object being registered for by a student.
	 */
	private CourseOffering theOffering;
	
	/**
	 * The grade the student has for that course offering.
	 */
	private char grade;
	
	/**
	 * Completes the registration of a student for a specific course by calling the add registration method.
	 * 
	 * @param st the student registers for a course offering.
	 * @param of the course offering being registered for by a student.
	 */
	void completeRegistration(Student st, CourseOffering of) 
	{
		theStudent = st;
		theOffering = of;
		addRegistration();
	}
	
	/**
	 * Adds the registration to the student and the course offering.
	 * 
	 */
	private void addRegistration() 
	{
		theStudent.addRegistration(this);
		theOffering.addRegistration(this);
	}
	
	/**
	 * Removes the registration from the course offering.
	 * 
	 */
	public void removeRegistration()
	{
		theOffering.removeRegistration(this);
	}
	
	/**
	 * Creates a String representing the Registration object,
	 * 
	 * @returns a String representation of the registration object.
	 */
	@Override
	public String toString () 
	{
		String st = "\0";
		st += "Student Name: " + getTheStudent() + "\0";
		st += "The Offering: " + getTheOffering () + "\0";
		st += "Grade: " + getGrade();
		st += "\0-----------\0";
		return st;
	}
	
	//------------------Getter and Setter Functions--------------------//
	public Student getTheStudent() 
	{
		return theStudent;
	}
	
	public void setTheStudent(Student theStudent) 
	{
		this.theStudent = theStudent;
	}
	
	public CourseOffering getTheOffering() 
	{
		return theOffering;
	}
	
	public void setTheOffering(CourseOffering theOffering) 
	{
		this.theOffering = theOffering;
	}
	
	public char getGrade() 
	{
		return grade;
	}
	
	public void setGrade(char grade)
	{
		this.grade = grade;
	}
}