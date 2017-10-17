
public class CarTest {

	public static void main(String[] args) {
		// Test equals on the Car type
		
//		Car c1 = new Car("Ford", 2000);
//		Car c2 = new Car("Ford", 2000);
//		System.out.println("c1.equals(c2) is " + c1.equals(c2));
//		System.out.println("c1.equals(c2) is " + c1.equals(c2));
		
		// Test equals on the FancyCar type
		// Ensure that this is symmetric
		FancyCar fc1 = new FancyCar("Ford", 2000, 250);
		FancyCar fc2 = new FancyCar("Ford", 2000, 250);
		System.out.println("fc1.equals(c2) is " + fc1.equals(null));
		System.out.println("fc1.equals(c2) is " + fc2.equals(fc1));
		
		// Test equals between a Car and a FancyCar
	}

}