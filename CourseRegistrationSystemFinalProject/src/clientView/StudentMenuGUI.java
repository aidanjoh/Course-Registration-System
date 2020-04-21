package clientView;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * JFrame for the Student menu. Contains a buttons for several functions specified
 * in Lab 3 assignment instructions.
 * 
 * @author Aidan Johnson and Michele Piperni
 * @version 1.0
 * @since April 15, 2020
 */
public class StudentMenuGUI extends JFrame
{
	/**
	 * Serial UID.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Title of the frame.
	 */
	private JLabel title = new JLabel("Student Menu");
	/**
	 * Label for the Different Options.
	 */
	private JLabel optionsTitle = new JLabel("Please Select One of the Following Options");
	/**
	 * Panel that contains all the buttons.
	 */
	private JPanel buttonsPanel = new JPanel();
	/**
	 * Search Catalogue Button.
	 */
	private JButton searchCatalogueButton = new JButton();
	/**
	 * Add course button.
	 */
	private JButton addCourseButton = new JButton();
	/**
	 * Remove course button.
	 */
	private JButton removeCourseButton = new JButton();
	/**
	 * View catalogue button.
	 */
	private JButton viewCatalogueButton = new JButton();
	/**
	 * View my courses button.
	 */
	private JButton viewMyCoursesButton = new JButton();
	/**
	 * Quit button.
	 */
	private JButton quitButton = new JButton("Quit");
	/**
	 * Logout button.
	 */
	private JButton logoutButton = new JButton("Logout");
	/**
	 * Current user label.
	 */
	private JLabel currentLogin = new JLabel();
	
	/**
	 * Constructor for the StudentMenuGUI Class. Calls several function to 
	 * customize and initialize the frame.
	 */
	public StudentMenuGUI()
	{
		super("Course Registration Student Menu");		
		this.setFrameSizeAndLayout();
		this.makeButtonPanel();
		this.setAlignments();
		this.setFontOptions();
		this.setButtonColors();
		this.addToFrame();
	}
	
	/**
	 * Sets size, layout and default close operation.
	 */
	private void setFrameSizeAndLayout()
	{
		this.setSize(600, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BoxLayout boxLayout = new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS); // top to bottom
	    this.setLayout(boxLayout);
	}
	
