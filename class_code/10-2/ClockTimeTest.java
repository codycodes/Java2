import static org.junit.Assert.*;

import org.junit.Test;

public class ClockTimeTest {

	public static void main(String[] args) {
		// Old-fashioned test (by checking a printout)
		ClockTime t = new ClockTime(1, 11, "AM");
		System.out.println("Before advancing: t = " + t);
		t.advance(21075);
		System.out.println("After advancing: t = " + t);
	}
	
	// Test using a unit test
	@Test
	public void testAdvance() {
		// Old-fashioned test (by checking a printout)
		ClockTime t = new ClockTime(1, 11, "AM");
		//System.out.println("Before advancing: t = " + t);
		assertTrue(t.getHour() == 4); // checks if get hour is indeed equal to one
		assertTrue(t.getMinute() == 26); // checks if get minute is indeed equal to eleven
		assertTrue(t.getAmPm().equals("PM")); // checks if getAmPm() is indeed equal to "AM" EQUAL
		t.advance(21075);
		System.out.println("	After advancing: t = " + t);
	}

}
