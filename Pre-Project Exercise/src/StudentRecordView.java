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

	/**
	 * 
	 */
	public StudentRecordView()
	{
		super("Main window");
		
		JPanel mainText = new JPanel();
		JPanel buttonPanel = new JPanel();
		JScrollPane textArea = new JScrollPane();
		
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
		textArea.add(studentRecords);
		
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
		studentRecords.append(records);
	}
	
	/**
	 * 
	 * @return
	 */
	public String getFileName()
	{
		return JOptionPane.showInputDialog(fileName);
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
