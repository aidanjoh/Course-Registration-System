package serverController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import serverModel.RegistrationApp;

/**
 * The Class ServerController is the implementation of a server for a course Registration client-server application.
 * 
 * @author Aidan Johnson and Michele Piperni
 * @version 1.0
 * @since April 9, 2020
 *
 */
public class ServerController 
{
	/**
	 * The ServerSocket object serverSocket binds the server to a particular port and accepts connection from a connection attempt.
	 */
	private ServerSocket serverSocket;
	
	/**
	 * The Socket object communicationSocket handles the communication for the server.
	 */
	private Socket communicationSocket;
	
	/**
	 * The PrintWriter object socketOutput writes information back to the client.
	 */
	private PrintWriter socketOutput;
	
	/**
	 * The BufferedReader object socketInput reads the information from the client.
	 */
	private BufferedReader socketInput;
	
	/**
	 * The serverModel
	 */
	private RegistrationApp regApp;
	
	/**
	 * Constructs a ServerController object by assigning a port number to the server socket.
	 * 
	 * @param portNumber the port number of the Server.
	 */
	public ServerController(int portNumber, RegistrationApp regApp)
	{
		System.out.println("Server is now running!");
		
		this.regApp = regApp;
		
		try
		{
			serverSocket = new ServerSocket(portNumber);
			communicationSocket = serverSocket.accept();
			socketInput = new BufferedReader(new InputStreamReader(communicationSocket.getInputStream()));
			socketOutput = new PrintWriter((communicationSocket.getOutputStream()), true);
			
		}
		catch(IOException error)
		{
			System.err.println("Sending error: " + error.getMessage());
		}
	}
	
	/**
	 * Communicates with the client.
	 * 
	 */
	public void communicateWithClient()
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
							messageToBeSent = regApp.searchCatalogueCourses(courseName, courseNumber);
							socketOutput.println(messageToBeSent);
							break;
						case 2:
							studentID = Integer.parseInt(infoSent[1]);
							courseName = infoSent[2];
							courseNumber = Integer.parseInt(infoSent[3]);
							sectionNumber = Integer.parseInt(infoSent[4]);
							messageToBeSent = regApp.addCourse(studentID, courseName, courseNumber, sectionNumber);
							socketOutput.println(messageToBeSent);
							break;
						case 3:
							studentID = Integer.parseInt(infoSent[1]);
							courseName = infoSent[2];
							courseNumber = Integer.parseInt(infoSent[3]);
							messageToBeSent = regApp.removeCourse(studentID, courseName, courseNumber);
							socketOutput.println(messageToBeSent);
							break;
						case 4:
							messageToBeSent = regApp.viewAllCoursesInCatalogue();
							socketOutput.println(messageToBeSent);
							break;
						case 5:
							System.out.println("Viewing all of your courses in your course list!");
							studentID = Integer.parseInt(infoSent[1]);
							messageToBeSent = regApp.viewAllStudentsCourses(studentID);
							socketOutput.println(messageToBeSent);
							break;
						case 6:
							System.out.println("\nExiting Program, see you later!");
							socketOutput.println("quit");
							
							//Properly closing all of the sockets.
							try
							{
								socketInput.close();
								socketOutput.close();
								serverSocket.close();
								communicationSocket.close();
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
	
	/**
	 * Creates the ServerController object and calls the server's method communicateWithClient to implement a 
	 * course registration client-server application.
	 * 
	 * @param args Default
	 * 
	 */
	public static void main(String[] args) 
	{
		RegistrationApp regApp = new RegistrationApp();
		ServerController server = new ServerController(8099, regApp);
		server.communicateWithClient();
	}
}