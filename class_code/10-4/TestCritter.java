public class TestCritter
{
  // Write description of and sound produced by Critter c.
  public static void writeInfo(Critter c)
  {
//	  c.describe();
//	  c.speak();
//	  if (c instanceof Trainable) {
//		  Trainable t = (Trainable) c;
//		  t.fetch();
//		  t.rollOver();
//		  System.out.println(t.getClass());
//	  }
	 
//	  System.out.println(c.getClass() + "^");
//	  System.out.println(c);
//	  System.out.println();
	  
//	  
  }

  // Test critter classes
  public static void main(String[] args)
  {
    // Create a poodle, a buffalo, a cow and a bird
//	  Critter poodle = new Poodle();
//	  poodle.speak();
//	  
//	  Critter buffalo = new Buffalo();
//	  buffalo.speak();
//	  
//	  Critter cow = new Cow();
//	  cow.speak();
	  
	  Critter[] a = {new Poodle(), new Buffalo(), new Cow()};
	  
	  for (Critter animal : a) {
		  writeInfo(animal);
	  }
	  
	  Bovine b = new Buffalo();
	  Critter c = b;
	  System.out.println(c.getClass());
	  
    // Call writeInfo for each critter

  }
}
