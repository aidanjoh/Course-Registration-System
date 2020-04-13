package serverModel;

import java.util.ArrayList;

public class RegistrationApp 
{
	private CourseCatalogue courseCatalogue;
	
	private ArrayList<Student> studentList;
	
	private DBManager dbManager;
	
	public RegistrationApp()
	{
		courseCatalogue = new CourseCatalogue();
		dbManager = new DBManager();
		studentList = dbManager.readStudentsFromDB();
	}

	public String searchCatalogueCourses(String courseName, int courseNum) 
	{
		String stringToBeSent;
		
		Course course = courseCatalogue.searchCat(courseName, courseNum);
		if(course == null)
		{
			stringToBeSent = "Course was not found";
			return stringToBeSent;
		}
		else
		{
			stringToBeSent = "Course was found\0";
			stringToBeSent += course.toString();
			return stringToBeSent;
		}
	}

	public String addCourse(int studentID, String courseName, int courseNum, int sectionNumber) 
	{
		Student student = searchForStudent(studentID);
		String stringToBeSent;
		
		if(student == null)
		{
			stringToBeSent = "A student with that ID could not be found.";
			return stringToBeSent;
		}
		
		if(student.getStudentRegList().size() > 5)
		{
			stringToBeSent = "Sorry this student already has 6 courses and can not register for anymore.";
			return stringToBeSent;
		}
		
		Course course = courseCatalogue.searchCat(courseName, courseNum);
		
		if(course == null)
		{
			stringToBeSent = "Course was not found!";
			return stringToBeSent;
		}
		
		for(Registration registration : student.getStudentRegList())
		{
			if(registration.getTheOffering().getTheCourse() == course)
			{
				stringToBeSent = "Sorry the student is already registered in this class!";
				return stringToBeSent;
			}
		}
		
		Registration regManager = new Registration();
		
		if(course.getCourseOfferingAt(sectionNumber-1) == null)
		{
			stringToBeSent = "Sorry the section number you entered was invalid!";
			return stringToBeSent;
		}
		else
		{
			regManager.completeRegistration(student, course.getCourseOfferingAt(sectionNumber-1));
			stringToBeSent = "Course was successfully added!";
			return stringToBeSent;
		}
	}
	
	public Student searchForStudent(int studentID)
	{
		for(Student student : studentList)
		{
			if(student.getStudentId() == studentID)
			{
				return student;
			}
		}
		return null;
	}

	public String removeCourse(int studentID, String courseName, int courseNum) 
	{
		Student student = searchForStudent(studentID);
		String stringToBeSent;
		
		if(student == null)
		{
			stringToBeSent = "A student with that ID could not be found.";
			return stringToBeSent;
		}
		
		Course course = courseCatalogue.searchCat(courseName, courseNum);
		
		if(course == null)
		{
			stringToBeSent = "The student is not enrolled in this course.";
			return stringToBeSent;
		}
		
		student.removeRegistration(course);
		stringToBeSent = "The course was successfully removed!";
		return stringToBeSent;
	}

	public String viewAllCoursesInCatalogue() 
	{
		String stringToBeSent = courseCatalogue.toString();
		return stringToBeSent;
	}

	public String viewAllStudentsCourses(int studentID) 
	{
		Student student = searchForStudent(studentID);
		String stringToBeSent;
		
		if(student == null)
		{
			stringToBeSent = "A student with that ID could not be found.";
			return stringToBeSent;
		}
		
		stringToBeSent = student.printTheStudentsCourses();
		return stringToBeSent;
	}
}
