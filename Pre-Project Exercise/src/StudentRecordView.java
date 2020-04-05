import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * 
 * @author Aidan Johnson and Michele Piperni
 * @version 1.0
 * @since April 4, 2020
 *
 */
public class StudentRecordView extends JFrame
{
	//For the main panel
	private JLabel title = new JLabel("An Application to Maintain Student Records");
	
	private JButton insertButton = new JButton("Insert");
	private JButton findButton = new JButton("Find");
	private JButton browseButton = new JButton("Browse");
	private JButton createTreeButton = new JButton("Create Tree from File");
	
	private JTextArea studentRecords = new JTextArea();
	
	//For the input panel
	private JLabel fileName = new JLabel("Enter the file name:");

	private JLabel studentID = new JLabel("Please enter the student's ID");
	
	private JLabel ID = new JLabel("Enter the Student's ID");
	
	private JLabel faculty = new JLabel("Enter Faculty");
	
	private JLabel major = new JLabel("Enter Student's Major");
	
	private JLabel year = new JLabel("Enter year");
	
	/**
	 * 
	 */
	public StudentRecordView()
	{
		super("Main window");
		
		JPanel mainText = new JPanel();
		JPanel buttonPanel = new JPanel();
		JScrollPane textArea = new JScrollPane(studentRecords);
		
		setSize(600, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mainText.setLocation(0, 0);
		mainText.setSize(600, 50);
		mainText.add(title);
		
		buttonPanel.setSize(600, 50);
		buttonPanel.setLocation(0, 350);
		buttonPanel.add(insertButton);
		buttonPanel.add(findButton);
		buttonPanel.add(browseButton);
		buttonPanel.add(createTreeButton);
		
		textArea.setSize(600, 250);
		textArea.setLocation(0, 50);
		
		add("North", mainText);
		add("South", buttonPanel);
		add("Center", textArea);
	}
	
	/**
	 * 
	 * @param records
	 */
	public void setStudentRecords(String records)
	{
		studentRecords.setText(records);
	}
	
	public String getStudentInformation()
	{
		JPanel newNode = new JPanel(new GridLayout(0, 1, 2, 2));
		
		JLabel enterId = new JLabel("Enter the Student's ID");
		JTextField id = new JTextField();
		
		JLabel enterFac = new JLabel("Enter faculty");
		JTextField fac = new JTextField();
		
		JLabel enterMajor = new JLabel("Enter Student's Major");
		JTextField major = new JTextField();
		
		JLabel enterYear = new JLabel("Enter Student's year");
		JTextField year = new JTextField();
		
		JButton insert2Button = new JButton("Insert");
		JButton returnButton = new JButton("Return to Main Window");
		
		newNode.add(enterId);
		newNode.add(id);
		newNode.add(enterFac);
		newNode.add(fac);
		newNode.add(enterMajor);
		newNode.add(major);
		newNode.add(enterYear);
		newNode.add(year);
		newNode.add(insert2Button);
		newNode.add(returnButton);
		
		JFrame frame = new JFrame();
		frame.add(newNode);
		frame.setVisible(true);
		return "hello";
	}
	
	/**
	 * 
	 * @return
	 */
	public String getFileName()
	{
		return JOptionPane.showInputDialog(fileName);
	}
	
	
	public String getStudentID()
	{
		return JOptionPane.showInputDialog(studentID);
	}
	/**
	 * 
	 * @param message
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
	
}