	/**
	 * Initializes the button panel.
	 */
	private void makeButtonPanel() 
	{
		buttonsPanel.setLayout(new GridLayout(2, 5, 10, 20)); //the second two arguments are the horizontal and vertical gaps
		
		//wraps the text
		addCourseButton.setText("<html><center>"+"Add a"+"<br>"+"Course"+"</center></html>");
		searchCatalogueButton.setText("<html><center>"+"Search the Course"+"<br>"+"Catalogue"+"</center></html>");
		viewCatalogueButton.setText("<html><center>"+"View Courses in"+"<br>"+"the Catalogue"+"</center></html>");
		removeCourseButton.setText("<html><center>"+"Remove a"+"<br>"+"Course"+"</center></html>");
		viewMyCoursesButton.setText("<html><center>"+"View my"+"<br>"+"Courses"+"</center></html>");
		
		//Whitespace added to center the buttons.
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
	
	/**
	 * Aligns the panels and labels. All center justified.
	 */
	private void setAlignments() 
	{
	    title.setAlignmentX( Component.CENTER_ALIGNMENT );
	    optionsTitle.setAlignmentX( Component.CENTER_ALIGNMENT );
	    logoutButton.setAlignmentX( Component.CENTER_ALIGNMENT );
	    currentLogin.setAlignmentX( Component.CENTER_ALIGNMENT );
	}
	
	/**
	 * Sets font options.
	 */
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
	
	/**
	 * Sets the button colors.
	 */
	private void setButtonColors() 
	{
		Color lightBlue = new Color(51,194,255);
		searchCatalogueButton.setBackground(lightBlue);
		addCourseButton.setBackground(lightBlue);
		removeCourseButton.setBackground(lightBlue);
		viewCatalogueButton.setBackground(lightBlue);
		viewMyCoursesButton.setBackground(lightBlue);
		quitButton.setBackground(lightBlue);
		logoutButton.setBackground(lightBlue);
	}
	
	/**
	 * Adds all the components to the frame. Rigid text area is for
	 * whitespace.
	 */
	private void addToFrame() 
	{
		this.add(title);
		this.add(Box.createRigidArea(new Dimension(0, 15)));
		this.add(optionsTitle);
		this.add(Box.createRigidArea(new Dimension(0, 10)));
		this.add(buttonsPanel);
		this.add(Box.createRigidArea(new Dimension(0, 30)));
		this.add(logoutButton);
		this.add(Box.createRigidArea(new Dimension(0, 5)));
		this.add(currentLogin);
		this.add(Box.createRigidArea(new Dimension(0, 15)));
	}
	
	//---------------- Getters And Setters for buttons --------------------------//

	/**
	 * Returns the searchCatalogueButton.
	 * 
	 * @return searchCatalogueButton
	 */
	public JButton getSearchCatalogueButton() {
		return searchCatalogueButton;
	}

	/**
	 * Returns the addCourseButton.
	 * 
	 * @return addCourseButton
	 */
	public JButton getAddCourseButton() {
		return addCourseButton;
	}

	/**
	 * Returns the removeCourseButton.
	 * 
	 * @return removeCourseButton
	 */
	public JButton getRemoveCourseButton() {
		return removeCourseButton;
	}

	/**
	 * Returns the viewCatalogueButton.
	 * 
	 * @return viewCatalogueButton
	 */
	public JButton getViewCatalogueButton() {
		return viewCatalogueButton;
	}

	/**
	 * Returns the viewMyCoursesButton.
	 * 
	 * @return viewMyCoursesButton
	 */
	public JButton getViewMyCoursesButton() {
		return viewMyCoursesButton;
	}

	/**
	 * Returns the quitButton.
	 * 
	 * @return quitButton
	 */
	public JButton getQuitButton() {
		return quitButton;
	}
	
	/**
	 * Returns the logoutButton.
	 * 
	 * @return logoutButton
	 */
	public JButton getLogoutButton() {
		return logoutButton;
	}
	
	/**
	 * Sets the current user JLabel.
	 * 
	 * @param nameAndID The name and id of the current user.
	 */
	public void setCurrentLogin(String nameAndID) {
		currentLogin.setText(nameAndID);
	}
	
	//----------------Button Listener Methods--------------------------//
	
	/**
	 * Adds the button listener for the searchCatalogueButton.
	 * 
	 * @param listenForSearchCatalogueButton
	 */
	public void addSearchCatalogueButtonListener(ActionListener listenForSearchCatalogueButton)
	{
		searchCatalogueButton.addActionListener(listenForSearchCatalogueButton);
	}
	
	/**
	 * Adds the button listener for the addCourseButton.
	 * 
	 * @param listenForAddCourseButton
	 */
	public void addAddCourseButtonListener(ActionListener listenForAddCourseButton)
	{
		addCourseButton.addActionListener(listenForAddCourseButton);
	}
	
	/**
	 * Adds the button listener for the removeCourseButton.
	 * 
	 * @param listenForRemoveCourseButton
	 */
	public void addRemoveCourseButtonListener(ActionListener listenForRemoveCourseButton)
	{
		removeCourseButton.addActionListener(listenForRemoveCourseButton);
	}
	
	/**
	 * Adds the button listener for the viewCatalogueButton.
	 * 
	 * @param listenForViewCatalogueButton
	 */
	public void addViewCatalogueButtonListener(ActionListener listenForViewCatalogueButton)
	{
		viewCatalogueButton.addActionListener(listenForViewCatalogueButton);
	}
	
	/**
	 * Adds the button listener for the viewMyCoursesButton.
	 * 
	 * @param listenForViewMyCoursesButton
	 */
	public void addViewMyCoursesButtonListener(ActionListener listenForViewMyCoursesButton)
	{
		viewMyCoursesButton.addActionListener(listenForViewMyCoursesButton);
	}
	
	/**
	 * Adds the button listener for the quitButton.
	 * 
	 * @param listenForQuitButton
	 */
	public void addQuitButtonListener(ActionListener listenForQuitButton)
	{
		quitButton.addActionListener(listenForQuitButton);
	}
	
	/**
	 * Adds the button listener for the logoutButton.
	 * 
	 * @param listenForLogoutButton
	 */
	public void addLogoutButtonListener(ActionListener listenForLogoutButton)
	{
		logoutButton.addActionListener(listenForLogoutButton);
	}
	
	//--------------------------- JOptionPane ---------------------------------//
	
	/**
	 * Options Panel for searching a course in the course catalogue. Has text fields for
	 * course name and number.
	 * 
	 * @return A string containing the course name and number.
	 */
	public String getCourseNameAndNumberForSearchCatalogue()
	{		
		//Entering Information Panel For New Course
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
		    	
		    	//Making sure all the fields are filled out before returning
		    	if(insertedCourseName.equals("") || insertedCourseNum.equals("")) {
		    		showEmptyStringError();
					return getCourseNameAndNumberForSearchCatalogue();
		    	}
		    	
		    	//Make sure all the required fields have integers
		    	boolean isInteger = isStringInteger(insertedCourseNum);
				if(!isInteger) {
					showIntegerError();
					return getCourseNameAndNumberForSearchCatalogue();
		    	}
		    	
		    	String fullCourseString = insertedCourseName + " " + insertedCourseNum;
		    	return fullCourseString;
		    }
		    
		return null; //Returns null if cancel is pressed
	}
	
