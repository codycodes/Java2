
public class ComplexityExample {
	// Write a method that takes an array of doubles and returns the maximum element

	
	
	
	
	public double ComplexityExample(double[] a) {
		double maxSoFar = a[0]; // (2) 
		for (int i = 1; i < a.length; i++) { // int i = 1 (1), i < a.length (4), i++ (3)
			if (maxSoFar < a[i]) {  // (4)
				maxSoFar = a[i]; // (2)
			}
		}
		return maxSoFar; // (1)
	}
// 14n - 6
}
