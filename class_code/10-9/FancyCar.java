
public class FancyCar extends Car{
	private double topSpeed;
	
	public FancyCar(String make, int weight, double topSpeed) {
		super(make, weight);
		this.topSpeed = topSpeed;
		// TODO Auto-generated constructor stub
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Car) {
			FancyCar c = (FancyCar) obj;
			return super.equals(c) && c.topSpeed == this.topSpeed; 
		} else {
			return false;
		}
	}

}
