import java.util.Scanner;

public class RecursionExamples {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		while(true) {
			System.out.println("Enter a phrase (lowercase, no punct.) or q to quit: ");
			String s = scan.nextLine();
			if (s.startsWith("q")) {
				break;
			} else {
				// more readable? 
//				if (isPalindrome(s)) { 
//					System.out.println("\"" + s + "\" is a palindrome");
//				} else {
//					System.out.println("\"" + s + "\" isn't a palindrome");
//				}
				
				Boolean b = isPalindrome(s);
				System.out.println("\"" + s + "\" is" + (b ? " " : " not") + " a palindrome!");
			}
		}
		scan.close(); // would close auto when main ends

	}
	
	// REturns true if the given string is a palindrome (meaning it is spelled the same 
	// way left to right to left ignoring
	// punctuation and case)
	
	public static boolean isPalindrome(String s) {
		if (s.length() <= 1) {
			return true;
		} else {
			// recursive algorithm
			// first and last characters are equal and the string in between is a palindrome
			return s.charAt(0) == s.charAt(s.length()-1) &&
					isPalindrome(s.substring(1, s.length() - 1));
		}
	}

}
