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
		startUpView.addLoginButtonListener(new addLoginButtonListener());
	}
	
	public class addLoginButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			studentUCID = startUpView.getUCID();
			startUpView.setVisible(false);
			
			studentView = new StudentMenuGUI();
			studentView.setVisible(true);
			
			System.out.println(studentUCID);
		}
		
	}
}
