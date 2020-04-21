package serverModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

/**
 * The class RegistrationApp is the main class of the back end controlling what functions run based upon
 * what was received from the client sockets.
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
	 * The SQL database holding the students, admin and courses table.
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
	 * Runs the switch case program that determines based upon what String message was received from the client which back end
	 * functions to run in the Model architecture of the program.
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
				//Reading from the socket
				input = socketInput.readLine();
				
				if(input != null)
				{
					//Splitting the message received to figure out the first integer which represents which function to call
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
					
					//Switch case choosing which function to run based upon the message's first integer received.
					switch(methodToRun)
					{
						//Calling the search catalogue function
						case 1:
							courseName = infoSent[2];
							courseNumber = Integer.parseInt(infoSent[3]);
							messageToBeSent = searchCatalogueCourses(courseName, courseNumber);
							socketOutput.println(messageToBeSent);
							socketOutput.flush();
							break;
							
						//Calling the add course to a specific student function
						case 2:
							studentID = Integer.parseInt(infoSent[1]);
							courseName = infoSent[2];
							courseNumber = Integer.parseInt(infoSent[3]);
							sectionNumber = Integer.parseInt(infoSent[4]);
							messageToBeSent = addCourse(studentID, courseName, courseNumber, sectionNumber);
							socketOutput.println(messageToBeSent);
							socketOutput.flush();
							break;
							
						//Calling the remove course from a specific student function
						case 3:
							studentID = Integer.parseInt(infoSent[1]);
							courseName = infoSent[2];
							courseNumber = Integer.parseInt(infoSent[3]);
							messageToBeSent = removeCourse(studentID, courseName, courseNumber);
							socketOutput.println(messageToBeSent);
							socketOutput.flush();
							break;
							
						//Calling the view all courses in Catalogue function
						case 4:
							messageToBeSent = viewAllCoursesInCatalogue();
							socketOutput.println(messageToBeSent);
							socketOutput.flush();
							break;
							
						//Calling the view all of a specific students courses function
						case 5:
							studentID = Integer.parseInt(infoSent[1]);
							messageToBeSent = viewAllStudentsCourses(studentID);
							socketOutput.println(messageToBeSent);
							socketOutput.flush();
							break;
							
						//Quitting the program
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
							
						//Calling the validate student login function
						case 7:
							studentID = Integer.parseInt(infoSent[1]);
							studentPassword = infoSent[2];
							messageToBeSent = dbManager.validateStudentLogin(studentID, studentPassword);
							socketOutput.println(messageToBeSent);
							socketOutput.flush();
							break;
							
						//Calling the validate admin login function
						case 8:
							adminID = Integer.parseInt(infoSent[1]);
							adminPassword = infoSent[2];
							messageToBeSent = dbManager.validateAdminLogin(adminID, adminPassword);
							socketOutput.println(messageToBeSent);
							socketOutput.flush();
							break;
							
						//Calling the insert a new course into the database (course catalogue) functions
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
				break;
			}
		}
	}
	
	/**
	 * Searches the catalogue for a specific course by calling the searchCat method of course Catalogue which returns a course.
	 * If the course returned is null a message saying that the course was not found is returned, otherwise a message saying 
	 * the course was found with the respective course represented as a string is returned.
	 * 
	 * @param courseName the name of the course being searched for.
	 * @param courseNum the number of the course being searched for.
	 * @return a String message saying that the course was found with the String representing the course if the course was found,
	 * otherwise it returns a String message saying that the course was not found.
	 */
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

	/**
	 * Adds a course to a specific student's registration list. If the course was successfully added then a String message
	 * is returned saying that the course was successfully added, otherwise a specific message is returned with the proper
	 * reason why the course was not added to the student's registration list.
	 * 
	 * @param studentID the ID of the student adding a course.
	 * @param courseName the name of the course being added.
	 * @param courseNum the number of the course being added.
	 * @param sectionNumber the section number of the course being added.
	 * @return a String displaying if the course was successfully added or not. If a course
	 * was not successfully added different messages are returned for specifics reasons,
	 * like the course does not exist or that the student is already registered in 6 courses.
	 */
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
	
	/**
	 * Searches for a student in the array list of students that was created from the SQL database. If the student is found
	 * by searching by the students id then the Student object is returned. If the student is not found then null is returned.
	 * 
	 * @param studentID the ID of the student being searched for.
	 * @return the Student object if the student is in the list, otherwise it returns null.
	 */
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

	/**
	 * Removes a course from a student's registration list. If the course was successfully removed then a String message
	 * is returned saying that the course was successfully removed, otherwise a specific message is returned with the proper
	 * reason why the course was not removed from the student's registration list.
	 * 
	 * @param studentID the ID of the student removing a course.
	 * @param courseName the name of the course being removed.
	 * @param courseNum the number of the course being removed.
	 * @return a String displaying if the course was successfully removed or not. If a course
	 * was not successfully removed different messages are returned for specifics reasons,
	 * like the course does not exist or the student is not registered in the course.
	 */
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

	/**
	 * Displays all the courses in the course catalogue.
	 * 
	 * @return a String that has all of the courses in the course catalogue.
	 */
	public String viewAllCoursesInCatalogue() 
	{
		String stringToBeSent = courseCatalogue.toString();
		return stringToBeSent;
	}

	/**
	 * Displays all the courses that a specific student is enrolled in.
	 * 
	 * @param studentID the ID of the student thats courses are being displayed.
	 * @return the String displaying all of a specific student's courses.
	 */
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
