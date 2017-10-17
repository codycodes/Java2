// A Bovine is a Critter that enjoys hay as its favorite dish

public abstract class Bovine extends Critter
{
  // Constructor
  public Bovine(String s)
  {
	  super (s += "hay is my favorite food!");
  }
  // We don't override speak.
  // Bovine is abstract as well.
}
