/* Name: Alyssa Gibson
 * Creation Date: 04.29.2018
 * Completion Date: 05.08.2018
 * CSC161*1 
 *    		Prompt: Grade these students' multiple choice tests.
 * 
 */

public class Test {
	
	String ID;
	String answers;
	Double score;
	String grade;

	public Test(String i, String a, Double s, String g) {
		ID = i;
		answers = a;
		score = s;
		grade = g;

	}
	
	public String getID() {
		return ID;
	}
	
	public String getAnswers() {
		return answers;
	}
	
	public void setScore(double d) {
		score = d;
	}
	
	public void setGrade(String s) {
		grade = s;
	}
	
	public double getScore() {
		return score;
	}
	
	public String getGrade() {
		return grade;
	}
	
	public static void print(Test t) {
		System.out.println("ID#: " + t.getID());
		System.out.println("Answers: " + t.getAnswers());
		System.out.println("Score: " + t.getScore());
		System.out.println("Grade: " + t.getGrade() + "\n");
	}
}
