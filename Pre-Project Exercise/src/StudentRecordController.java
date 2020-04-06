import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * 
 * Controller class for the Student Records Application.
 * 
 * @author Aidan Johnson and Michele Piperni
 * @version 1.0
 * @since April 2, 2020
 * 
 */
public class StudentRecordController 
{	
	/**
	 * The GUI application of the Student Records
	 */
	private StudentRecordView studentRecordView;
	/**
	 * The model application of the Student Records
	 */
	private StudentRecordModel studentRecordModel;
	
	/**
	 * Constructs a StudentRecordController.
	 * 
	 * @param view
	 * @param model
	 */
	public StudentRecordController(StudentRecordView view, StudentRecordModel model)
	{
		setStudentRecordView(view);
		setStudentRecordModel(model);
		
		studentRecordView.addCreateTreeButtonListener(new addCreateTreeButtonListener());
		studentRecordView.addBrowseButtonListener(new addBrowseButtonListener());
		studentRecordView.addFindButtonListener(new addFindButtonListener());
		studentRecordView.addInsertButtonListener(new addInsertButtonListener());
	}

	/**
	 * Sets studentRecordModel
	 * 
	 * @param studentRecordModel
	 */
	public void setStudentRecordModel(StudentRecordModel studentRecordModel)
	{
		this.studentRecordModel = studentRecordModel;
	}

	/**
	 * Sets studentRecordView
	 * 
	 * @param studentRecordView
	 */
	public void setStudentRecordView(StudentRecordView studentRecordView)
	{
		this.studentRecordView = studentRecordView;
	}

	/**
	 * Inner Class for Create Tree Button Listener
	 * 
	 * @author Aidan Johnson and Michele Piperni
	 */
	public class addCreateTreeButtonListener implements ActionListener
	{
		
		/**
		 * Performs the action of calling the create tree from file method in StudentRecordModel
		 */
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			String fileName = "";
			try
			{
				fileName = studentRecordView.getFileName();
				studentRecordModel.createTreeFromFile(fileName);
			}
			catch(IOException error)
			{
				studentRecordView.displayMessage("Inputted file name was not correct.");
			}
			catch(NullPointerException error)
			{
			}
		}
		
	}
	
	/**
	 * Inner Class for Browse Button Listener
	 * 
	 * @author Aidan Johnson and Michele Piperni
	 */
	public class addBrowseButtonListener implements ActionListener
	{
		/**
		 * Performs the action of calling the to String method in StudentRecordModel and setting the textArea in studentRecordView
		 */
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			try
			{
				studentRecordView.setStudentRecords(studentRecordModel.toStringStudentRecords());
			}
			catch(NullPointerException error)
			{
				studentRecordView.displayMessage("There is nothing to Browse!");
			}
			catch(IOException error)
			{
				studentRecordView.displayMessage("Input/Output Error!");
			}
		}
	}
	
	/**
	 * Inner Class for Find Button Listener
	 * 
	 * @author Aidan Johnson and Michele Piperni
	 */
	public class addFindButtonListener implements ActionListener
	{
		/**
		 * Performs the action of searching for a student in StudentRecordModel and returning the student to be diplayed in the view.
		 */
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			String studentID = "";
			try
			{
				studentID = studentRecordView.getStudentID();
				
			
				Data dataFound = studentRecordModel.findStudentRecordFromID(studentID);
				if(dataFound == null)
				{
					studentRecordView.displayMessage("The target was not Found!");
				}
				else
				{
					String studentInfo = "id: " + dataFound.id + " faculty: " + dataFound.faculty;
					studentInfo +=  " major: " + dataFound.major + " year: " + dataFound.year;
					studentRecordView.displayMessage(studentInfo);
				}
			}
			catch(NullPointerException error)
			{
				
			}
			catch(Exception error)
			{
				studentRecordView.displayMessage("Error!");
			}
		}
	}
	
	/**
	 * Inner Class for Insert Button Listener
	 * 
	 * @author Aidan Johnson and Michele Piperni
	 */
	public class addInsertButtonListener implements ActionListener
	{
		/**
		 * Performs the action of inserting a new student in the binary search tree after getting user input
		 */
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			Data studentInfo;
			try
			{
				studentInfo = studentRecordView.getStudentInformation();
				if(studentInfo != null) {
					studentRecordModel.insertStudent(studentInfo);
					studentRecordView.setStudentRecords(studentRecordModel.toStringStudentRecords()); //updates the browse automatically
				}
				
			}
			catch(Exception error)
			{
				studentRecordView.displayMessage("Error!");
			}
		}
	}
}
