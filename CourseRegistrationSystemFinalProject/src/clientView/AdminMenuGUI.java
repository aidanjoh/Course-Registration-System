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
import javax.swing.JTextField;

/**
 * JFrame for the Admin menu. Contains 4 different JButtons, view catalogue,
 * add a course to the catalogue, quit, and logout.
 * 
 * @author Aidan Johnson and Michele Piperni
 * @version 1.0
 * @since April 15, 2020
 */
public class AdminMenuGUI extends JFrame{
	
	/**
	 * Serial UID.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Title of the frame.
	 */
	private JLabel title = new JLabel("Admin Menu");
	/**
	 * Label for the Different Options.
	 */
	private JLabel optionsTitle = new JLabel("Please Select One of the Following Options");
	/**
	 * Panel that contains all the buttons.
	 */
	private JPanel buttonsPanel = new JPanel();
	/**
	 * View catalogue button.
	 */
	private JButton viewCatalogueButton = new JButton();
	/**
	 * Add course button.
	 */
	private JButton addCoursesToCatalogueButton = new JButton();
	/**
	 * Quit button.
	 */
	private JButton quitButton = new JButton("Quit");
	/**
	 * Logout button.
	 */
	private JButton logoutButton = new JButton("Logout");
	/**
	 * Current Login Label. Contains the current user logged in.
	 */
	private JLabel currentLogin = new JLabel();
	
	/**
	 * Constructor for the AdminMenuGUI Class. Calls several function to 
	 * customize and initialize the frame.
	 */
	public AdminMenuGUI()
	{
		super("Course Registration Admin Menu");
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
		this.setSize(600, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BoxLayout boxLayout = new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS); // top to bottom
	    this.setLayout(boxLayout);
	}
	
	/**
	 * Initializes the button panel.
	 */
	private void makeButtonPanel() 
	{
		buttonsPanel.setLayout(new GridLayout(1, 5, 10, 20)); //the second two arguments are the horizontal and vertical gaps
		
		//wraps the text
		viewCatalogueButton.setText("<html><center>"+"View Courses in"+"<br>"+"the Catalogue"+"</center></html>");
		addCoursesToCatalogueButton.setText("<html><center>"+"Add a Courses to"+"<br>"+"the Catalogue"+"</center></html>");
	
		//Whitespace added to center the buttons
		JLabel whiteSpace = new JLabel("  ");
		JLabel whiteSpace1 = new JLabel("  ");
		
		buttonsPanel.add(whiteSpace);
		buttonsPanel.add(viewCatalogueButton);
		buttonsPanel.add(addCoursesToCatalogueButton);
		buttonsPanel.add(quitButton);
		buttonsPanel.add(whiteSpace1);
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
		viewCatalogueButton.setFont(new Font("Dialog", Font.PLAIN, 12));
		addCoursesToCatalogueButton.setFont(new Font("Dialog", Font.PLAIN, 12));
		quitButton.setFont(new Font("Dialog", Font.PLAIN, 12));
	}
	
	/**
	 * Sets the button colors.
	 */
	private void setButtonColors() 
	{
		Color lightBlue = new Color(51,194,255);
		viewCatalogueButton.setBackground(lightBlue);
		addCoursesToCatalogueButton.setBackground(lightBlue);
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
	
	//---------------- Getters And Setters --------------------------//

	/**
	 * Returns the viewCatalogueButton.
	 * 
	 * @return viewCatalogueButton
	 */
	public JButton getViewCatalogueButton() {
		return viewCatalogueButton;
	}

	/**
	 * Returns the addCoursesToCatalogueButton.
	 * 
	 * @return addCoursesToCatalogueButton
	 */
	public JButton getAddCoursesToCatalogueButton() {
		return addCoursesToCatalogueButton;
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
	 * Adds the button listener for the viewCatalogueButton.
	 * 
	 * @param listenForViewCatalogueButton
	 */
	public void addViewCatalogueButtonListener(ActionListener listenForViewCatalogueButton)
	{
		viewCatalogueButton.addActionListener(listenForViewCatalogueButton);
	}
	
	/**
	 * Adds the button listener for the addCoursesToCatalogueButton.
	 * 
	 * @param listenForAddCoursesToCatalogueButton
	 */
	public void addAddCoursesToCatalogueButtonListener(ActionListener listenForAddCoursesToCatalogueButton)
	{
		addCoursesToCatalogueButton.addActionListener(listenForAddCoursesToCatalogueButton);
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
	
	//----------------  JOPtionPane  --------------------------//
	
	/**
	 * Options Panel for adding a course to the course catalogue. Has text fields for
	 * name, number, number of sections and max section size.
	 * 
	 * @return A string containing the name, number of sections and max section size.
	 */
	public String getCourseNameNumberSecCapForSearchCatalogue() {
		
		//Entering Information Panel For New Course
		JPanel addCourse = new JPanel(new GridLayout(4, 2, 5, 0));
				
		JLabel enterCourseName = new JLabel("Enter the New Course Name");
		JTextField courseNameResponse = new JTextField();
		JLabel enterCourseNum = new JLabel("Enter the New Course Number");
		JTextField courseNumResponse = new JTextField();
		JLabel enterCourseSecNum = new JLabel("Enter the Number of Course Sections");
		JTextField enterCourseSecNumResponse = new JTextField();
		JLabel enterCourseSectionCapNum = new JLabel("Enter the Max Capacity of each Section");
		JTextField enterCourseSectionCapNumResponse = new JTextField();
		
		addCourse.add(enterCourseName);
		addCourse.add(courseNameResponse);
		addCourse.add(enterCourseNum);
		addCourse.add(courseNumResponse);
		addCourse.add(enterCourseSecNum);
		addCourse.add(enterCourseSecNumResponse);
		addCourse.add(enterCourseSectionCapNum);
		addCourse.add(enterCourseSectionCapNumResponse);

		int result = JOptionPane.showConfirmDialog(null, addCourse, "Add to Course Catalogue", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		    if (result == JOptionPane.OK_OPTION) 
		    {
		    	//Checks the text fields
		    	String insertedCourseName = courseNameResponse.getText();
		    	String insertedCourseNum = courseNumResponse.getText();
		    	String insertedCourseSectionsNum = enterCourseSecNumResponse.getText();
		    	String insertedCourseCapacityNum = enterCourseSectionCapNumResponse.getText();
		    	
		    	//Making sure all the fields are filled out before returning
		    	if(insertedCourseName.equals("") || insertedCourseNum.equals("") || insertedCourseSectionsNum.equals("") || insertedCourseCapacityNum.equals("")) {
		    		showEmptyStringError();
					return getCourseNameNumberSecCapForSearchCatalogue(); 
		    	}
		    	
		    	//Make sure all the required fields have integers
		    	boolean isInteger = isStringInteger(insertedCourseNum);
		    	boolean isInteger2 = isStringInteger(insertedCourseSectionsNum);
		    	boolean isInteger3 = isStringInteger(insertedCourseCapacityNum);
				
		    	if(!isInteger || !isInteger2 || !isInteger3) {
					showIntegerError();
					return getCourseNameNumberSecCapForSearchCatalogue();
		    	}
		    	
		    	String fullCourseString = insertedCourseName + " " + insertedCourseNum + " " + insertedCourseSectionsNum + " " + insertedCourseCapacityNum;
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
	 * Option Panel showing an error message for a empty string.
	 */
	public void showEmptyStringError() {
		JPanel emptyStringError = new JPanel(); 
		
		JOptionPane.showMessageDialog(emptyStringError,
			    "Please fill out all fields",
			    "Error Message",
			    JOptionPane.ERROR_MESSAGE);
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
