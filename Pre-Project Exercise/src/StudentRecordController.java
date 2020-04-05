import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

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
		studentRecordView.addInsertButtonFromAddNodeListener(new addInsertButtonFromAddNodeListener());
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
			catch(IOException error)
			{
				studentRecordView.displayMessage("Inputted file name was not correct.");
			}
			catch(NullPointerException error)
			{
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
			catch(NullPointerException error)
			{
				
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
	
	public class addInsertButtonFromAddNodeListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			Data studentInfo;
			try
			{
				String id = studentRecordView.id.getText().toString();
				String faculty = studentRecordView.fac.getText().toString();
				String major = studentRecordView.major.getText().toString();
				String year = studentRecordView.year.getText().toString();
				studentInfo = new Data(id, faculty, major, year);
				studentRecordModel.insertStudent(studentInfo);
			}
			catch(Exception error)
			{
				studentRecordView.displayMessage("In error!");
			}
		}
	}
}
