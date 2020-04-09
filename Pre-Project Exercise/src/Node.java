
/**
 * A node with data and two references to the next-left and next-right node.
 */
class Node 
{
	/**
	 * The data Data object represents the data in the node.
	 */
	Data data;
	
	/**
	 * The left and right node object.
	 */
	Node left, right;
	
	/**
	 * Constructs a node object by assigning a string representing the id, faculty, major and year to a data object and
	 * making the next left and right node null.
	 *
	 * @param id student id
	 * @param faculty faculty code
	 * @param major student's major
	 * @param year student's year of study
	 */
	public Node(String id, String faculty, String major, String year) {
		// creating a data item
		data = new Data(id, faculty, major, year);
		left = null;
        right = null;
	}
	
	/**
	 * Creates a String representing the information in the node object.
	 */
	public String toString() 
	{
		return data.toString();
	}
}

