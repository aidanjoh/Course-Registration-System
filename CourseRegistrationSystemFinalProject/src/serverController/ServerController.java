package serverController;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import serverModel.RegistrationApp;

/**
 * The Class ServerController is the implementation of a server for a course Registration client-server application. The server class
 * uses a thread pool so multiple clients are able to use the application at a time.
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
	 * The Registration object regApp is the server model holding the data and handling what functions should be run in the backend.
	 */
	private RegistrationApp regApp;

	/**
	 * The executorService object threadPool is the thread pool object to handle multiple clients.
	 */
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
	 * Communicates with the client and creates a new thread every time a client connects to the server.
	 *
	 */
	public void communicateWithClient()
	{
		try
		{
			while(true)
			{
				regApp = new RegistrationApp(serverSocket.accept());
				System.out.println("Connected with a client!");
				threadPool.execute(regApp);
			}
		}
		catch(Exception error)
		{
			System.err.println("There was an error with communicating to the client!");
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
		//ServerController server = new ServerController(8099);

		//This commented code was used to run the server and client on separate machine using a specific port.
		ServerController server = new ServerController(9091);
		server.communicateWithClient();
	}
}
