package clientView;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 * JFrame for the StartUp menu. Contains a buttons for admin or student login,
 * login button, and text field for username and password.
 * 
 * @author Aidan Johnson and Michele Piperni
 * @version 1.0
 * @since April 15, 2020
 */
public class StartUpMenuGUI extends JFrame{

	/**
	 * Serial UID.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Title of the frame.
	 */
	private JLabel title = new JLabel("Welcome to the Course Registration System");
	/**
	 * Label for the Different Login Options.
	 */
	private JLabel radioButtonTitle = new JLabel("Login as:");
	/**
	 * Panel for the radio buttons.
	 */
	private JPanel radioButtonsPanel = new JPanel();
	/**
	 * Button group for the radio buttons.
	 */
	private ButtonGroup loginChoies = new ButtonGroup();
	/**
	 * Student radio button.
	 */
	private JRadioButton existingStudent = new JRadioButton("Student                  ", true); //The true is to make it the default selction
	/**
	 * Admin radio button.
	 */
	private JRadioButton admin = new JRadioButton("Admin                    "); //The spaces are to extend the color.
	/**
	 * Label for UCID field.
	 */
	private JLabel ucid = new JLabel("UCID");
	/**
	 * Label for Password field.
	 */
	private JLabel password = new JLabel("Password");
	/**
	 * Text field for UCID.
	 */
	private JTextField ucidResponse = new JTextField();
	/**
	 * Text field for password. Password Field to hide letters.
	 */
	private JPasswordField  passwordResponse = new JPasswordField();
	/**
	 * Login Button.
	 */
	private JButton loginButton = new JButton("Login");
	
	/**
	 * Constructor for the StartUpMenuGUI Class. Calls several function to 
	 * customize and initialize the frame.
	 */
	public StartUpMenuGUI()
	{
		super("Course Registration Start Menu");	
		this.setFrameSizeAndLayout();
		this.setFontOptions();
	    this.makeButtonPanel();
	    this.setButtonColors();   
	    this.setTextBoxSize();
	    this.setAlignments();
	    this.addToFrame();
	}
	
	/**
	 * Sets size, layout and default close operation.
	 */
	private void setFrameSizeAndLayout(){
		this.setSize(600, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BoxLayout boxLayout = new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS); // top to bottom
	    this.setLayout(boxLayout);
	}
	
	/**
	 * Sets font options.
	 */
	private void setFontOptions() {
		title.setFont(new Font("Dialog", Font.BOLD, 24)); 
	    radioButtonTitle.setForeground(Color.darkGray);
	    radioButtonTitle.setFont(new Font("Dialog", Font.BOLD, 20));
	    ucid.setFont(new Font("Dialog", Font.BOLD, 14));
	    password.setFont(new Font("Dialog", Font.BOLD, 14));
	}
	
	/**
	 * Initializes the radio button panel.
	 */
	private void makeButtonPanel() {
		radioButtonsPanel.setLayout(new BoxLayout(radioButtonsPanel, BoxLayout.Y_AXIS)); //Set the Panel layout to box layout
		
		radioButtonsPanel.add(existingStudent);
		radioButtonsPanel.add(Box.createRigidArea(new Dimension(0, 3)));
		radioButtonsPanel.add(admin);
		radioButtonsPanel.add(Box.createRigidArea(new Dimension(0, 3)));
		
		//This adds all the radio buttons to the same group so that only one can be selected at once
		loginChoies.add(existingStudent);
		loginChoies.add(admin);
	}
	
	/**
	 * Sets the button colors.
	 */
	private void setButtonColors() {
		Color lightBlue = new Color(51,194,255);
	    existingStudent.setBackground(lightBlue);
	    admin.setBackground(lightBlue);
	    loginButton.setBackground(lightBlue);
	}
	
