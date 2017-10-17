public class FancyCar extends Car{
	private double topSpeed;
	
	public FancyCar(String make, double weight, double topSpeed) {
		super(make, weight);
		this.topSpeed = topSpeed;
	}
	
	@Override
	
	public String toString() { 
		return super.toString() + ", top speed = "  + topSpeed;
	}

	public boolean equals(Object obj) {
		// Would we need to check using instanceof here?
			return super.equals(obj) && this.topSpeed == ((FancyCar) obj).topSpeed; 
		
	}
	
}
