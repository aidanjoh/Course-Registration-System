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
		
		studentRecordView.addCreateTreeButtonListener(new addListener());
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

	public class addListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			String fileName = "";
			try
			{
				fileName = studentRecordView.getFileName();
				
				studentRecordModel.createTreeFromFile(fileName);
				
				studentRecordView.setStudentRecords(studentRecordModel.toString()); //Will need to fix this
			}
			catch(Exception error)
			{
				studentRecordView.displayErrorMessage("Error!");
			}
		}
		
	}
}
