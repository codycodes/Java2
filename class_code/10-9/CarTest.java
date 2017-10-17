
public class CarTest {

	public static void main(String[] args) {
		// Test equals on the Car type
		
		Car c1 = new Car("Ford", 2000);
		Car c2 = new Car("Ford", 2000);
		
		FancyCar fc1 = new FancyCar("Ford", 2000, 2500);
		FancyCar fc2 = new FancyCar("Ford", 2000, 2500);
		// test between normal cars
		System.out.println("c1.equals(c2) is " + c1.equals(c2));
		System.out.println("c2.equals(c1) is " + c2.equals(c1));
		System.out.println("************************************");
		// test between fancycars
		System.out.println("fc1.equals(fc2) is " + fc1.equals(fc2));
		System.out.println("fc2.equals(fc1) is " + fc2.equals(fc1));
		System.out.println("************************************");
		// test with null
		System.out.println("fc1.equals(null) is " + fc1.equals(null));
		System.out.println("fc2.equals(null) is " + fc2.equals(null));
		System.out.println("************************************");
				
	}

}