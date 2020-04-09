/**
 * The class MVCStudentRecordApp is the application of the program that maintains student records and it creates the model, view and the
 * controller object of the student records to run the program.
 * 
 * @author Aidan Johnson and Michele Piperni
 * @version 1.0
 * @since April 4, 2020
 *
 */
public class MVCStudentRecordApp 
{
	/**
	 * Runs the program.
	 * 
	 * @param args Default
	 */
	public static void main(String[] args)
	{
		StudentRecordModel model = new StudentRecordModel();
		StudentRecordView view = new StudentRecordView();
		
		@SuppressWarnings("unused")
		StudentRecordController controller = new StudentRecordController(view, model);
		view.setVisible(true);
	}
}
