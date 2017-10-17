
public class PersonStudentTest {
	public static void main(String[] args) {
		Student s = new Student("Sara", 29, 3.9);
		s.speak();
		
		System.out.println(s);
		// it prints s.toString()
		
		// With inheritance, any variable comes with 2 types
		// static type: type used to declare the variable
		// when you write: Person p -> p has static type = Person
		// dynamic type: type of the object pointed to by the variable
		// p = a[0] -> p has dynamic type = Person
		// p = a[1] -> p has dynamic type = Student
		Person[] a = {
				new Person("Huy", 25),
				new Student("Sharon", 22, 3.9)};
		for (Person p : a) {
			p.speak();
			// p.speak() compiles because speak is in Person
			// at runtime, the JVM looks for speak starting in the dynamic type of p
		}
	}
}
