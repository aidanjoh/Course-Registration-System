import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * The Class StudentRecordModel is an implementation of the model for an application to maintain student records following MVC architecture
 * design. 
 * 
 * @author Aidan Johnson and Michele Piperni
 * @version 1.0
 * @since April 4, 2020
 */
public class StudentRecordModel 
{
	/**
	 * The binary search tree that is created.
	 */
	private BinSearchTree binarySearchTree;
	
	/**
	 * Constructor for the StudentRecordModel class.
	 */
	public StudentRecordModel() 
	{
		binarySearchTree = new BinSearchTree();
	}
	
	/**
	 * Creates a binary search tree from a text file.
	 * 
	 * @param fileName
	 * @throws IOException
	 */
	public void createTreeFromFile(String fileName) throws IOException
	{
		FileReader fr = new FileReader(fileName);
		
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(fr);
		String line = "";
		
		//Creates a new binary search tree every time a new file is read
		binarySearchTree = new BinSearchTree();
		
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
	
	/**
	 * Returns a string containing the entire binary search tree. 
	 * 
	 * @return String containing the binary search tree.
	 * @throws IOException
	 */
	public String toStringStudentRecords() throws IOException 
	{
		StringWriter bigString = new StringWriter();
		PrintWriter p = new PrintWriter(bigString);
		binarySearchTree.print_tree(binarySearchTree.root, p);
		String s = bigString.toString();
		return s;
	}
	
	/**
	 * Returns the binary search tree.
	 * 
	 * @return The binary search tree
	 */
	public BinSearchTree getBinarySearchTree()
	{
		return binarySearchTree;
	}
	
	
	/**
	 * Takes an Id number and searches the binary search 
	 * 
	 * @param idNumber The ID number that is searched for.
	 * @return All the information of the student
	 */
	public Data findStudentRecordFromID(String idNumber)
	{
		Node searchedNode;
		searchedNode = binarySearchTree.find(binarySearchTree.root, idNumber); //Starts the search at the root Node
		if(searchedNode == null)
			return null;
		else
			return searchedNode.data;
	}
	
	/**
	 * Inserts a student into the binary search tree
	 * 
	 * @param studentInfo
	 */
	public void insertStudent(Data studentInfo) 
	{
		binarySearchTree.insert(studentInfo.id, studentInfo.faculty, studentInfo.major, studentInfo.year);
	}
}
