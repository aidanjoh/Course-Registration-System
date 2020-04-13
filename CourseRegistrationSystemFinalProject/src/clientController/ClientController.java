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
	 * Constructs a ClientController object as well instantiates the socket object for communication.
	 * 
	 * @param serverName the name of the server.
	 * @param portNumber the port number that the server is using for its communication.
	 */
	public ClientController(String serverName, int portNumber)
	{
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
	public String communicateWithServer(String messageToBeSent)
	{
		//String line = "1 Dave";
		String response = "";

		try 
		{
			System.out.println(messageToBeSent);
			socketOutput.println(messageToBeSent);
			response = socketInput.readLine();
			response.replaceAll("\0", "\n");
			System.out.println(response);
		} 
		catch (Exception error)
		{
			System.out.println("Sending error: " + error.getMessage());
				
		}
		
//		try
//		{
//			socketInput.close();
//			socketOutput.close();
//			communicationSocket.close();
//		}
//		catch(IOException error)
//		{
//			System.out.println("Closing error: " + error.getMessage());
//		}
		
		return response;
	}
	
	/**
	 * Creates the ClientController object and calls the client's method communicateWithServer to run a course registration 
	 * client-server application.
	 * 
	 * @param args Default.
	 */
	public static void main(String[] args) 
	{
		ClientController client = new ClientController("localhost", 8099);
		GUIController courseRegMainGUI = new GUIController(client);
	}
}