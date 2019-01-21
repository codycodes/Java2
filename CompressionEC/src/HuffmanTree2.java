import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.util.PriorityQueue;
import java.util.Scanner;

public class HuffmanTree2 {

	private LeafNode root;
	private LeafNode overallRoot;
	

	/**
	 * Creates a HuffmanTree2 using LeafNode objects
	 * 
	 * @param count
	 * @throws IOException
	 */
	public HuffmanTree2(int[] count) throws IOException {
		PriorityQueue<LeafNode> pq = new PriorityQueue<LeafNode>();
		for (int i = 0; i < count.length; i++) {
			if (count[i] != 0) {
				LeafNode leaf = new LeafNode((char) i, count[i], null, null);
				pq.add(leaf);
			}
		}
		// create pseudo-eof
		char pseudoChar = (char) Decode2.CHAR_MAX;
		LeafNode pseudoLeaf = new LeafNode(pseudoChar, 1, null, null);
		pq.add(pseudoLeaf);
		while (pq.size() > 1) {
			// dequeue the first two nodes
			LeafNode left = pq.remove();
			LeafNode right = pq.remove();
			// merge the leafnodes together
			pq.add(new LeafNode('\0', left.getFreq() + right.getFreq(), left, right));
		}
		// pq size should be one
		root = pq.remove();
		overallRoot = root;
		this.printTree();
	}

	public HuffmanTree2(BitInputStream input) throws IOException {
		overallRoot = readTree(input);
		this.printTree();
	}

	/**
	 * Traverses the HuffmanTree in a pre-order traversal
	 * When it finds a bit, it adds it to the output stream
	 * 
	 * @param input
	 * @return
	 */
	private LeafNode readTree(BitInputStream input) {
		int bit = input.readBit();
		if (bit == 1) {
			char c = (char) read9(input);
			return new LeafNode(c, 0, null, null);
		} else {
			LeafNode left = readTree(input);
			LeafNode right = readTree(input);
			return new LeafNode('\0', -1, left, right);
		}
	}

	/**
	 * Prints the output from the HuffmanTree2
	 * 
	 * @param output
	 */
	public void write(PrintStream output) {
		String code = "";
		traverseCreateCode(output, code, root);
	}

	/**
	 * Writes the bits of a HuffmanTree2 to an output stream as we traverse it
	 * 
	 * @param output
	 */
	public void writeHeader(BitOutputStream output) {
		writeHeader(output, root);
	}

	/**
	 * Writes the bits of a HuffmanTree2, as well as the characters it contains to
	 * an output stream, as we traverse the tree
	 * 
	 * @param output
	 * @param root
	 */
	private void writeHeader(BitOutputStream output, LeafNode root) {
		if (root != null) {
			if (root.left == null && root.right == null) {
				output.writeBit(1);
				write9(output, (int) root.getChar());
			} else {
				// not a leaf
				output.writeBit(0);
				writeHeader(output, root.left);
				writeHeader(output, root.right);

			}
		}
	}

	/**
	 * Writes a given character to the output stream as a series of 9 bits
	 * 
	 * @param output
	 * @param c
	 */
	// pre : 0 <= n < 512
	// post: writes a 9-bit representation of n to the given output stream
	private void write9(BitOutputStream output, int c) {
		// TODO Auto-generated method stub
		for (int i = 0; i < 9; i++) {
			output.writeBit(c % 2);
			c /= 2;
		}
	}

	/**
	 * Prints the tree
	 * @throws IOException
	 */
	public void printTree() throws IOException {
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
		
		overallRoot.printTree(writer);
		writer.flush();
	}

	/**
	 * Helper method to traverse through HuffmanTree and print characters and codes
	 * in the following format: char (as int)\ncode
	 * 
	 * @param output
	 * @param root
	 */
	public void traverseCreateCode(PrintStream output, String code, LeafNode root) {
		if (root != null) {
			if (root.right == null && root.left == null) {
				output.println((int) root.getChar());
				output.println(code);
			}
			traverseCreateCode(output, code + "0", root.left);
			traverseCreateCode(output, code + "1", root.right);
		}
	}

	/**
	 * Reads bits from the given input stream and writes the corresponding
	 * characters to the output. Stops reading when it encounters a character with
	 * value equal to eof
	 * 
	 * @param input
	 * @param output
	 * @param eof
	 * @throws IOException
	 */
	void decode(BitInputStream input, PrintStream output, int eof) throws IOException {
		root = overallRoot;
		int bit;
		this.printTree();
		while ((bit = input.readBit()) != -1) {
			if (bit == 0) {
				root = root.left;
			}

			if (bit == 1) {
				root = root.right;
			}

			char c = root.getChar();
			if (root.left == null && root.right == null) {
				if (c == eof) {
					break;
				}
				output.write(c);
				root = overallRoot;
			}
		}
		root = overallRoot;
	}

	/**
	 * Reads a given character via the input stream as a series of 9 bits
	 * 
	 * @param input
	 * @return
	 */
	// pre : an integer n has been encoded using write9 or its equivalent
	// post: reads 9 bits to reconstruct the original integer
	private int read9(BitInputStream input) {
		int multiplier = 1;
		int sum = 0;
		for (int i = 0; i < 9; i++) {
			sum += multiplier * input.readBit();
			multiplier *= 2;
		}
		return sum;
	}

	/**
	 * Put all the codes for each character in the codes String array
	 * 
	 * @param codes
	 */
	public void assign(String[] codes) {
		assignHelper(codes, "", root);
	}

	/**
	 * Creates and finds characters at LeafNodes as a Huffman Tree
	 * 
	 * @param codes
	 * @param code
	 * @param root
	 */
	public void assignHelper(String[] codes, String code, LeafNode root) {
		if (root != null) {
			if (root.right == null && root.left == null) {
				codes[(int) root.getChar()] = code;
			}
			assignHelper(codes, code + "0", root.left);
			assignHelper(codes, code + "1", root.right);
		}
	}

}
