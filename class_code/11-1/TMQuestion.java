import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Representation of one question for the Teaching Machine
 * 
 * @author codes
 * 
 */
public class TMQuestion {
	// text of the question
	private String[] text; // made an array because that's how it's going to be in HW5
	// question number
	private int number;
	// possible answers
	private Map<String, Integer> answers;

	// Hide the constructor from other classes
	private TMQuestion() {

	}

	public static TMQuestion readQuestion(Scanner scan) {
		TMQuestion q = new TMQuestion();
		q.number = scan.nextInt();
		// skip the new line after the int
		scan.nextLine();
		// text of the question
		ArrayList<String> t = new ArrayList<String>();
		String line;
		while (!(line = scan.nextLine()).equals("-----")) {
			t.add(line);
		}
		q.text = new String[t.size()];
		t.toArray(q.text);
		// possible answers
		q.answers = new HashMap<String, Integer>();
		while (scan.hasNextLine() && (line = scan.nextLine()).trim().isEmpty()) {
			// format example: "true: 5"
			String[] parts = line.split(":\\s*");
			q.answers.put(parts[0].trim(), Integer.parseInt(parts[1].trim()));
		}
		
		return q;
		
	}

	/**
	 * Returns the full question as a string
	 */
	@Override
	public String toString() {
		String s = number + "\n"; 
		for (String line : text) {
			s += line + "\n";
		}
		s += "-----\n";
		for (String a : answers.keySet()) {
			s += a + ": " + answers.get(a) + "\n";
		}
		return s;
	}
}
