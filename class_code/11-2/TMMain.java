import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class TMMain {

	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner(new File("JavaReview.txt"));
		TMCourse course = TMCourse.readCourse(scan);
		System.out.println(course);
	}

}
