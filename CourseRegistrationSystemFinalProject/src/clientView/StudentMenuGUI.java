package clientView;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;


import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StudentMenuGUI extends JFrame
{

	private JLabel title = new JLabel("Student Menu");
	
	private JLabel optionsTitle = new JLabel("Please Select One of the Following Options");
	
	private JLabel blankSpace1 = new JLabel(" ");
	private JLabel blankSpace2 = new JLabel(" ");
	
	private JPanel buttonsPanel = new JPanel();
	private JButton searchCatalogueButton = new JButton();
	private JButton addCourseButton = new JButton("Add a Course");
	private JButton removeCourseButton = new JButton("Remove a Course");
	private JButton viewCatalogueButton = new JButton();
	private JButton viewMyCoursesButton = new JButton("View my Courses");
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
	
	private void addToFrame() 
	{
		this.add(title);
		this.add(blankSpace1);
		this.add(optionsTitle);
		this.add(blankSpace2);
		this.add(buttonsPanel);
	}

	private void setFrameSizeAndLayout()
	{
		this.setSize(600, 400);
		
		BoxLayout boxLayout = new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS); // top to bottom
	    this.setLayout(boxLayout);
	}
	
	private void makeButtonPanel() 
	{
		buttonsPanel.setLayout(new GridLayout(2, 3, 40, 30)); //the second two arguments are the horizontal and vertical gaps
		
		//wraps the text
		searchCatalogueButton.setText("<html><center>"+"Search the Course"+"<br>"+"Catalogue"+"</center></html>");
		viewCatalogueButton.setText("<html><center>"+"View Courses in"+"<br>"+"the Catalogue"+"</center></html>");
		
		buttonsPanel.add(searchCatalogueButton);
		buttonsPanel.add(addCourseButton);
		buttonsPanel.add(removeCourseButton);
		buttonsPanel.add(viewCatalogueButton);
		buttonsPanel.add(viewMyCoursesButton);
		buttonsPanel.add(quitButton);
	}
	
	private void setAlignments() 
	{
	    title.setAlignmentX( Component.CENTER_ALIGNMENT );
	    optionsTitle.setAlignmentX( Component.CENTER_ALIGNMENT );
	}
	
	private void setFontOptions() 
	{
		title.setFont(new Font("Dialog", Font.BOLD, 24)); 
		blankSpace1.setFont(new Font("Dialog", Font.BOLD, 10));
		blankSpace2.setFont(new Font("Dialog", Font.BOLD, 20));
		optionsTitle.setForeground(Color.darkGray);
		optionsTitle.setFont(new Font("Dialog", Font.BOLD, 20));
		
		searchCatalogueButton.setFont(new Font("Dialog", Font.PLAIN, 16));
		addCourseButton.setFont(new Font("Dialog", Font.PLAIN, 16));
		removeCourseButton.setFont(new Font("Dialog", Font.PLAIN, 16));
		viewCatalogueButton.setFont(new Font("Dialog", Font.PLAIN, 16));
		viewMyCoursesButton.setFont(new Font("Dialog", Font.PLAIN, 16));
		quitButton.setFont(new Font("Dialog", Font.PLAIN, 16));
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
	
	public static void main(String[] args) 
	{
		StudentMenuGUI view = new StudentMenuGUI();
		view.setVisible(true);
	}
}
