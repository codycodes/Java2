import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileCopy2 {
	public static void main(String[] args) throws IOException {
		InputStream in = new FileInputStream("src/FileCopy2.java");
		OutputStream out = new FileOutputStream("copy2.txt");

		byte[] data = new byte[500];

		// number of bytes read
		int n;
		while ((n = in.read(data)) != -1) {
			out.write(data, 0, n); // write to n so that we know exactly how many we need
		}
		in.close();
		out.close();
	}

}