	/**
	 * Aligns the panels and labels. All center justified.
	 */
	private void setAlignments() {
	    title.setAlignmentX( Component.CENTER_ALIGNMENT );
	    radioButtonTitle.setAlignmentX( Component.CENTER_ALIGNMENT );
		radioButtonsPanel.setAlignmentX( Component.CENTER_ALIGNMENT );
		ucid.setAlignmentX( Component.CENTER_ALIGNMENT );
		password.setAlignmentX( Component.CENTER_ALIGNMENT );
		loginButton.setAlignmentX( Component.CENTER_ALIGNMENT );
	}
	
	/**
	 * Sets the text box sizes.
	 */
	private void setTextBoxSize() {
		ucidResponse.setSize(200, 30);
	    ucidResponse.setPreferredSize(ucidResponse.getSize());
	    ucidResponse.setMaximumSize(ucidResponse.getPreferredSize() );  
	    passwordResponse.setSize(200, 30);
	    passwordResponse.setPreferredSize(ucidResponse.getSize());
	    passwordResponse.setMaximumSize(ucidResponse.getPreferredSize() );
	}
	
	/**
	 * Adds all the components to the frame. Rigid text area is for
	 * whitespace.
	 */
	private void addToFrame() {
		this.add(title);
		this.add(Box.createRigidArea(new Dimension(0, 15)));
		this.add(radioButtonTitle);
		this.add(radioButtonsPanel);
		this.add(Box.createRigidArea(new Dimension(0, 30)));
		this.add(ucid);
		this.add(ucidResponse);
		this.add(password);
		this.add(passwordResponse);
		this.add(Box.createRigidArea(new Dimension(0, 30)));
		this.add(loginButton);
	}
	
	
	//---------------- Getters And Setters --------------------------//
	
	/**
	 * Returns the loginButton.
	 * 
	 * @return loginButton
	 */
	public JButton getLoginButton() {
		return loginButton;
	}
	
	/**
	 * Returns the existingStudent Radio Button.
	 * 
	 * @return existingStudent
	 */
	public JRadioButton getExistingStudent() {
		return existingStudent;
	}

	/**
	 * Returns the admin radio button.
	 * 
	 * @return admin
	 */
	public JRadioButton getAdmin() {
		return admin;
	}
	
	/**
	 * Returns the text in the password field.
	 * 
	 * @return password
	 */
	public String getPassword() {
		@SuppressWarnings("deprecation")
		String password = passwordResponse.getText();		
		return password;
	}
	
	/**
	 * Returns the UCID in the text field.
	 * 
	 * @return loginButton
	 */
	public int getUCID() {
	
		String stringUCID = ucidResponse.getText();
		int ucidParsed = 0;
		
		//This checks if its blank
		if(stringUCID.contentEquals("")) {
			return 0;
		}
		
		//This checks to make sure its an integer
		try {
			ucidParsed = Integer.parseInt(stringUCID);
		}catch(NumberFormatException e) {
			showInvalidUCID();
			return 0;
		}
		
		return ucidParsed;
	}
	
	/**
	 * Sets both text fields to blank.
	 */
	public void resetTextFields() {
	     ucidResponse.setText("");
	     passwordResponse.setText("");
	}
	
	//----------------  JOPtionPane  --------------------------//
	
	/**
	 * Option Panel showing an error message for incorrect combination of UCID and password.
	 */
	public void showInvalidPasswordAndUCID() {
		JPanel passwordError = new JPanel(); 
		
		JOptionPane.showMessageDialog(passwordError,
			    "Incorrect UCID or Password",
			    "Error Message",
			    JOptionPane.ERROR_MESSAGE);
		
	}
	
	/**
	 * Option Panel showing an error message for non integer UCID.
	 */
	public void showInvalidUCID() {
		JPanel numberError = new JPanel(); 
		
		JOptionPane.showMessageDialog(numberError,
			    "Please enter a valid UCID",
			    "Error Message",
			    JOptionPane.ERROR_MESSAGE);
		
	}
		
	//----------------Button Listener Methods--------------------------//
	
	/**
	 * Adds the action listener for the Login button.
	 * 
	 * @param listenForLoginButton
	 */
	public void addLoginButtonListener(ActionListener listenForLoginButton)
	{
		loginButton.addActionListener(listenForLoginButton);
	}
}
