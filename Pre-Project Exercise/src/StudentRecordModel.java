import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class StudentRecordModel 
{
	private BinSearchTree binarySearchTree;

	public void createTreeFromFile(String fileName) throws IOException
	{
		
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
		}
	}
	
	//makes a big ass string of all the records in order from the binary search tree
	public String toStringStudentRecords() throws IOException 
	{
		StringWriter bigString = new StringWriter();
		PrintWriter p = new PrintWriter(bigString);
		binarySearchTree.print_tree(binarySearchTree.root, p);
		String s = bigString.toString();
		return s;
	}
	
	public BinSearchTree getBinarySearchTree()
	{
		return binarySearchTree;
	}
	
	
	public Data findStudentRecordFromID(String idNumber)
	{
		Node searchedNode;
		searchedNode = binarySearchTree.find(binarySearchTree.root, idNumber); //Starts the search at the root Node
		if(searchedNode == null)
			return null;
		else
			return searchedNode.data;
	}
	
	public void insertStudent(Data studentInfo) 
	{
		binarySearchTree.insert(studentInfo.id, studentInfo.faculty, studentInfo.major, studentInfo.year);
	}
}
