/**
 * A student is a person with a gpa
 * 
 * @author CSC 143
 *
 */
public class Student extends Person {
	// gpa of the student
	private double gpa;

	/**
	 * Creates a student given the name, age and gpa
	 */
	public Student(String name, int age, double gpa) {
		// construct the Person part of the student (with a super call)
		// MUST always be done first
		super(name, age);
		this.gpa = gpa;
	}

	/**
	 * Prints the name, age and gpa of the student
	 */
	@Override
	public void speak() {
		// Same signature as in Person -> override
		super.speak();
		System.out.println("gpa = " + gpa);
	}
	
	/**
	 * Returns the name, age and gpa of the student in a string
	 */
	@Override
	public String toString() {
		return super.toString() + ", gpa = " + gpa;
	}

}
