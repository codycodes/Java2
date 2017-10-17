	public class Car implements Cloneable {
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
		if (obj instanceof Car) {
			Car c = (Car) obj;
			return this.make.equals(c.make) && this.weight == c.weight;
		} else {
			return false;
		}
		
		
	}
	// Making a copy
	// Return an alias
	public Car aliasCopy() {
		return this;
	}
	
	// Return a shallow copy (the copy and the original share the String object = make)
	public Car shallowCopy() {
		return new Car(this.make, this.weight);
	}
	
	// Return a deep copy (the copy and the original are totally distinct)
	public Car deepCopy() {
		return new Car(new String(this.make), this.weight);
		// since strings are immutable (meaning that it can't be changed once created), there is
		// no difference between a deep and a shallow copy. The original can't be modified via a
		// shallow copy
	}
	
	
	// or using clone from Object (not recommended since syntax heavy)
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone(); // clone in object makes a shallow copy
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}