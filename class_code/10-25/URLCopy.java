import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class URLCopy {
	public static void main(String[] args) throws IOException {
		URL url = new URL("http://seattlecentral.edu/faculty/flepeint/java143/class.html");
		InputStream in = url.openStream(); // new FileInputStream("src/FileCopy2.java");
		OutputStream out = new FileOutputStream("copy2.html"); // ("copy2.txt");

		byte[] data = new byte[500];

		// number of bytes read
		int n;
		while ((n = in.read(data)) != -1) {
			out.write(data, 0, n); // write to n so that we know exactly how many we need
		}
		in.close();
		out.close();
		
		System.out.println("Copy was successfully made!");
	}

}
