import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class StudentRecordModel {
	
	private BinSearchTree binarySearchTree;

	public void createTreeFromFile(String fileName) throws IOException{
		
		binarySearchTree = new BinSearchTree();
		FileReader fr = new FileReader(fileName);
		BufferedReader br = new BufferedReader(fr);
		String line = "";
		while((line = br.readLine()) != null) 
		{
			String[] words = line.split("\\s+"); //words has a format [, 64115, EN, ENCH, 2]
			String id = words[1];
			String faculty = words[2];
			String major = words[3];
			String year = words[4];
			binarySearchTree.insert(id, faculty, major, year);
			System.out.println(id + " " + faculty + " " + major + " " + year); //Just printing to the ouput for now
		}
	}
	
	public BinSearchTree getBinarySearchTree()
	{
		return binarySearchTree;
	}
	
	public Data findRecordFromID(int idNumber) {
		return null;
	}
	
	public void insertStudent(Data newStudent) {
		
	}
	
	//just using the main to test 
	public static void main(String[] args) throws IOException {
		StudentRecordModel model = new StudentRecordModel();
		model.createTreeFromFile("input.txt");
	}
}
