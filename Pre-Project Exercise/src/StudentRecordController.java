import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 
 * @author Aidan Johnson and Michele Piperni
 * @version 1.0
 * @since April 2, 2020
 * 
 */
public class StudentRecordController 
{
	private StudentRecordView studentRecordView;
	private StudentRecordModel studentRecordModel;
	
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
	 * 
	 * @param studentRecordModel
	 */
	public void setStudentRecordModel(StudentRecordModel studentRecordModel)
	{
		this.studentRecordModel = studentRecordModel;
	}

	/**
	 * 
	 * @param studentRecordView
	 */
	public void setStudentRecordView(StudentRecordView studentRecordView)
	{
		this.studentRecordView = studentRecordView;
	}

	public class addCreateTreeButtonListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			String fileName = "";
			try
			{
				fileName = studentRecordView.getFileName();
				studentRecordModel.createTreeFromFile(fileName);
			}
			catch(Exception error)
			{
				studentRecordView.displayMessage("Error!");
			}
		}
		
	}
	
	public class addBrowseButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			try
			{
				studentRecordView.setStudentRecords(studentRecordModel.toStringStudentRecords());
			}
			catch(Exception error)
			{
				studentRecordView.displayMessage("Error!");
			}
		}
	}
	
	public class addFindButtonListener implements ActionListener
	{
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
			catch(Exception error)
			{
				studentRecordView.displayMessage("Error!");
			}
		}
	}
	
	public class addInsertButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			String studentInfo = "";
			try
			{
				studentInfo = studentRecordView.getStudentInformation();
				
			}
			catch(Exception error)
			{
				studentRecordView.displayMessage("Error!");
			}
		}
	}
}
