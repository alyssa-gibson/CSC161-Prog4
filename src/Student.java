/* Name: Alyssa Gibson
 * Creation Date: 04.29.2018
 * Completion Date: 05.09.2018
 * CSC161*1 
 *    		Prompt: Grade these students' multiple choice tests.
 * 
 */

public class Student {
	
	String id;
	String fname;
	String lname;
	
	public Student (String i, String f, String l) {
		id = i;
		fname = f;
		lname = l;
		
	}
	
	public String getID() {
		return id;
	}
	
	public String getFirstName() {
		return fname;
	}
	
	public String getLastName() {
		return lname;
	}
	
	public static String print(Student s) {
		String result = (s.getLastName() + "\t" + s.getFirstName());
		return result;
	}
	
}
