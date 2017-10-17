public class Car {
	private String make;
	private double weight;

	public Car(String make, double weight) {
		this.make = make;
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "make = " + make + ", weight = " + weight;
	}
	
	// parameter must be object for it to override
	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj.getClass() == this.getClass()) {
			Car c = (Car) obj;
			return this.make.equals(c.make) && this.weight == c.weight;
		} else {
			return false;
		}
		
	}
}