import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

public class PaletteTest {
	
	private Color[] colors1 = {Color.BLACK,Color.BLUE,Color.CYAN};
	private Color[] colors2 = {Color.BLUE};
	private Color[] colors3 = {Color.BLACK, Color.BLUE, Color.WHITE};
	
	private Palette p1 = new Palette(colors1, "hello, world");
	private Palette p2 = new Palette(colors2, "hello, world");
	private Palette p3 = new Palette(colors3, "hello, world"); 
	private Palette p4 = new Palette(colors1, "yo yo y-yo yo!"); 
	private Palette p5 = new Palette(colors1, "hello, world"); 

	@Test
	public void testSameObject() {
		assertTrue(p1.equals(p1));
	}
	
	@Test
	public void testDifferentColorsLength() {
		assertFalse(p1.equals(p2));
	}
	
	@Test
	public void testDifferentColorElements() {
		assertFalse(p1.equals(p3));
	}
	
	@Test
	public void testSameColorsLengthElementsDifferentNames() {
		assertFalse(p1.equals(p4));
	}
	
	@Test
	public void testEqualPalettes() {
		assertTrue(p1.equals(p5));
	}
	// Create a string array of Color[]
	

	

}
