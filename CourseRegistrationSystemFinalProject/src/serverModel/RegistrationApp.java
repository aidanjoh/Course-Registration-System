package serverModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

/**
 * 
 * @author Aidan Johnson and Michele Piperni
 *
 */
public class RegistrationApp implements Runnable
{
	private CourseCatalogue courseCatalogue;
	
	private ArrayList<Student> studentList;
	
	private DBManager dbManager;
	
	private Socket regAppSocket;
	
	private BufferedReader socketInput;
	
	private PrintWriter socketOutput;

	public RegistrationApp(Socket regAppSocket)
	{
		courseCatalogue = new CourseCatalogue();
		dbManager = new DBManager();
		studentList = dbManager.readStudentsFromDB();
		this.regAppSocket = regAppSocket;
		
		try
		{
			socketInput = new BufferedReader(new InputStreamReader(regAppSocket.getInputStream()));
			socketOutput = new PrintWriter(regAppSocket.getOutputStream());
		}
		catch(IOException error)
		{
			error.printStackTrace();
		}
	}

	@Override
	public void run()
	{
		String input = null;
		boolean running = true;
		
		while(running)
		{
			try 
			{
				input = socketInput.readLine();
				
				if(input != null)
				{
					String[] infoSent = input.split(" ");
					int methodToRun = Integer.parseInt(infoSent[0]);
					
					int studentID;
					String courseName;
					int courseNumber;
					int sectionNumber;
					String messageToBeSent;
					
					switch(methodToRun)
					{
						case 1:
							courseName = infoSent[2];
							courseNumber = Integer.parseInt(infoSent[3]);
							messageToBeSent = searchCatalogueCourses(courseName, courseNumber);
							socketOutput.println(messageToBeSent);
							socketOutput.flush();
							break;
						case 2:
							studentID = Integer.parseInt(infoSent[1]);
							courseName = infoSent[2];
							courseNumber = Integer.parseInt(infoSent[3]);
							sectionNumber = Integer.parseInt(infoSent[4]);
							messageToBeSent = addCourse(studentID, courseName, courseNumber, sectionNumber);
							socketOutput.println(messageToBeSent);
							socketOutput.flush();
							break;
						case 3:
							studentID = Integer.parseInt(infoSent[1]);
							courseName = infoSent[2];
							courseNumber = Integer.parseInt(infoSent[3]);
							messageToBeSent = removeCourse(studentID, courseName, courseNumber);
							socketOutput.println(messageToBeSent);
							socketOutput.flush();
							break;
						case 4:
							messageToBeSent = viewAllCoursesInCatalogue();
							socketOutput.println(messageToBeSent);
							socketOutput.flush();
							break;
						case 5:
							studentID = Integer.parseInt(infoSent[1]);
							messageToBeSent = viewAllStudentsCourses(studentID);
							socketOutput.println(messageToBeSent);
							socketOutput.flush();
							break;
						case 6:
							System.out.println("\nExiting Program, see you later!");
							socketOutput.println("quit");
							socketOutput.flush();
							
							//Properly closing all of the sockets.
							try
							{
								socketInput.close();
								socketOutput.close();
								regAppSocket.close();
							}
							catch(IOException error)
							{
								System.err.println("Closing error: " + error.getMessage());
							}
							return;
					}
				}
			}
			catch(IOException error)
			{
				System.err.println("Sending error: " + error.getMessage());
			}
		}
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
			stringToBeSent = "The course does not exist.";
			return stringToBeSent;
		}
		
		stringToBeSent = student.removeRegistration(course);
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
