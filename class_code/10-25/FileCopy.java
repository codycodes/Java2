import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 *  Makes a copy of a file
 *
 * @author codes
 *
 */
public class FileCopy {
	public static void main(String[] args) {
		String fileName = "src/FileCopy.java";
		
		// open an input stream
		InputStream in;
		try {
			in = new FileInputStream(fileName);
		} catch(FileNotFoundException e) {
			System.out.println(fileName + " doesn't exist!");
		}
		
		try (InputStream in2 = new FileInputStream(fileName)) {
			
		} catch(FileNotFoundException e) {
			System.out.println(fileName + " could not be found");
		} catch(IOException e) {
			System.out.println("input stream could not be closed");
		}
	}

}
