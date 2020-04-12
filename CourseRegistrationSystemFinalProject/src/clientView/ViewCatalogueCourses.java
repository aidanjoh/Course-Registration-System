package clientView;

import java.awt.Color;
import java.awt.Font;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * 
 * @author Aidan Johnson and Michele Piperni
 * @version 1.0
 * @since April 10, 2020
 */
public class ViewCatalogueCourses extends JDialog
{
	/**
	 * 
	 */
	private JLabel title;
	
	/**
	 * 
	 */
	private JTextArea listOfStudentsCourses;
	
	/**
	 * 
	 */
	private JButton returnButton;
	
	/**
	 * 
	 */
	public ViewCatalogueCourses(StudentMenuGUI mainFrame)
	{
		super(mainFrame, "Course Catalogue", ModalityType.DOCUMENT_MODAL);
		
		this.setSize(400, 400);
		this.setLocationRelativeTo(mainFrame);
		
		JPanel titlePanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		
		title = new JLabel("Courses in the Catalogue");
		title.setFont(new Font("Helvetica", Font.BOLD, 30));
		title.setForeground(Color.black);
		title.setSize(100, 100);
		title.setLocation(200, 10);
		titlePanel.add(title);
		
		listOfStudentsCourses = new JTextArea();
		listOfStudentsCourses.setBackground(Color.LIGHT_GRAY);
		
		//Making it so the user can not edit the courses displayed
		listOfStudentsCourses.setEditable(false);
		
		JScrollPane studentCourses = new JScrollPane(listOfStudentsCourses);
		studentCourses.setSize(600, 250);
		studentCourses.setLocation(0, 50);
		
		Color lightBlue = new Color(51,194,255);
		
		returnButton = new JButton("Return to Main Menu");
		returnButton.setSize(200, 50);
		returnButton.setLocation(185, 300);
		returnButton.setForeground(Color.black);
		returnButton.setBackground(lightBlue);
		returnButton.setFont(new Font("Helvetica", Font.BOLD, 20));
		buttonPanel.add(returnButton);
		
		this.add("Center", studentCourses);
		this.add("South", buttonPanel);
		this.add("North", titlePanel);
	}
	
	/**
	 * Adds the action listener for the return to main menu button.
	 * 
	 * @param listenForReturnButton ActionListener object for return button.
	 */
	public void addReturnButtonListener(ActionListener listenForReturnButton)
	{
		returnButton.addActionListener(listenForReturnButton);
	}

}
