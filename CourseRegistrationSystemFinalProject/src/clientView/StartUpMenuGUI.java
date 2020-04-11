package clientView;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class StartUpMenuGUI extends JFrame{

	private JLabel title = new JLabel("Welcome to the Course Registration Systems");
	
	private JLabel radioButtonTitle = new JLabel("Login as:");
	
	private JPanel radioButtonsPanel = new JPanel();
	private ButtonGroup loginChoies = new ButtonGroup();
	private JRadioButton existingStudent = new JRadioButton("Existing Student ");
	private JRadioButton admin = new JRadioButton("Admin                    ");
	private JRadioButton newStudent = new JRadioButton("New Student        ");
	
	private JLabel ucid = new JLabel("UCID");
	private JLabel password = new JLabel("Password");
	private JTextField ucidResponse = new JTextField();
	private JTextField passwordResponse = new JTextField();
	private JButton loginButton = new JButton("Login");
	
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
	
	private void setFrameSizeAndLayout(){
		this.setSize(600, 400);
		
		BoxLayout boxLayout = new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS); // top to bottom
	    this.setLayout(boxLayout);
	}
	
	private void setFontOptions() {
		title.setFont(new Font("Dialog", Font.BOLD, 24)); 
	    radioButtonTitle.setForeground(Color.darkGray);
	    radioButtonTitle.setFont(new Font("Dialog", Font.BOLD, 20));
	    ucid.setFont(new Font("Dialog", Font.BOLD, 14));
	    password.setFont(new Font("Dialog", Font.BOLD, 14));
	}
	
	private void makeButtonPanel() {
		radioButtonsPanel.setLayout(new BoxLayout(radioButtonsPanel, BoxLayout.Y_AXIS)); //Set the Panel layout to box layout
		
		radioButtonsPanel.add(existingStudent);
		radioButtonsPanel.add(Box.createRigidArea(new Dimension(0, 3)));
		radioButtonsPanel.add(admin);
		radioButtonsPanel.add(Box.createRigidArea(new Dimension(0, 3)));
		radioButtonsPanel.add(newStudent);
		
		//This adds all the radio buttons to the same group so that only one can be selected at once
		loginChoies.add(existingStudent);
		loginChoies.add(admin);
		loginChoies.add(newStudent);
	}
	
	private void setButtonColors() {
		Color lightBlue = new Color(51,194,255);
	    existingStudent.setBackground(lightBlue);
	    admin.setBackground(lightBlue);
	    newStudent.setBackground(lightBlue);
	    loginButton.setBackground(lightBlue);
	}
	
	private void setAlignments() {
	    title.setAlignmentX( Component.CENTER_ALIGNMENT );
	    radioButtonTitle.setAlignmentX( Component.CENTER_ALIGNMENT );
		radioButtonsPanel.setAlignmentX( Component.CENTER_ALIGNMENT );
		ucid.setAlignmentX( Component.CENTER_ALIGNMENT );
		password.setAlignmentX( Component.CENTER_ALIGNMENT );
		loginButton.setAlignmentX( Component.CENTER_ALIGNMENT );
	}
	
	private void setTextBoxSize() {
		ucidResponse.setSize(200, 30);
	    ucidResponse.setPreferredSize(ucidResponse.getSize());
	    ucidResponse.setMaximumSize(ucidResponse.getPreferredSize() );
	    
	    passwordResponse.setSize(200, 30);
	    passwordResponse.setPreferredSize(ucidResponse.getSize());
	    passwordResponse.setMaximumSize(ucidResponse.getPreferredSize() );
	}
	
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
	
	public int getUCID() {
		//ucid.
		return 0;
	}

	//This is just to test right now
	public static void main(String[] args)
	{
		StartUpMenuGUI view = new StartUpMenuGUI();
		view.setVisible(true);
	}
}
