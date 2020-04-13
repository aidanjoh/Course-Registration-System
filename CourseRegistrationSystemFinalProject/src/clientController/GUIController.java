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
		
		startUpView = new StartUpMenuGUI();
		startUpView.setVisible(true);
		
		studentView = new StudentMenuGUI();
		studentView.setVisible(false);
		
		viewCatalogue = new ViewCatalogueCourses(studentView);
		viewCatalogue.setVisible(false);
		
		viewStudentsCourses = new ViewStudentsCourses(studentView);
		viewStudentsCourses.setVisible(false);
		
		startUpView.addLoginButtonListener(new addButtonListener());
		
		
		studentView.addSearchCatalogueButtonListener(new addButtonListener());
		studentView.addAddCourseButtonListener(new addButtonListener());
		studentView.addRemoveCourseButtonListener(new addButtonListener());
		studentView.addViewCatalogueButtonListener(new addButtonListener());
		studentView.addViewMyCoursesButtonListener(new addButtonListener());
		studentView.addQuitButtonListener(new addButtonListener());
		
		viewCatalogue.addReturnButtonListener(new addButtonListener());
		viewStudentsCourses.addReturnButtonListener(new addButtonListener());
	}
	
	public class addButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() == startUpView.getLoginButton()) 
			{
				studentUCID = startUpView.getUCID();
				
				startUpView.setVisible(false);
				studentView.setVisible(true);
				
				System.out.println(studentUCID);
			}
			else if(e.getSource() == studentView.getSearchCatalogueButton()) 
			{
				String courseNameAndNum = studentView.getCourseNameAndNumberForSearchCatalogue();
				System.out.println(courseNameAndNum);
				String messageToBeSent = "1 " + studentUCID + " " + courseNameAndNum;
				System.out.println(messageToBeSent);
				
				String messageRecieved = client.communicateWithServer(messageToBeSent);
				System.out.println(messageRecieved);
		
			}
			else if(e.getSource() == studentView.getAddCourseButton()) 
			{
				String courseNameAndNum = studentView.getCourseNameAndNumberForAddAndRemoveCourse();
				String messageToBeSent = "2 " + studentUCID + " " + courseNameAndNum;
				System.out.println(courseNameAndNum);
				String messageRecieved = client.communicateWithServer(messageToBeSent);
				System.out.println(messageRecieved);
			}
			else if(e.getSource() == studentView.getRemoveCourseButton()) 
			{
				String courseNameAndNum = studentView.getCourseNameAndNumberForAddAndRemoveCourse();
				String messageToBeSent = "3 " + studentUCID + " " + courseNameAndNum;
				System.out.println("Remove Course");
				System.out.println(courseNameAndNum);
				String messageRecieved = client.communicateWithServer(messageToBeSent);
				System.out.println(messageRecieved);
			}
			else if(e.getSource() == studentView.getViewCatalogueButton()) 
			{
				System.out.println("View Catalogue");
				String messageToBeSent = "4 " + studentUCID;
				String messageRecieved = client.communicateWithServer(messageToBeSent);
				System.out.println(messageRecieved);
				viewCatalogue.setVisible(true);
			}
			else if(e.getSource() == studentView.getViewMyCoursesButton()) 
			{
				System.out.println("View My Courses");
				String messageToBeSent = "5 " + studentUCID;
				String messageRecieved = client.communicateWithServer(messageToBeSent);
				System.out.println(messageRecieved);
				viewStudentsCourses.setVisible(true);
			}
			else if(e.getSource() == studentView.getQuitButton()) 
			{
				String messageToBeSent = "6 " + studentUCID;
				String messageRecieved = client.communicateWithServer(messageToBeSent);
				System.out.println(messageRecieved);
				System.out.println("Quit");
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
