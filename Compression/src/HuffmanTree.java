import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.util.PriorityQueue;
import java.util.Scanner;

public class HuffmanTree {

	private LeafNode root;
	private LeafNode overallRoot;

	/**
	 * Creates a HuffmanTree using LeafNode objects
	 * 
	 * @param count
	 * @throws IOException
	 */
	public HuffmanTree(int[] count) throws IOException {
		PriorityQueue<LeafNode> pq = new PriorityQueue<LeafNode>();
		for (int i = 0; i < count.length; i++) {
			if (count[i] != 0) {
				LeafNode leaf = new LeafNode((char) i, count[i], null, null);
				pq.add(leaf);
			}
		}
		// create pseudo-eof
		char pseudoChar = (char) MakeCode.CHAR_MAX;
		LeafNode pseudoLeaf = new LeafNode(pseudoChar, 1, null, null);
		pq.add(pseudoLeaf);
		while (pq.size() > 1) {
			// deque the first two nodes
			LeafNode left = pq.remove();
			LeafNode right = pq.remove();
			// merge the leafnodes together
			pq.add(new LeafNode('\0', left.getFreq() + right.getFreq(), left, right));
		}
		// pq size should be one
		root = pq.remove();
		this.printTree();
	}

	/**
	 * Constructs a Huffman tree from the Scanner. Assumes the Scanner contains a
	 * tree description in standard format.
	 * 
	 * @param input
	 */
	public HuffmanTree(Scanner input) {
		overallRoot = new LeafNode('\0', -1, null, null);
		while (input.hasNextLine()) {
			int n = Integer.parseInt(input.nextLine());
			String code = input.nextLine();
			root = overallRoot;
			// Process char, and insert it into the tree inside LeafNode
			for (int i = 0; i < code.length(); i++) {
				char c = code.charAt(i);
				char nodeChar = (char) n;
				if (c == '0' && root.left == null) {
					root.left = new LeafNode(nodeChar, -1, null, null);
					root = root.left;
				} else if (c == '1' && root.right == null) {
					root.right = new LeafNode(nodeChar, -1, null, null);
					root = root.right;
				} else if (c == '0') {
					root = root.left;
				} else {
					// c == 1
					root = root.right;
				}
			}
		}
	}

	/**
	 * Prints the output from the HuffmanTree
	 * 
	 * @param output
	 */
	public void write(PrintStream output) {
		String code = "";
		traverseCreateCode(output, code, root);
	}

	/**
	 * Prints a representation of the tree
	 * 
	 * @throws IOException
	 */
	public void printTree() throws IOException {
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
		root.printTree(writer);
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
	 * value equal to eof.
	 * 
	 * @param input
	 * @param output
	 * @param eof
	 * @throws IOException
	 */
	void decode(BitInputStream input, PrintStream output, int eof) throws IOException {
		int bit;
		root = overallRoot;
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

}
