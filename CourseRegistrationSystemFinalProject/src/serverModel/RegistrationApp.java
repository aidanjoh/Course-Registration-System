package serverModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

/**
 * The class RegistartionApp is the main class of the back end controlling what back end functions run.
 * 
 * @author Aidan Johnson and Michele Piperni
 * @version 1.0
 * @since April 15, 2020
 */
public class RegistrationApp implements Runnable
{
	/**
	 * The course catalogue object holding all the different courses and their respective offerings.
	 */
	private CourseCatalogue courseCatalogue;
	
	/**
	 * The list of students in the course registration application.
	 */
	private ArrayList<Student> studentList;
	
	/**
	 * The SQL database holding all the students, admins and courses.
	 */
	private DBManager dbManager;
	
	/**
	 * The Socket object regAppSocket handles the communication between the client and server.
	 */
	private Socket regAppSocket;
	
	/**
	 * The BufferedReader object socketInput reads the information from the client.
	 */
	private BufferedReader socketInput;
	
	/**
	 * The PrintWriter object socketOutput writes information back to the client.
	 */
	private PrintWriter socketOutput;

	/**
	 * Constructs a RegistrationApp object by assigning a socket to the regApp socket and creating a new database object.
	 * 
	 * @param regAppSocket
	 */
	public RegistrationApp(Socket regAppSocket)
	{
		dbManager = new DBManager();
		studentList = dbManager.readStudentsFromDB();
		courseCatalogue = new CourseCatalogue();
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

	/**
	 * 
	 */
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
					int adminID;
					String courseName;
					int courseNumber;
					int sectionNumber;
					String messageToBeSent;
					String studentPassword;
					String adminPassword;
					
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
						case 7:
							studentID = Integer.parseInt(infoSent[1]);
							studentPassword = infoSent[2];
							messageToBeSent = dbManager.validateStudentLogin(studentID, studentPassword);
							socketOutput.println(messageToBeSent);
							socketOutput.flush();
							break;
						case 8:
							adminID = Integer.parseInt(infoSent[1]);
							adminPassword = infoSent[2];
							messageToBeSent = dbManager.validateAdminLogin(adminID, adminPassword);
							socketOutput.println(messageToBeSent);
							socketOutput.flush();
							break;
						case 9:
							courseName = infoSent[1];
							courseNumber = Integer.parseInt(infoSent[2]);
							int numOfCourseSection = Integer.parseInt(infoSent[3]);
							int courseCapacity = Integer.parseInt(infoSent[4]);
							messageToBeSent = dbManager.insertCourse(courseNumber, courseName);
							if(messageToBeSent == null)
							{
								messageToBeSent = courseName + " " + courseNumber + " was not able to be added to the database.";
								socketOutput.println(messageToBeSent);
								socketOutput.flush();
							}
							else
							{
								Course newCourse = new Course(courseName, courseNumber);
								courseCatalogue.getCourseList().add(newCourse);
								for(int num = 1; num < (numOfCourseSection + 1); num++)
								{
									courseCatalogue.createCourseOffering(newCourse, num, courseCapacity);
								}
								socketOutput.println(messageToBeSent);
								socketOutput.flush();
							}
							break;
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
