package serverModel;
import java.util.Scanner;

public class FrontEnd 
{
	public static void main (String [] args) 
	{
		RegistrationApp regApp = new RegistrationApp();
	
		Scanner userInput = new Scanner(System.in);
		
		int selection;
		while(true)
		{
			System.out.println("Please select one of the following options: ");
			System.out.println("Press 1 to search catalogue courses.");
			System.out.println("Press 2 to add courses to students courses.");
			System.out.println("Press 3 to remove course from students course.");
			System.out.println("Press 4 view all courses in the catalogue.");
			System.out.println("Press 5 view all courses taken by student.");
			System.out.println("Press 6 to quit.");
			
			while(!userInput.hasNextInt())
			{
				System.out.println("Sorry the input was not a number! Please try again!");
				userInput.next();
			}
			selection = userInput.nextInt();
			
			switch (selection)
			{
				case 1:
					regApp.searchCatalogueCourses();
					break;
					
				case 2:
					regApp.addCourse();
					break;
					
				case 3:
					regApp.removeCourse();
					break;
					
				case 4:
					regApp.viewAllCoursesInCatalogue();
					break;
					
				case 5:
					regApp.viewAllStudentsCourses();
					break;
					
				case 6:
					System.out.println("See you later!");
					userInput.close();
					return;
					
				default:
					System.out.println("You did not select one of the options. Please try again!");
					break;
				
			}
		}
	}
}
