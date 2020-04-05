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
				studentRecordView.displayErrorMessage("Error!");
			}
		}
		
	}
	
	public class addBrowseButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			String output = "";
			try
			{
				studentRecordView.setStudentRecords(studentRecordModel.toStringStudentRecords());
			}
			catch(Exception error)
			{
				studentRecordView.displayErrorMessage("Error!");
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
				
				studentRecordModel.findStudentRecordFromID(studentID);
			}
			catch(Exception error)
			{
				studentRecordView.displayErrorMessage("Error!");
			}
		}
	}
}
