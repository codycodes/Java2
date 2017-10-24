import java.util.Scanner;

public class ExceptionExample {

	public static void main(String[] args) {
		String[] names = {"Adonay", "Meng", "Connor", "Rebecca"};
		Scanner scan = new Scanner(System.in);
		while (true) {
			System.out.println("Enter an index value or q to quit: ");
			// trim removes the leading and trailing white spaces
			String input = scan.nextLine().toLowerCase().trim();
			if (input.startsWith("q")) {
				break;
			} else {
				try {
					int index = Integer.parseInt(input);
					System.out.printf("Name at index %d is %s\n", index, names[index]);
				} catch(NumberFormatException e) {
					System.out.println("Please enter an integer value!");
				} catch(ArrayIndexOutOfBoundsException e) {
					System.out.println("Please enter a valid index value!");
				}
			}
		}
		scan.close();
	}

}
