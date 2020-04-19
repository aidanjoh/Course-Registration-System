package serverController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
	
	
	private ExecutorService threadPool;
	
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
			threadPool = Executors.newCachedThreadPool();	
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
		try
		{
			while(true)
			{
				RegistrationApp regApp = new RegistrationApp(serverSocket.accept());
				System.out.println("Connected with a client!");
				threadPool.execute(regApp);
			}
		}
		catch(Exception error)
		{
			System.err.println("There was an error");
			threadPool.shutdown();
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
		ServerController server = new ServerController(8099);
		server.communicateWithClient();
	}
}