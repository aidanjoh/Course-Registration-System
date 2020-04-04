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
	
	private JTextField studentRecords = new JTextField();
	
	//For the input panel
	private JLabel fileName = new JLabel("Enter the file name:");
	
	private JTextField inputtedFile = new JTextField();
	
	private JButton cancelButton = new JButton("Cancel");
	private JButton okButton = new JButton("OK");
	
	
	//For the 
	public StudentRecordView()
	{
		super("Student Records");
		
		JPanel studentRecordPanel = new JPanel();
		
		setSize(400, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		studentRecordPanel.add(title);
		studentRecordPanel.add(insertButton);
		studentRecordPanel.add(findButton);
		studentRecordPanel.add(browseButton);
		studentRecordPanel.add(createTreeButton);
		studentRecordPanel.add(studentRecords);
		
		add(studentRecordPanel);
	}
	
	/**
	 * 
	 * @param records
	 */
	public void setStudentRecords(String records)
	{
		studentRecords.setText(records);
	}
	
	/**
	 * 
	 * @return
	 */
	public String getFileName()
	{
		return getTextFromTextBox(inputtedFile);
	}
	
	/**
	 * 
	 * @param errorMessage
	 */
	public void displayErrorMessage(String errorMessage)
	{
		JOptionPane.showMessageDialog(this, errorMessage);
	}
	
	/**
	 * 
	 * @param textField
	 * @return
	 */
	public String getTextFromTextBox(JTextField textField)
	{
		return textField.getText();
	}
	
	/**
	 * 
	 * @param textField
	 * @return
	 */
	public int getIntFromTextBox(JTextField textField)
	{
		return Integer.parseInt(textField.getText());
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
