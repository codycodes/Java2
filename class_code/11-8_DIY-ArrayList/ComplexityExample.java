
public class ComplexityExample {
	// Write a method that takes an array of doubles and returns the maximum element
	public double ComplexityExample(double[] a) {
		double maxSoFar = a[0];
		for (int i = 1; i < a.length; i++) {
			if (maxSoFar < a[i]) {
				maxSoFar = a[i];
			}
		}
		return maxSoFar;
	}

}
