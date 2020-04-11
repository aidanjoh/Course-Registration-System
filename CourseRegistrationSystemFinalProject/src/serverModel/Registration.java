package serverModel;

public class Registration 
{
	private Student theStudent;
	private CourseOffering theOffering;
	private char grade;
	
	void completeRegistration (Student st, CourseOffering of) 
	{
		theStudent = st;
		theOffering = of;
		addRegistration();
	}
	
	private void addRegistration() 
	{
		System.out.printf("Course was succesfully added for the student: %s.\n", theStudent.getStudentName());
		theStudent.addRegistration(this);
		theOffering.addRegistration(this);
	}
	
	public void removeRegistration()
	{
		theOffering.removeRegistration(this);
	}
	
	
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
	
	@Override
	public String toString () 
	{
		String st = "\n";
		st += "Student Name: " + getTheStudent() + "\n";
		st += "The Offering: " + getTheOffering () + "\n";
		st += "Grade: " + getGrade();
		st += "\n-----------\n";
		return st;
	}
}
