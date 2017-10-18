
public class RecursionExamples {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

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
