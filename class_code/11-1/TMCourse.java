import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Representation of a full course for the Teaching Machine
 * 
 * @author codes
 *
 */
public class TMCourse {
	// title of the course
	private String title;
	// the questions in the course
	SortedMap<Integer, TMQuestion> questions;
	
	// Hide the constructor
	private TMCourse() {
	}
	
	// The user must use the factory method below to create
	// a TMCourse object
	public static TMCourse readCourse(Scanner scan) {
		TMCourse course = new TMCourse();
		course.title = scan.nextLine();
		course.questions = new TreeMap<Integer, TMQuestion>();
		while (scan.hasNextLine()) {
			TMQuestion q = TMQuestion.readQuestion(scan);
			course.questions.put(q.getNumber(), q);
		}
		return course;
	}
	
	/**
	 * Returns the full description of the course as a string
	 */
	@Override
	public String toString() {
		String s = title + "\n";
		for (Integer n : questions.keySet()) {
			s += questions.get(n) + "\n";
		}
		return s;
	}
}
