package clientController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * The class ClientController is the implementation of a client for a course registration client-server application.
 * 
 * @author Aidan Johnson and Michele Piperni
 * @version 1.0
 * @since April 9, 2020
 *
 */
public class ClientController 
{
	/**
	 * The PrintWriter object socketOutput is for writing to the server.
	 */
	private PrintWriter socketOutput;
	
	/**
	 * The object BufferedReader socketInput reads the information from the server.
	 */
	private BufferedReader socketInput;
	
	/**
	 * The object Socket communicationSocket will hold the host name and port number and handles the input to and output of the server.
	 */
	private Socket communicationSocket;
	
	/**
	 * The object GUIController courseRegGUI is the graphical user interface for the course registration object.
	 */
	private GUIController courseRegGUI;
	
	/**
	 * Constructs a ClientController object as well instantiates the socket object for communication.
	 * 
	 * @param serverName the name of the server.
	 * @param portNumber the port number that the server is using for its communication.
	 */
	public ClientController(String serverName, int portNumber, GUIController userInterface)
	{
		this.courseRegGUI = userInterface;
		
		try
		{
			communicationSocket = new Socket(serverName, portNumber);
			socketInput = new BufferedReader(new InputStreamReader(communicationSocket.getInputStream()));
			socketOutput = new PrintWriter((communicationSocket.getOutputStream()), true);
		}
		catch(IOException error)
		{
			System.err.println(error.getStackTrace());
		}
	}
	
	/**
	 * 
	 * Reads words inputed by the user and communicates them to the server.
	 */
	public void communicateWithServer()
	{
		String line = "2 2 ENCM 511 1";
		String response = "";
		
		boolean running = true;
		
		while(running) 
		{
			try 
			{
				System.out.println(response);
				if(!line.equals("QUIT") || !response.equalsIgnoreCase("quit"))
				{
					System.out.println(line);
					socketOutput.println(line);
					response = socketInput.readLine();
					System.out.println(response);
				}
				else
				{
					System.out.println("here");
					running = false;
				}
				
				line = "6 " + line;
				
			} 
			catch (IOException error)
			{
				System.out.println("Sending error: " + error.getMessage());
				error.printStackTrace();
				break;
			}
		}
		
		try
		{
			socketInput.close();
			socketOutput.close();
			communicationSocket.close();
		}
		catch(IOException error)
		{
			System.out.println("Closing error: " + error.getMessage());
		}
	}
	
	/**
	 * Creates the ClientController object and calls the client's method communicateWithServer to run a course registration 
	 * client-server application.
	 * 
	 * @param args Default.
	 */
	public static void main(String[] args) 
	{
		GUIController courseRegMainGUI = new GUIController();
		
		//ClientController client = new ClientController("localhost", 8099, courseRegMainGUI);
		//client.communicateWithServer();
	}
}