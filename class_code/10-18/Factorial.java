import java.util.Scanner;

public class Factorial {

	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		while(true) {
			
			System.out.println("please enter an integer or press q to quit");
			String s = scan.nextLine();
			if (s.startsWith("q")) {
				break;
			} else {
				int n = Integer.parseInt(s);
				System.out.println(factorial(n));				
			}
		}
		scan.close();
	}
	
	// returns n! using a recursive formula
	public static int factorial(int n) {
		if (n == 1) {
			return 1;
		} else {
			return factorial(n - 1) * n;
		}
	}
}
