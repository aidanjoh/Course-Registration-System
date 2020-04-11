package clientController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import clientView.*;


public class GUIController {

	private StartUpMenuGUI startUpView;
	private StudentMenuGUI studentView;
	
	private int studentUCID; //this is so the client knows which student it is
	
	public GUIController() {
		startUpView = new StartUpMenuGUI();
		startUpView.setVisible(true);
		
		studentView = new StudentMenuGUI();
		studentView.setVisible(false);
		
		startUpView.addLoginButtonListener(new addButtonListener());
		
		
		studentView.addSearchCatalogueButtonListener(new addButtonListener());
		studentView.addAddCourseButtonListener(new addButtonListener());
		studentView.addRemoveCourseButtonListener(new addButtonListener());
		studentView.addViewCatalogueButtonListener(new addButtonListener());
		studentView.addViewMyCoursesButtonListener(new addButtonListener());
		studentView.addQuitButtonListener(new addButtonListener());
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
				System.out.println("SearchCatalogue");
			}
			else if(e.getSource() == studentView.getAddCourseButton()) 
			{
				System.out.println("Add Course");
			}
			else if(e.getSource() == studentView.getRemoveCourseButton()) 
			{
				System.out.println("Remove Course");
			}
			else if(e.getSource() == studentView.getViewCatalogueButton()) 
			{
				System.out.println("View Catalogue");
			}
			else if(e.getSource() == studentView.getViewMyCoursesButton()) 
			{
				System.out.println("View My Course");
			}
			else if(e.getSource() == studentView.getQuitButton()) 
			{
				System.out.println("Quit");
			}
			
		}
		
	}

}
