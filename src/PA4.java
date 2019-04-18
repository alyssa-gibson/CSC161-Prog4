/* Name: Alyssa Gibson
 * Creation Date: 05.09.2018
 * Completion Date: 05.17.2018
 *  CSC161*1 Program 4
 *    		Prompt: Grade students' tests but... in a more complicated way.
 *    				(Just for funsies)
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class PA4 {
	
	public static void main(String[] args) throws IOException {
		// This section of main opens and parses the file
		// To sort them into custom Student and Test classes
		
		// Variables---
		ArrayList<String> temp = new ArrayList<String>();
		// a temporary ArrayList<String> used in processing student data.
		ArrayList<Student> student = new ArrayList<Student>();
			// an ArrayList that stores student data after processing.
		ArrayList<Test> tests = new ArrayList<Test>();
			// an ArrayList that stores test data after processing.
		File sfile = new File("students.txt");
			// raw student data
		File tfile = new File("rawTests.txt");
			// raw test data
		File errorlog = new File("errors.txt");
			// new text file to store errors
		File studentlog = new File("gradeData.txt");
			// new text file to store student info + test scores
			//		and averages
		Scanner studentInfo = new Scanner(sfile);
		Scanner testInfo = new Scanner(tfile);
		DecimalFormat df = new DecimalFormat("#.00"); 
			// used in formatting scores
		String anskey;
			// anskey = test answer key; used for grading
		String errorstart = "Error Log:\n";
			// Initial label in error log file
		String studentstart = "First Name\tLast Name\tAvg    Grade";
			// Initial label in student + grades file
		String buffer = "============================================";
			// used for formatting
	    BufferedWriter errorwriter = new BufferedWriter(new FileWriter(errorlog));
	    BufferedWriter studentwriter = new BufferedWriter(new FileWriter(studentlog));
	    // ----
	    errorwriter.write(errorstart);
	    errorwriter.newLine();
	    studentwriter.append("Alphabetically:");
	    studentwriter.newLine();
	    studentwriter.write(studentstart);
	    studentwriter.newLine();
	    studentwriter.append(buffer);
	    studentwriter.newLine();
	    
		while (studentInfo.hasNext()) {
			temp.add(studentInfo.next());
		}
		
		studentInfo.close();
		
		for (int i = 0; i < temp.size();) {
			Student st = new Student(temp.get(i), temp.get(i+1), temp.get(i+2));
			student.add(st);
			i = i+3;
		}
		
		temp.clear();
		// Cleared in case of need to use again.

		while (testInfo.hasNext()) {
			// From here, parse and process the raw tests file.
			String info = testInfo.nextLine();
				// testInfo in string form
			String[] infolist = info.split(";");
				// info, separated by ;
			String idnum = infolist[0];
			String answers = infolist[1];
			String result = isValidID(infolist[0]);
			
			if (result.length() > 6) {
				// isValidID() returns a String
				// If the ID is NOT Valid, it returns the ID 
				// 		with the reason that it isn't valid.
				// Therefore, if just the ID (6 characters) is returned then 
				// 		it is valid and does not need to be written to the error file.
				errorwriter.append(result);
				errorwriter.newLine();
			}
			
			// Create a new test object here.
			Test t = new Test(idnum, answers, 0.0, "");
			tests.add(t);
			anskey = tests.get(0).getAnswers();
			t.setScore(calcGrade(anskey, t.getAnswers()));
			t.setGrade(calcLetterGrade(t.getScore()));
			
			int k = 0;
			// k is a counter to determine if a match is found
			// 0 = no match
			// 1 = match
			for (int i = 0; i < student.size(); i++) {
				if (t.getID().equals(student.get(i).getID())) {
					k++;
				}
			}
			
			if (k == 0 && t.getID().equals("000000") == false) {
				// If no match was found AND it isn't the answer key:
				errorwriter.append("ID: " + t.getID() + "- no name found. " + t.getScore() + " " + t.getGrade());
				errorwriter.newLine();
			}
		}
		
		LinkedList byname = new LinkedList();
		LinkedList byscore = new LinkedList();
		byname = AlphabeticalSort(student, tests);
		byscore = NumericSort(student, tests);

		studentwriter.append(byname.displayAlphabetically());
		studentwriter.newLine();
		studentwriter.newLine();
		studentwriter.append("From Highest Grade:");
		studentwriter.newLine();
		studentwriter.append(studentstart);
		studentwriter.newLine();
		studentwriter.append(buffer);
		studentwriter.newLine();
		studentwriter.append(byscore.displayNumerically());
		studentwriter.newLine();
		
		// This section writes the averages to the bottom of the student file.
		// Setup: ---
		studentwriter.newLine();
		studentwriter.append(String.format("%-15s%-15s%-15s", "Grade", "Count", "Pct."));
		studentwriter.append(buffer);
		studentwriter.newLine();
		// ----
		
		// A ---
		studentwriter.append(String.format("%-15s%-15s%-15s", "A ", calcAverages("A ", tests), 
				String.format("%.2f%%", ((calcAverages("A ", tests)/tests.size()*100)))));
		studentwriter.newLine();
		// ----
		
		// A- ---
		studentwriter.append(String.format("%-15s%-15s%-15s", "A-", calcAverages("A-", tests), 
				String.format("%.2f%%", ((calcAverages("A-", tests)/tests.size()*100)))));
		studentwriter.newLine();
		// ----
		
		// B+ ---
		studentwriter.append(String.format("%-15s%-15s%-15s", "B+", calcAverages("B+", tests), 
				String.format("%.2f%%", ((calcAverages("B+", tests)/tests.size()*100)))));
		studentwriter.newLine();
		// ----
		
		// B ---
		studentwriter.append(String.format("%-15s%-15s%-15s", "B ", calcAverages("B ", tests), 
				String.format("%.2f%%", ((calcAverages("B ", tests)/tests.size()*100)))));
		studentwriter.newLine();
		// ----
		
		// B- ---
		studentwriter.append(String.format("%-15s%-15s%-15s", "B-", calcAverages("B-", tests), 
				String.format("%.2f%%", ((calcAverages("B-", tests)/tests.size()*100)))));
		studentwriter.newLine();
		// ----
		
		// C+ ---
		studentwriter.append(String.format("%-15s%-15s%-15s", "C+", calcAverages("C+", tests), 
				String.format("%.2f%%", ((calcAverages("C+", tests)/tests.size()*100)))));
		studentwriter.newLine();
		// ----
		
		// C ---
		studentwriter.append(String.format("%-15s%-15s%-15s", "C ", calcAverages("C ", tests), 
				String.format("%.2f%%", ((calcAverages("C ", tests)/tests.size()*100)))));
		studentwriter.newLine();
		// ----
		
		// C- ---
		studentwriter.append(String.format("%-15s%-15s%-15s", "C-", calcAverages("C-", tests), 
				String.format("%.2f%%", ((calcAverages("C-", tests)/tests.size()*100)))));
		studentwriter.newLine();
		// ----
		
		// D ---
		studentwriter.append(String.format("%-15s%-15s%-15s", "D ", calcAverages("D ", tests), 
				String.format("%.2f%%", ((calcAverages("D ", tests)/tests.size()*100)))));
		studentwriter.newLine();
		// ----
		
		// F ---
		studentwriter.append(String.format("%-15s%-15s%-15s", "F ", calcAverages("F ", tests), 
				String.format("%.2f%%", ((calcAverages("F ", tests)/tests.size()*100)))));
		studentwriter.newLine();
		// ----

		studentwriter.close();
		errorwriter.close();
		testInfo.close();
		
	}

	public static String isValidID(String t) {
		// This method uses a few if statements to check
		// 		if the passed String is an ID number.
		// All IDs from this school have the following basic structure:
		// 		"XX0000", meaning that every ID will have two letters
		//		followed by 4 numbers.
		String result = t;
		if (t.length() != 6) {
			result += ": Wrong length";
		}
		
		if (Character.isDigit(t.charAt(0)) || Character.isDigit(t.charAt(1))) {
			if (t.charAt(0) == '0' && t.charAt(1) == '0') {
				// Every ID follows the XX0000 model, EXCEPT for the answer key
				// marker "000000".
				// This conditional allows the answer key ID to pass.
				//result += ": "
				return result;
			}
			result += " is invalid - Invalid format.";
		}
		
		if (Character.isLetter(t.charAt(2)) || Character.isLetter(t.charAt(3))
				|| Character.isLetter(t.charAt(4)) || Character.isLetter(t.charAt(5))) {
			// The last 4 characters of the ID cannot contain letters.
			result += " is invalid - Invalid format.";
		}
		
		if (t.charAt(0) == t.charAt(1)) {
			// An ID number cannot have the same two first letters.
			result += " is invalid - Duplicate letters detected.";
		}
		
		if (t.charAt(2) == t.charAt(3) || t.charAt(2) == t.charAt(4)
				|| t.charAt(2) == t.charAt(5)) {
			// The first number of the ID cannot match any others.
			result += " is invalid - Duplicate numbers detected.";
		}
		
		if (t.charAt(3) == t.charAt(4) || t.charAt(3) == t.charAt(5)) {
			// The second number of the ID cannot match any others.
			// Checking the first number of the ID is not needed.
			result += " is invalid  - Duplicate numbers detected.";
		}
		
		if (t.charAt(4) == t.charAt(5)) {
			// The third number of the ID cannot match any others.
			// Checking the first and second number 
			// of the ID is not needed.
			result += " is invalid - Duplicate numbers detected.";
		}
		
		return result;
	}	
	
	public static double calcGrade(String a, String t) {
		// This method compares the multiple choice answers
		// then compares it to the answer key.
		// Input parameters:
		// a = answer key
		// t = student's test
		double score = 0;
		for (int i = 0; i < a.length(); i++) {
			if (a.charAt(i) == t.charAt(i)) {
				score++;
			}
			
			else if (t.charAt(i) == ' ') {
				// Do nothing.
			}
			
			else {
				score = score - (0.25);
			}
		}
		
		return score;
	}
	
	public static String calcLetterGrade(double score) {
		// This method converts the score given from
		// getScore into a letter grade.
		// Input parameters:
		// score = score of the student's test, calculated
		// 		   by getGrade().
		if (score > 46) {
			return "A ";
		}
		
		else if (score <= 46 && score > 44) {
			return "A-";
		}
		
		else if (score <= 44 && score > 42) {
			return "B+";
		}
		
		else if (score <= 42 && score > 40) {
			return "B ";
		}
		
		else if (score <= 40 && score > 38) {
			return "B-";
		}
		
		else if (score <= 38 && score > 36) {
			return "C+";
		}
		
		else if (score <= 36 && score > 34) {
			return "C ";
		}
		
		else if (score <= 34 && score > 32) {
			return "C-";
		}
		
		else if (score <= 32 && score > 30) {
			return "D ";
		}
		
		else {
			return "F ";
		}
		
	}
	
	public static double calcAverages(String g, ArrayList<Test> t) {
		// This method takes in the ArrayList of tests and counts how many
		// of each letter grade denomination occur.
		// Input parameters:
		// 		String g = the letter grade requested to find
		//		ArrayList<Test> t = the tests
		int counter = 0;
		for (int i = 0; i < t.size(); i++) {
			if (t.get(i).getGrade().equals(g)) {
				counter++;
			}
		}
		
		return counter;
	}
	
	public static LinkedList AlphabeticalSort(ArrayList<Student> student, ArrayList<Test> test) {
		// This method creates a custom LinkedList of the students and their grades
		// 		organized alphabetically by last name.
		// Input parameters:
		// 		ArrayList<Student> student = ArrayList of students
		//		ArrayList<Test> test = ArrayList of tests
		LinkedList s = new LinkedList();
		for (int i = 0; i < student.size(); i++) {
			for (int k = 0; k < test.size(); k++) {
				if (student.get(i).getID().equals(test.get(k).getID())) {
					s.insert(student.get(i), test.get(k));
				}
			}
		}
		
		return s;	
	}
	
	public static LinkedList NumericSort(ArrayList<Student> student, ArrayList<Test> test) {
		// This method creates a custom LinkedList of the students and their grades
		// 		organized by highest grade.
		// Input parameters:
		// 		ArrayList<Student> student = ArrayList of students
		//		ArrayList<Test> test = ArrayList of tests
		LinkedList s = new LinkedList();
		for (int i = 0; i < student.size(); i++) {
			for (int k = 0; k < test.size(); k++) {
				if (student.get(i).getID().equals(test.get(k).getID())) {
					s.insert(test.get(k), student.get(i));
				}
			}
		}

		return s;
	}
	
}