	/**
	 * Option Panel containing searched course.
	 * 
	 * @param returnedCourses The courses which were searched.
	 */
	public void showSearchedCatalogue(String returnedCourses) {

		JTextArea listOfCourseSections = new JTextArea(10, 20);
		listOfCourseSections.setText(returnedCourses);
		listOfCourseSections.setEditable(false);
		
		JScrollPane foundCoursesScrollPanel = new JScrollPane(listOfCourseSections);
				
		JOptionPane.showMessageDialog(null,
				foundCoursesScrollPanel,
			    "Searched Course",
			    JOptionPane.PLAIN_MESSAGE);
	}
	
	/**
	 * Options Panel for adding or removing a course from student courses. 
	 * Has text fields for course name, number and seciton number.
	 * 
	 * @return A string containing the course name, number and section.
	 */
	public String getCourseNameAndNumberForAddAndRemoveCourse()
	{		
		//Entering Information Panel For New Course
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
		
		int result = JOptionPane.showConfirmDialog(null, searchCourse, "Student Course", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		    if (result == JOptionPane.OK_OPTION) {
		    	String insertedCourseName = courseNameResponse.getText();
		    	String insertedCourseNum = courseNumResponse.getText();
		    	String insertedSectionNum = sectionNumResponse.getText();
		    	
		    	//Making sure all the fields are filled out before returning
		    	if(insertedCourseName.equals("") || insertedCourseNum.equals("") || insertedSectionNum.equals("")) {
		    		showEmptyStringError();
					return getCourseNameAndNumberForAddAndRemoveCourse();
		    	}
		    	
		    	//Make sure all the required fields have integers
		    	boolean isInteger = isStringInteger(insertedCourseNum);
		    	boolean isInteger2 = isStringInteger(insertedSectionNum);
				
		    	if(!isInteger || !isInteger2) {
					showIntegerError();
					return getCourseNameAndNumberForAddAndRemoveCourse();
		    	}
		    	
		    	String fullCourseString = insertedCourseName + " " + insertedCourseNum + " " + insertedSectionNum;
		    	return fullCourseString;
		    }

		return null; //Returns null if cancel is pressed
	}

	/**
	 * Message panel containing the message after adding a course.
	 * 
	 * @param message
	 */
	public void showAddCourseOptionPane(String message) {
		JPanel addCourse = new JPanel(); 
		
		JOptionPane.showMessageDialog(addCourse,
				message,
			    "Add Course",
			    JOptionPane.PLAIN_MESSAGE);	
	}
	
	/**
	 * Message panel containing the message after removing a course.
	 * 
	 * @param message
	 */
	public void showRemoveCourseOptionPane(String message) {
		JPanel removeCourse = new JPanel(); 
		
		JOptionPane.showMessageDialog(removeCourse,
				message,
			    "Remove Course",
			    JOptionPane.PLAIN_MESSAGE);	
	}
		
	/**
	 * Option Panel showing an error message for non integer string.
	 */
	public void showIntegerError() {
		JPanel numberError = new JPanel(); 
		
		JOptionPane.showMessageDialog(numberError,
			    "Please enter an Integer for Required Fields!",
			    "Error Message",
			    JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * Option Panel showing an error message for a empty string.
	 */
	public void showEmptyStringError() {
		JPanel emptyStringError = new JPanel(); 
		
		JOptionPane.showMessageDialog(emptyStringError,
			    "Please fill out all fields",
			    "Error Message",
			    JOptionPane.ERROR_MESSAGE);
	}
	
	//---------------- Helper Methods --------------------------//
	
	/**
	 * Checks if the string is an integer.
	 * 
	 * @param number The string to be checked.
	 * @return true if the string is an integer.
	 */
	public static boolean isStringInteger(String number){
	    try{
	        Integer.parseInt(number);
	    }catch(Exception e ){
	        return false;
	    }
	    return true;
	}	
}
