import java.io.IOException;

public class MVCStudentRecordApp {
 
	public static void main(String[] args){
		
		StudentRecordModel model = new StudentRecordModel();
		StudentRecordView view = new StudentRecordView();
		
		StudentRecordController controller = new StudentRecordController(view, model);
		view.setVisible(true);
	}
}
