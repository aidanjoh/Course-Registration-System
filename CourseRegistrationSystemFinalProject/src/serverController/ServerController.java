package serverController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

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
	 * Constructs a ServerController object by assigning a port number to the server socket.
	 * 
	 * @param portNumber the port number of the Server.
	 */
	public ServerController(int portNumber)
	{
		System.out.println("Server is now running!");
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
					//int id = Integer.parseInt(infoSent[1]);
					
					switch(methodToRun)
					{
						case 1:
							System.out.println("Searching the Catalogue for a Course.");
							break;
						case 2:
							System.out.println("Adding a course to your course list!");
							break;
						case 3:
							System.out.println("Removing a course from your course list!");
							break;
						case 4:
							System.out.println("Viewing all courses in the course Catalogue!");
							break;
						case 5:
							System.out.println("Viewing all of your courses in your course list!");
							break;
						case 6:
							System.out.println("\nExiting Program, see you later!");
							return;
					}
				}
				else
				{
					System.out.println("Server is shutting down!");
					break;
				}
			}
			catch(IOException error)
			{
				System.err.println("Sending error: " + error.getMessage());
			}
		}
		
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
		ServerController server = new ServerController(9000);
		server.communicateWithClient();
	}
}