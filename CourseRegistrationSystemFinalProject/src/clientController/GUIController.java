package clientController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import clientView.*;

/**
 * 
 * @author Aidan Johnson and Michele Piperni
 *
 */
public class GUIController 
{

	private StartUpMenuGUI startUpView;
	private StudentMenuGUI studentView;
	private ViewCatalogueCourses viewCatalogue;
	private ViewStudentsCourses viewStudentsCourses;
	
	/**
	 * 
	 */
	private ClientController client;
	
	private int studentUCID; //this is so the client knows which student it is
	
	/**
	 * 
	 */
	public GUIController(ClientController client) 
	{
		this.client = client;
		
		//Start Up Menu; Initially Visible, Initialize Login button listener
		startUpView = new StartUpMenuGUI();
		startUpView.setVisible(true);
		startUpView.addLoginButtonListener(new addButtonListener());
		
		//Student Menu; Initially not visible, Initialize 6 button listener
		studentView = new StudentMenuGUI();
		studentView.addSearchCatalogueButtonListener(new addButtonListener());
		studentView.addAddCourseButtonListener(new addButtonListener());
		studentView.addRemoveCourseButtonListener(new addButtonListener());
		studentView.addViewCatalogueButtonListener(new addButtonListener());
		studentView.addViewMyCoursesButtonListener(new addButtonListener());
		studentView.addQuitButtonListener(new addButtonListener());
		
		//View Catalogue Menu; Initially not visible, Initialize return button listener
		viewCatalogue = new ViewCatalogueCourses(studentView);
		viewCatalogue.addReturnButtonListener(new addButtonListener());
		
		//View Students Courses Menu; Initially not visible, Initialize return button listener
		viewStudentsCourses = new ViewStudentsCourses(studentView);
		viewStudentsCourses.addReturnButtonListener(new addButtonListener());
	}
	
	public class addButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() == startUpView.getLoginButton()) 
			{
				studentUCID = startUpView.getUCID();
				
				//This is for the error checking set up for the login
				if(studentUCID == 0) {
					return;
				}
				
				startUpView.setVisible(false);
				studentView.setVisible(true);
			}
			else if(e.getSource() == studentView.getSearchCatalogueButton()) 
			{
				String courseNameAndNum = studentView.getCourseNameAndNumberForSearchCatalogue();
				
				//This is for if the cancel button was pressed
				if(courseNameAndNum == null) {
					return;
				}
				
				String messageToBeSent = "1 " + studentUCID + " " + courseNameAndNum;
	
				
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
				
				String messageToBeSent = "2 " + studentUCID + " " + courseNameAndNum;
				
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
				
				String messageToBeSent = "3 " + studentUCID + " " + courseNameAndNum;
				
				String messageRecieved = client.communicateWithServer(messageToBeSent);
				studentView.showRemoveCourseOptionPane(messageRecieved);
				//System.out.println(messageRecieved);
			}
			else if(e.getSource() == studentView.getViewCatalogueButton()) 
			{
				String messageToBeSent = "4 " + studentUCID;
				
				String messageRecieved = client.communicateWithServer(messageToBeSent);
				System.out.println(messageRecieved);
				
				//Set the Text Area
				viewCatalogue.setStudentRecords(messageRecieved);
				
				viewCatalogue.setVisible(true);
			}
			else if(e.getSource() == studentView.getViewMyCoursesButton()) 
			{
				String messageToBeSent = "5 " + studentUCID;
				String messageRecieved = client.communicateWithServer(messageToBeSent);
				
				System.out.println(messageRecieved);
				
				//Set the Text Area
				viewStudentsCourses.setStudentRecords(messageRecieved);
				
				viewStudentsCourses.setVisible(true);
			}
			else if(e.getSource() == studentView.getQuitButton()) 
			{
				String messageToBeSent = "6 " + studentUCID;
				String messageRecieved = client.communicateWithServer(messageToBeSent);
				
				System.out.println(messageRecieved);
				System.exit(1);
			}
			else if(e.getSource() == viewCatalogue.getReturnButton()) 
			{
				System.out.println("Closed Catalogue");
				viewCatalogue.dispose();
			}
			else if(e.getSource() == viewStudentsCourses.getReturnButton()) 
			{
				System.out.println("Closed Student Menu");
				viewStudentsCourses.dispose();
			}
			
		}
		
	}

}
