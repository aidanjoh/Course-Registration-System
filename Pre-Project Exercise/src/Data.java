/**
 * Represents data within a node.
 */
public class Data 
{
	/**
	 * id represents the student's id.
	 * faculty represents the student's faculty.
	 * year represents the student's year.
	 * major represents the student's major.
	 */
	String  id,faculty, major, year;
	
	/**
	 * Constructs a Data object by assigning the id, faculty, major and year variables.
	 * @param i the id as a string to be assigned.
	 * @param f the faculty as a string to be assigned.
	 * @param m the major as a string to be assigned.
	 * @param y the year as a string to be assigned.
	 */
	public Data( String i, String f, String m, String y)
	{
		id = i;
		faculty = f;
		major = m;
		year = y;
	}
	
	/**
	 * Produces a string representing the information in the Data object.
	 */
	public String toString()
	{
		return ("id : " + id + " faculty: " + faculty + " major: " + major + 
						" year: " + year);
	}	
}
