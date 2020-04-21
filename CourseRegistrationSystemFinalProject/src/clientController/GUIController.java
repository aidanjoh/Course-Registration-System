package clientController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import clientView.*;

/**
 * GUI Controller class. Contains all the functionality of all action events from
 * buttons within an inner class.
 * 
 * @author Aidan Johnson and Michele Piperni
 * @version 1.0
 * @since April 15, 2020
 */
public class GUIController 
{
	/**
	 * JFrame for start up menu.
	 */
	private StartUpMenuGUI startUpView;
	/**
	 * JFrame for student menu.
	 */
	private StudentMenuGUI studentView;
	/**
	 * JFrame for admin menu.
	 */
	private AdminMenuGUI adminView;
	/**
	 * View catalogue JFrame.
	 */
	private ViewCatalogueCourses viewCatalogue;
	/**
	 * View student courses JFrame.
	 */
	private ViewStudentsCourses viewStudentsCourses;
	/**
	 * Client object.
	 */
	private ClientController client;
	/**
	 * UCID of the student. This is so the client knows which student it is.
	 */
	private int UCID;
	
	/**
	 * Constructor for the GUI controller class. Initializes the clients, builds 
	 * the different frames and adds button listeners.
	 */
	public GUIController(ClientController client) 
	{
		this.client = client;
		
		//Start Up Menu; Initially Visible, Initialize Login button listener
		startUpView = new StartUpMenuGUI();
		startUpView.setVisible(true);
		startUpView.addLoginButtonListener(new addButtonListener());
		
		//Student Menu; Initially not visible, Initialize 6 button listener + logout button
		studentView = new StudentMenuGUI();
		studentView.addSearchCatalogueButtonListener(new addButtonListener());
		studentView.addAddCourseButtonListener(new addButtonListener());
		studentView.addRemoveCourseButtonListener(new addButtonListener());
		studentView.addViewCatalogueButtonListener(new addButtonListener());
		studentView.addViewMyCoursesButtonListener(new addButtonListener());
		studentView.addQuitButtonListener(new addButtonListener());
		studentView.addLogoutButtonListener(new addButtonListener());
		
		//Admin Menu; Initially not visible, Initialize 3 button listener + logout button
		adminView = new AdminMenuGUI();
		adminView.addViewCatalogueButtonListener(new addButtonListener());
		adminView.addAddCoursesToCatalogueButtonListener(new addButtonListener());
		adminView.addQuitButtonListener(new addButtonListener());
		adminView.addLogoutButtonListener(new addButtonListener());
		
		//View Catalogue Menu; Initially not visible, Initialize return button listener
		viewCatalogue = new ViewCatalogueCourses(studentView);
		viewCatalogue.addReturnButtonListener(new addButtonListener());
		
		//View Students Courses Menu; Initially not visible, Initialize return button listener
		viewStudentsCourses = new ViewStudentsCourses(studentView);
		viewStudentsCourses.addReturnButtonListener(new addButtonListener());
	}
	
