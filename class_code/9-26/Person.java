/**
 * A simple model of a person
 * 
 * @author CSC 143
 *
 */
public class Person {
	// a person is defined by a name and an age
	private int age;
	// protected: visible in Person and in all of the derived classes of Person
	protected String name;

	/**
	 * Creates a person given a name and an age
	 * 
	 * @param name
	 *            the name of the person
	 * @param age
	 *            the age of the person
	 */
	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	/**
	 * Prints the name and age of the person
	 */
	public void speak() {
		System.out.println("name = " + name + ", age = " + age);
	}

	/**
	 * Returns the name and age of the person in a string
	 */
	@Override
	public String toString() {
		return "name = " + name + ", age " + age;
	}
}
