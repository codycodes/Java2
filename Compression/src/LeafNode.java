import java.io.BufferedWriter;
import java.io.IOException;

public class LeafNode implements Comparable<LeafNode> {
	private char c;
	private int freq;
	LeafNode left;
	LeafNode right;
	
	public LeafNode(char c, int freq, LeafNode left, LeafNode right) {
		this.c = c;
		this.freq = freq;
		this.left = left;
		this.right = right;
	}
	
	
	public char getChar() {
		return this.c;
	}
	
	public int getFreq() {
		return this.freq;
	}

	@Override
	public int compareTo(LeafNode o) {
		return this.freq - o.freq;
	}
	
	public void printTree(BufferedWriter out) throws IOException {
        if (right != null) {
            right.printTree(out, true, "");
        }
        printNodeValue(out);
        if (left != null) {
            left.printTree(out, false, "");
        }
    }

    private void printNodeValue(BufferedWriter out) throws IOException {
        if (c == 256) {
            out.write("<End Of File>");
        } else {
            out.write(((char) c) + " : " + freq);
        }

        out.write('\n');
    }

    // THANK YOU CONNOR
    // use string and not stringbuffer on purpose as we need to change the indent at each recursion
    private void printTree(BufferedWriter out, boolean isRight, String indent) throws IOException {
        if (right != null) {
            right.printTree(out, true, indent + (isRight ? "        " : " |      "));
        }

        out.write(indent);

        if (isRight) {
            out.write(" /");
        } else {
            out.write(" \\");
        }

        out.write("----- ");
        printNodeValue(out);

        if (left != null) {
            left.printTree(out, false, indent + (isRight ? " |      " : "        "));
        }
    }
	
	public String toString() {
		return "char = " + c + " freq = " + freq + " left = " + left + " right = " + right;
	}
}
