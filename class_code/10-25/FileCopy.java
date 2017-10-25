import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Makes a copy of a file
 *
 * @author codes
 *
 */
public class FileCopy {
	public static void main(String[] args) {
		String fileName = "src/FileCopy.java";

		// open an input stream
		InputStream in = null;
		try {
			in = new FileInputStream(fileName);
		} catch (FileNotFoundException e) {
			System.out.println(fileName + " doesn't exist!");
		}

		try (InputStream in2 = new FileInputStream(fileName)) {

		} catch (FileNotFoundException e) {
			System.out.println(fileName + " could not be found");
		} catch (IOException e) {
			System.out.println("input stream could not be closed");
		}

		OutputStream out;
		try {
			out = new FileOutputStream("copy.txt");
		} catch (FileNotFoundException e) {
			System.out.println("Could not create the file to copy to");
			try {
				in.close();
			} catch (IOException e1) {
				System.out.println("Could not close the input stream");
			}
			return;
		}
		
		// make the copy
		try {
			int data;
			while ((data = in.read()) != -1) {
				out.write(data);
			}
		} catch (IOException e) {
			System.out.println("Copy unsuccessful!");
		} finally { // Done whether the copy is successful or not
			try {
				in.close();
				out.close();
			} catch(IOException e1) {
				System.out.println("Could not close the input and/or output stream.");
			}
		}
		System.out.println("Copy was successfully made!");
	}
	

}
