package clientView;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * JDialog panel to show the courses in the catalogue. Has a scroll panel with a 
 * text area containing the courses for a student.
 * 
 * @author Aidan Johnson and Michele Piperni
 * @version 1.0
 * @since April 15, 2020
 */
public class ViewStudentsCourses extends JDialog
{
	/**
	 * Serial UID.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Label for the title of the frame.
	 */
	private JLabel title;
	/**
	 * Text area for the student courses.
	 */
	private JTextArea listOfStudentsCourses;
	/**
	 * Return to main menu button.
	 */
	private JButton returnButton;
	
	/**
	 * Constructor for the view student course menu. Calls several function to 
	 * customize and initialize the frame.
	 */
	public ViewStudentsCourses(StudentMenuGUI mainFrame)
	{
		super(mainFrame, "Student's Courses", ModalityType.DOCUMENT_MODAL);
		this.setSize(400, 400);
		this.setLocationRelativeTo(mainFrame);
		
		JPanel titlePanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		
		title = new JLabel("Your Courses");
		title.setFont(new Font("Helvetica", Font.BOLD, 30));
		title.setForeground(Color.black);
		title.setSize(100, 100);
		title.setLocation(200, 10);
		titlePanel.add(title);
		
		listOfStudentsCourses = new JTextArea();
		
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
	
	//---------------- Getters And Setters --------------------------//
	
	/**
	 * Returns the returnButton.
	 * 
	 * @return returnButton
	 */
	public JButton getReturnButton() {
		return returnButton;
	}
	
	/**
	 * Sets the text area for the student courses.
	 * 
	 * @param records String containing the courses in the catalogue.
	 */
	public void setStudentRecords(String records)
	{
		listOfStudentsCourses.setText(records);
	}
	
	//----------------Button Listener Methods--------------------------//
	
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
