import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * The Class StudentRecordView is an implementation of the view for an application to maintain student records following MVC architecture
 * design. 
 * 
 * @author Aidan Johnson and Michele Piperni
 * @version 1.0
 * @since April 4, 2020
 *
 */
public class StudentRecordView extends JFrame
{
		//For the main panel (North)
		private JLabel title = new JLabel("An Application to Maintain Student Records");
			
		//For the Student Records panel (Center)
		private JTextArea studentRecords = new JTextArea();
		
		//For the input panel (South)
		private JButton insertButton = new JButton("Insert");
		private JButton findButton = new JButton("Find");
		private JButton browseButton = new JButton("Browse");
		private JButton createTreeButton = new JButton("Create Tree from File");
		
		//When inserting the file name
		private JLabel fileName = new JLabel("Enter the file name:");

		//When searching for a node by Student ID
		private JLabel studentID = new JLabel("Please enter the student's ID");
		
		//For Insert Node Function
		JButton insert2Button = new JButton("Insert");
		JButton returnButton = new JButton("Return to Main Window");	
		
		//Entering Information Panel For New Node
		JPanel newNode = new JPanel(new GridLayout(2, 2));
		
		JLabel insertTitle = new JLabel("Insert a New Node");
		
		JLabel enterId = new JLabel("Enter the Student's ID");
		JTextField id = new JTextField();
		
		JLabel enterFac = new JLabel("Enter faculty");
		JTextField fac = new JTextField();
		
		JLabel enterMajor = new JLabel("Enter Student's Major");
		JTextField major = new JTextField();
		
		JLabel enterYear = new JLabel("Enter Student's year");
		JTextField year = new JTextField();
		
	/**
	 * Constructing a StudentRecordView object that creates the main window GUI for the maintaining student record application.
	 * This main window has a label in the north panel, a textArea in the center panel and a 4 buttons representing insert, find,
	 * browse and create tree from panel on the south panel.
	 */
	public StudentRecordView()
	{
		super("Main window");
		
		//Making the JTextArea non-editable
		studentRecords.setEditable(false);
		
		//Creating three different panels for the main frame
		JPanel mainText = new JPanel();
		JPanel buttonPanel = new JPanel();
		JScrollPane textArea = new JScrollPane(studentRecords);
		
		//Setting the size of the frame
		setSize(600, 400);
		
		//If the frame closes the system exits on close
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mainText.add(title);
		
		//Adding the buttons to the button Panel
		buttonPanel.add(insertButton);
		buttonPanel.add(findButton);
		buttonPanel.add(browseButton);
		buttonPanel.add(createTreeButton);
		
		//Setting the size of the textArea
		textArea.setSize(600, 250);
		
		//Adding the title into the north panel
		add("North", mainText);
		
		//Adding the buttons into the south panel
		add("South", buttonPanel);
		
		//Adding the textArea into the center panel
		add("Center", textArea);
	}
	
	/**
	 * Sets the student records JTextArea to display a String message.
	 * 
	 * @param records the String to be displayed in the JTextArea.
	 */
	public void setStudentRecords(String records)
	{
		studentRecords.setText(records);
	}
	
	public Data getStudentInformation()
	{		
		newNode.add("North", insertTitle);
		
		newNode.add(enterId);
		newNode.add(id);
		newNode.add(enterFac);
		newNode.add(fac);
		newNode.add(enterMajor);
		newNode.add(major);
		newNode.add(enterYear);
		newNode.add(year);
		
		
		
		UIManager.put("OptionPane.okButtonText", "Insert");
		UIManager.put("OptionPane.cancelButtonText", "Return to Main Window");

		int result = JOptionPane.showConfirmDialog(null, newNode, "", JOptionPane.OK_CANCEL_OPTION);
		    if (result == JOptionPane.OK_OPTION) {
		    	String insertedId = id.getText();
		    	String insertedFaculty = fac.getText();
		    	String insertedMajor = major.getText();
		    	String insertedYear = year.getText();
		    	return new Data(insertedId, insertedFaculty,insertedMajor,insertedYear);
		    }
		    
		return null;
	}
	
	/**
	 * Gets the inputed file name from user input.
	 * 
	 * @returns the String representing the file name.
	 */
	public String getFileName()
	{
		return JOptionPane.showInputDialog(this, fileName);
	}
	
	/**
	 * Gets the student's ID from user input.
	 * 
	 * @returns the String representing the student's ID.
	 */
	public String getStudentID()
	{
		return JOptionPane.showInputDialog(studentID);
	}
	/**
	 * Displays a message.
	 * 
	 * @param message the string message to be displayed.
	 */
	public void displayMessage(String message)
	{
		JOptionPane.showMessageDialog(this, message);
	}
	
	
	//Button Listener Functions
	/**
	 * 
	 * @param listenForInsertButton
	 */
	public void addInsertButtonListener(ActionListener listenForInsertButton)
	{
		insertButton.addActionListener(listenForInsertButton);
	}
	
	/**
	 * 
	 * @param listenForBrowseButton
	 */
	public void addBrowseButtonListener(ActionListener listenForBrowseButton)
	{
		browseButton.addActionListener(listenForBrowseButton);
	}
	
	/**
	 * 
	 * @param listenForFindButton
	 */
	public void addFindButtonListener(ActionListener listenForFindButton)
	{
		findButton.addActionListener(listenForFindButton);
	}
	
	/**
	 * 
	 * @param listenForCreateTreeButton
	 */
	public void addCreateTreeButtonListener(ActionListener listenForCreateTreeButton)
	{
		createTreeButton.addActionListener(listenForCreateTreeButton);
	}
	
	public void addInsertButtonFromAddNodeListener(ActionListener listenForInsertAddNodeButton)
	{
		insert2Button.addActionListener(listenForInsertAddNodeButton);
	}
}
