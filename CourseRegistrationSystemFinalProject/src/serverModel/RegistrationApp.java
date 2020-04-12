package serverModel;

import java.util.ArrayList;
import java.util.Scanner;

public class RegistrationApp 
{
	private CourseCatalogue courseCatalogue;
	
	private Scanner userInput;
	
	private ArrayList<Student> studentList;
	
	private DBManager dbManager;
	
	public RegistrationApp()
	{
		courseCatalogue = new CourseCatalogue();
		dbManager = new DBManager();
		studentList = dbManager.readStudentsFromDB();
		userInput = new Scanner(System.in);
	}

	public void searchCatalogueCourses(String courseName, int courseNum) 
	{
		//System.out.println("Please enter the course name:");
		//String courseName = userInput.nextLine();
		
		//System.out.println("Please enter the course number:");
		//while(!userInput.hasNextInt())
		//{
			//System.out.println("Sorry the input was not a number! Please try again!");
			//userInput.next();
		//}
		//int courseNum = userInput.nextInt();
		//userInput.nextLine();
		
		System.out.println(courseCatalogue.searchCat(courseName, courseNum));
	}

	public void addCourse(int studentID, String courseName, int courseNum, int sectionNumber) 
	{
		Student student = searchForStudent(studentID);
		if(student == null)
		{
			System.out.println("A student with that ID could not be found.");
			return;
		}
		
		if(student.getStudentRegList().size() > 5)
		{
			System.out.println("Sorry this student already has 6 courses and can not register for anymore.");
			return;
		}
		
		//System.out.println("Please enter the course name:");
		//String courseName = userInput.nextLine();
		
		//System.out.println("Please enter the course number:");
		//while(!userInput.hasNextInt())
		//{
			//System.out.println("Sorry the input was not a number! Please try again!");
			//userInput.next();
		//}
		//int courseNum = userInput.nextInt();
		//userInput.nextLine();
		
		Course course = courseCatalogue.searchCat(courseName, courseNum);
		if(course == null)
		{
			System.out.println("Course was not found!");
			return;
		}
		
		for(Registration registration : student.getStudentRegList())
		{
			if(registration.getTheOffering().getTheCourse() == course)
			{
				System.out.println("Sorry the student is already registered in this class!");
				return;
			}
		}
		
		//System.out.println("Please enter the section number:");
		//while(!userInput.hasNextInt())
		//{
			//System.out.println("Sorry the input was not a number! Please try again!");
			//userInput.next();
		//}
		//int sectionNumber = userInput.nextInt();
		//userInput.nextLine();
		Registration regManager = new Registration();
		
		if(course.getCourseOfferingAt(sectionNumber-1) == null)
		{
			System.out.println("Sorry the section number you entered was invalid!");
			return;
		}
		else
		{
			regManager.completeRegistration(student, course.getCourseOfferingAt(sectionNumber-1));
			return;
		}
	}
	
	public Student searchForStudent(int studentID)
	{
		//System.out.println("Please enter the student's ID number:");
		//while(!userInput.hasNextInt())
		//{
			//System.out.println("Sorry the input was not a number! Please try again!");
			//userInput.next();
		//}
		//int idNumber = userInput.nextInt();
		//userInput.nextLine();
		for(Student student : studentList)
		{
			if(student.getStudentId() == studentID)
			{
				return student;
			}
		}
		return null;
	}

	public void removeCourse(int studentID) 
	{
		Student student = searchForStudent(studentID);
		if(student == null)
		{
			System.out.println("A student with that ID could not be found.");
			return;
		}
		System.out.println("Please enter the course name:");
		String courseName = userInput.nextLine();
		
		System.out.println("Please enter the course number:");
		while(!userInput.hasNextInt())
		{
			System.out.println("Sorry the input was not a number! Please try again!");
			userInput.next();
		}
		int courseNum = userInput.nextInt();
		userInput.nextLine();
		
		Course course = courseCatalogue.searchCat(courseName, courseNum);
		if(course == null)
		{
			return;
		}
		
		student.removeRegistration(course);
	}

	public void viewAllCoursesInCatalogue() 
	{
		System.out.println(courseCatalogue.toString());
	}

	public void viewAllStudentsCourses(int studentID) 
	{
		Student student = searchForStudent(studentID);
		if(student == null)
		{
			System.out.println("A student with that ID could not be found.");
			return;
		}
		System.out.println(student.printTheStudentsCourses());
	}
}