	/**
	 * Add button listener class. Contains all the action events of the 
	 * buttons and does different action depending on the action event.
	 * Action events form a string which is sent through the client and 
	 * server.
	 * 
	 * @author Aidan Johnson and Michele Piperni
	 * @version 1.0
	 * @since April 15, 2020
	 */
	public class addButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() == startUpView.getLoginButton()) 
			{
				UCID = startUpView.getUCID();
				String readPassword = startUpView.getPassword();
				
				//This is for the error checking set up for the login
				if(UCID == 0 || readPassword.equals("")) {
					return;
				}
				
				//The if statement checks which radio button is selected
				if(startUpView.getExistingStudent().isSelected()) {
					
					String messageToBeSent = "7 " + UCID + " " + readPassword;
					String messageRecieved = client.communicateWithServer(messageToBeSent);
					
					if(!messageRecieved.equals("false")) //valid ucid and password
					{ 
						startUpView.setVisible(false);
						
						//Set the current user based on the login information
						String currentLogin = "Current User: " + messageRecieved + "    ID: " + UCID;
						studentView.setCurrentLogin(currentLogin);
						
						studentView.setVisible(true);
					}
					else 
					{
						startUpView.showInvalidPasswordAndUCID();
					}
				}
				else if(startUpView.getAdmin().isSelected()) {
			
					String messageToBeSent = "8 " + UCID + " " + readPassword;					
					String messageRecieved = client.communicateWithServer(messageToBeSent);
					
					if(!messageRecieved.equals("false")) //valid ucid and password
					{
						startUpView.setVisible(false);
						
						//Set the current user based on the login information
						String currentLogin = "Current User: " + messageRecieved + "    ID: " + UCID;
						adminView.setCurrentLogin(currentLogin);
						
						adminView.setVisible(true);
					}
					else 
					{
						startUpView.showInvalidPasswordAndUCID();
					}
				}
			}
			else if(e.getSource() == studentView.getSearchCatalogueButton()) 
			{
				String courseNameAndNum = studentView.getCourseNameAndNumberForSearchCatalogue();
				
				//This is for if the cancel button was pressed
				if(courseNameAndNum == null) {
					return;
				}
				
				String messageToBeSent = "1 " + UCID + " " + courseNameAndNum;
				String messageRecieved = client.communicateWithServer(messageToBeSent);
				studentView.showSearchedCatalogue(messageRecieved);
			}
			else if(e.getSource() == studentView.getAddCourseButton()) 
			{
				String courseNameAndNum = studentView.getCourseNameAndNumberForAddAndRemoveCourse();
				
				//This is for if the cancel button was pressed
				if(courseNameAndNum == null) {
					return;
				}
				
				String messageToBeSent = "2 " + UCID + " " + courseNameAndNum;
				String messageRecieved = client.communicateWithServer(messageToBeSent);
				studentView.showAddCourseOptionPane(messageRecieved);
			}
			else if(e.getSource() == studentView.getRemoveCourseButton()) 
			{
				String courseNameAndNum = studentView.getCourseNameAndNumberForAddAndRemoveCourse();
				
				//This is for if the cancel button was pressed
				if(courseNameAndNum == null) {
					return;
				}
				
				String messageToBeSent = "3 " + UCID + " " + courseNameAndNum;
				String messageRecieved = client.communicateWithServer(messageToBeSent);
				studentView.showRemoveCourseOptionPane(messageRecieved);
			}
			else if(e.getSource() == studentView.getViewCatalogueButton() || e.getSource() == adminView.getViewCatalogueButton()) 
			{
				String messageToBeSent = "4 " + UCID;
				String messageRecieved = client.communicateWithServer(messageToBeSent);
				
				//Set the Text Area
				viewCatalogue.setStudentRecords(messageRecieved);
				viewCatalogue.setVisible(true);
			}
			else if(e.getSource() == studentView.getViewMyCoursesButton()) 
			{
				String messageToBeSent = "5 " + UCID;
				String messageRecieved = client.communicateWithServer(messageToBeSent);
				
				//Set the Text Area
				viewStudentsCourses.setStudentRecords(messageRecieved);
				viewStudentsCourses.setVisible(true);
			}
			else if(e.getSource() == studentView.getQuitButton() || e.getSource() == adminView.getQuitButton()) 
			{
				String messageToBeSent = "6 " + UCID;
				client.communicateWithServer(messageToBeSent);
				System.exit(1);
			}
			else if(e.getSource() == studentView.getLogoutButton()) 
			{
				startUpView.resetTextFields();
				studentView.setVisible(false);
				startUpView.setVisible(true);
			}
			else if(e.getSource() == adminView.getLogoutButton()) 
			{
				startUpView.resetTextFields();
				adminView.setVisible(false);
				startUpView.setVisible(true);
			}
			else if(e.getSource() == adminView.getAddCoursesToCatalogueButton()) 
			{
				String courseNameNumSectionsCap = adminView.getCourseNameNumberSecCapForSearchCatalogue();
				
				//This is for if the cancel button was pressed
				if(courseNameNumSectionsCap == null) {
					return;
				}
				
				String messageToBeSent = "9 " + courseNameNumSectionsCap;
				String messageRecieved = client.communicateWithServer(messageToBeSent);
				adminView.showAddCourseOptionPane(messageRecieved);
			}
			else if(e.getSource() == viewCatalogue.getReturnButton()) 
			{
				viewCatalogue.dispose();
			}
			else if(e.getSource() == viewStudentsCourses.getReturnButton()) 
			{
				viewStudentsCourses.dispose();
			}
		}
	}
}
