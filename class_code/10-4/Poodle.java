// A Poodle is a Critter that makes a noise of a Poodle

public class Poodle extends Critter implements Trainable {
	// constructor
	public Poodle() {
		super("A poodle is a dog with a funny haircut.");
	}

	// We must override speak if we want Poodle to be instantiated
	public void speak() {
		System.out.println("Woof, woof! I want to go to the park.");
	}

	@Override
	public void rollOver() {
		System.out.println("rolling over is so much fun!");
		
	}

	@Override
	public void fetch() {
		System.out.println("I love to play fetch!");
		
	}
}
