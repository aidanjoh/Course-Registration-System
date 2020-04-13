package clientView;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class StudentMenuGUI extends JFrame
{

	private JLabel title = new JLabel("Student Menu");
	
	private JLabel optionsTitle = new JLabel("Please Select One of the Following Options");
		
	private JPanel buttonsPanel = new JPanel();
	private JButton searchCatalogueButton = new JButton();
	private JButton addCourseButton = new JButton();
	private JButton removeCourseButton = new JButton();
	private JButton viewCatalogueButton = new JButton();
	private JButton viewMyCoursesButton = new JButton();
	private JButton quitButton = new JButton("Quit");
	
	public StudentMenuGUI()
	{
		super("Course Registration Start Menu");
		
		this.setFrameSizeAndLayout();
		this.makeButtonPanel();
		this.setAlignments();
		this.setFontOptions();
		this.setButtonColors();
		this.addToFrame();
	}
	
	private void setFrameSizeAndLayout()
	{
		this.setSize(600, 400);
		
		BoxLayout boxLayout = new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS); // top to bottom
	    this.setLayout(boxLayout);
	}
	
	private void makeButtonPanel() 
	{
		buttonsPanel.setLayout(new GridLayout(2, 5, 10, 20)); //the second two arguments are the horizontal and vertical gaps
		
		//wraps the text
		addCourseButton.setText("<html><center>"+"Add a"+"<br>"+"Course"+"</center></html>");
		searchCatalogueButton.setText("<html><center>"+"Search the Course"+"<br>"+"Catalogue"+"</center></html>");
		viewCatalogueButton.setText("<html><center>"+"View Courses in"+"<br>"+"the Catalogue"+"</center></html>");
		removeCourseButton.setText("<html><center>"+"Remove a"+"<br>"+"Course"+"</center></html>");
		viewMyCoursesButton.setText("<html><center>"+"View my"+"<br>"+"Courses"+"</center></html>");
		
		JLabel whiteSpace = new JLabel("  ");
		JLabel whiteSpace1 = new JLabel("  ");
		JLabel whiteSpace2 = new JLabel("  ");
		JLabel whiteSpace3 = new JLabel("  ");
		
		buttonsPanel.add(whiteSpace);
		buttonsPanel.add(searchCatalogueButton);
		buttonsPanel.add(addCourseButton);
		buttonsPanel.add(removeCourseButton);
		buttonsPanel.add(whiteSpace1);
		buttonsPanel.add(whiteSpace2);
		buttonsPanel.add(viewCatalogueButton);
		buttonsPanel.add(viewMyCoursesButton);
		buttonsPanel.add(quitButton);
		buttonsPanel.add(whiteSpace3);
	}
	
	private void setAlignments() 
	{
	    title.setAlignmentX( Component.CENTER_ALIGNMENT );
	    optionsTitle.setAlignmentX( Component.CENTER_ALIGNMENT );
	}
	
	private void setFontOptions() 
	{
		title.setFont(new Font("Dialog", Font.BOLD, 24)); 
		optionsTitle.setForeground(Color.darkGray);
		optionsTitle.setFont(new Font("Dialog", Font.BOLD, 20));
		
		searchCatalogueButton.setFont(new Font("Dialog", Font.PLAIN, 12));
		addCourseButton.setFont(new Font("Dialog", Font.PLAIN, 12));
		removeCourseButton.setFont(new Font("Dialog", Font.PLAIN, 12));
		viewCatalogueButton.setFont(new Font("Dialog", Font.PLAIN, 12));
		viewMyCoursesButton.setFont(new Font("Dialog", Font.PLAIN, 12));
		quitButton.setFont(new Font("Dialog", Font.PLAIN, 12));
	}
	
	private void setButtonColors() 
	{
		Color lightBlue = new Color(51,194,255);
		
		searchCatalogueButton.setBackground(lightBlue);
		addCourseButton.setBackground(lightBlue);
		removeCourseButton.setBackground(lightBlue);
		viewCatalogueButton.setBackground(lightBlue);
		viewMyCoursesButton.setBackground(lightBlue);
		quitButton.setBackground(lightBlue);
	}
	
	private void addToFrame() 
	{
		this.add(title);
		this.add(Box.createRigidArea(new Dimension(0, 15)));
		this.add(optionsTitle);
		this.add(Box.createRigidArea(new Dimension(0, 10)));
		this.add(buttonsPanel);
		this.add(Box.createRigidArea(new Dimension(0, 30)));
	}
	
	//---------------- Getters for buttons --------------------------//

	
	public JButton getSearchCatalogueButton() {
		return searchCatalogueButton;
	}


	public JButton getAddCourseButton() {
		return addCourseButton;
	}


	public JButton getRemoveCourseButton() {
		return removeCourseButton;
	}


	public JButton getViewCatalogueButton() {
		return viewCatalogueButton;
	}

	

	public JButton getViewMyCoursesButton() {
		return viewMyCoursesButton;
	}


	public JButton getQuitButton() {
		return quitButton;
	}
	
	//----------------Button Listener Functions--------------------------//
	
	
	/**
	 * 
	 * @param listenForSearchCatalogueButton
	 */
	public void addSearchCatalogueButtonListener(ActionListener listenForSearchCatalogueButton)
	{
		searchCatalogueButton.addActionListener(listenForSearchCatalogueButton);
	}
	
	/**
	 * 
	 * @param listenForAddCourseButton
	 */
	public void addAddCourseButtonListener(ActionListener listenForAddCourseButton)
	{
		addCourseButton.addActionListener(listenForAddCourseButton);
	}
	
	/**
	 * 
	 * @param listenForRemoveCourseButton
	 */
	public void addRemoveCourseButtonListener(ActionListener listenForRemoveCourseButton)
	{
		removeCourseButton.addActionListener(listenForRemoveCourseButton);
	}
	
	/**
	 * 
	 * @param listenForViewCatalogueButton
	 */
	public void addViewCatalogueButtonListener(ActionListener listenForViewCatalogueButton)
	{
		viewCatalogueButton.addActionListener(listenForViewCatalogueButton);
	}
	
	/**
	 * 
	 * @param listenForViewMyCoursesButton
	 */
	public void addViewMyCoursesButtonListener(ActionListener listenForViewMyCoursesButton)
	{
		viewMyCoursesButton.addActionListener(listenForViewMyCoursesButton);
	}
	
	/**
	 * 
	 * @param listenForQuitButton
	 */
	public void addQuitButtonListener(ActionListener listenForQuitButton)
	{
		quitButton.addActionListener(listenForQuitButton);
	}
	
	public String getCourseNameAndNumberForSearchCatalogue()
	{		
		//Entering Information Panel For New Node
		JPanel searchCourse = new JPanel(new GridLayout(2, 2, 5, 0));
				
		JLabel enterCourseName = new JLabel("Enter the Course Name");
		JTextField courseNameResponse = new JTextField();
	
		JLabel enterCourseNum = new JLabel("Enter the Course Number");
		JTextField courseNumResponse = new JTextField();
		
		searchCourse.add(enterCourseName);
		searchCourse.add(courseNameResponse);
		searchCourse.add(enterCourseNum);
		searchCourse.add(courseNumResponse);

		int result = JOptionPane.showConfirmDialog(null, searchCourse, "Search the Course Catalogue", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		    if (result == JOptionPane.OK_OPTION) 
		    {
		    	String insertedCourseName = courseNameResponse.getText();
		    	String insertedCourseNum = courseNumResponse.getText();
		    	String fullCourseString = insertedCourseName + " " + insertedCourseNum;
		    	return fullCourseString;
		    }
		    
		return null;
	}
	
	public void showSearchedCatalogue(String returnedCourses) {

		JTextArea listOfCourseSections = new JTextArea(25, 40);
		listOfCourseSections.setText(returnedCourses);
		listOfCourseSections.setEditable(false);
		//listOfCourseSections.setBackground(Color.LIGHT_GRAY);
			
		JScrollPane foundCoursesScrollPanel = new JScrollPane(listOfCourseSections);
				
		JOptionPane.showMessageDialog(null,
				foundCoursesScrollPanel,
			    "Searched Course",
			    JOptionPane.PLAIN_MESSAGE);
	}
	
	public String getCourseNameAndNumberForAddAndRemoveCourse()
	{		
		//Entering Information Panel For New Node
		JPanel searchCourse = new JPanel(new GridLayout(3, 2, 5, 0));
				
		JLabel enterCourseName = new JLabel("Enter the Course Name");
		JTextField courseNameResponse = new JTextField();
	
		JLabel enterCourseNum = new JLabel("Enter the Course Number");
		JTextField courseNumResponse = new JTextField();
		
		JLabel enterSectionNum = new JLabel("Enter the Section Number");
		JTextField sectionNumResponse = new JTextField();
		
		searchCourse.add(enterCourseName);
		searchCourse.add(courseNameResponse);
		searchCourse.add(enterCourseNum);
		searchCourse.add(courseNumResponse);
		searchCourse.add(enterSectionNum);
		searchCourse.add(sectionNumResponse);


		
		int result = JOptionPane.showConfirmDialog(null, searchCourse, "Search the Course Catalogue", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		    if (result == JOptionPane.OK_OPTION) {
		    	String insertedCourseName = courseNameResponse.getText();
		    	String insertedCourseNum = courseNumResponse.getText();
		    	String insertedSectionNum = sectionNumResponse.getText();
		    	String fullCourseString = insertedCourseName + " " + insertedCourseNum + " " + insertedSectionNum;
		    	return fullCourseString;
		    }
		    
		return null;
	}

	public void showAddCourseOptionPane(String message) {
		JPanel addCourse = new JPanel(); 
		
		JOptionPane.showMessageDialog(addCourse,
				message,
			    "Add course",
			    JOptionPane.PLAIN_MESSAGE);	
	}
	
	public void showRemoveCourseOptionPane(String message) {
		JPanel removeCourse = new JPanel(); 
		
		JOptionPane.showMessageDialog(removeCourse,
				message,
			    "Remove course",
			    JOptionPane.PLAIN_MESSAGE);	
	}
}
