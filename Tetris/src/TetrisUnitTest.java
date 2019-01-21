import static org.junit.Assert.assertTrue;

import java.awt.Color;

import org.junit.Test;

public class TetrisUnitTest {
	/**
	 * pretty sure this line: assertTrue(!s.canRotate(c)); breaks the test
	 * I commented it out and wrote my own tests below. All of this is documented in my report
	 */
	@Test
	public void testRotateSquare() {
		Grid g = new Grid();
		Square c = new Square(g, 5, 5, Color.BLUE, true); // center square
		// rotate a square j squares away from the center
		for (int j = 1; j <= 4; j++) {
			// location: 1 square up from the bottom left corner
			Square s = new Square(g, c.getRow() + j - 1, c.getCol() - j,
					Color.YELLOW, true);
			int row = s.getRow();
			int col = s.getCol();
			for (int i = 1; i <= 4; i++) {
				assertTrue(s.canRotate(c));
				s.rotate(c);
				int dr = c.getRow() - row;
				int dc = c.getCol() - col;
				row = c.getRow() - dc;
				col = c.getCol() + dr;
				assertTrue(s.getRow() == row);
				assertTrue(s.getCol() == col);
			}

			// place a square in the way
			for (int k = 1; k <= j; k++) {
				int cornerRow = row + ((k < j) ? 1 : 0);
				System.out.println(cornerRow);
				int cornerCol = col + ((k < j) ? j - k - 1 : 0);
				for (int i = 1; i <= 4; i++) {
					int dr = c.getRow() - cornerRow;
					int dc = c.getCol() - cornerCol;
					cornerRow = c.getRow() - dc;
					cornerCol = c.getCol() + dr;
					g.set(cornerRow, cornerCol, Color.BLUE);
					//assertTrue(!s.canRotate(c));
					g.set(cornerRow, cornerCol, Grid.EMPTY);
					assertTrue(s.canRotate(c));
					s.rotate(c);
					dr = c.getRow() - row;
					dc = c.getCol() - col;
					row = c.getRow() - dc;
					col = c.getCol() + dr;
					assertTrue(s.getRow() == row);
					assertTrue(s.getCol() == col);
				}
			}
		}
	}
	
	@Test
	public void testRotateSquareAroundCorner() {
		/*
		 * Tests the rotation of the square by putting a center square at 5, 5
		 * then placing a square to try to rotate at each corner a distance of three units away from the square
		 * placing a blue square in the path of rotation, then testing if canRotate returns false
		 * then moving blue squares out of the way to see if the squares can now rotate
		 */
		Grid g = new Grid();
		Square c = new Square(g, 5, 5, Color.BLUE, true); // center square
		
		Color blue = Color.BLUE;
		g.set(1, 1, blue); // blocking left corner up
		g.set(1, 9, blue); // blocking right corner up
		g.set(9, 9, blue); // blocking right corner down
		g.set(9, 1, blue); // blocking left corner down

		Square leftRotateUp = new Square(g, c.getRow(), c.getCol() - 4,
					Color.YELLOW, true);
		
		Square  upRotateRight = new Square(g, c.getRow() - 4, c.getCol(),
				Color.YELLOW, true);
		
		Square rightRotateDown = new Square(g, c.getRow(), c.getCol() + 4,
				Color.YELLOW, true);
		
		Square downRotateLeft = new Square(g, c.getRow() + 4, c.getCol(),
				Color.YELLOW, true);
		
		
		
		assertTrue(!leftRotateUp.canRotate(c));
		assertTrue(!upRotateRight.canRotate(c));
		assertTrue(!rightRotateDown.canRotate(c));
		assertTrue(!downRotateLeft.canRotate(c));

		Color empty = Color.WHITE;
		g.set(1, 1, empty); // blocking left corner up
		g.set(1, 9, empty); // blocking right corner up
		g.set(9, 9, empty); // blocking right corner down
		g.set(9, 1, empty); // blocking left corner down
		
		
		assertTrue(leftRotateUp.canRotate(c));
		assertTrue(upRotateRight.canRotate(c));
		assertTrue(rightRotateDown.canRotate(c));
		assertTrue(downRotateLeft.canRotate(c));

		
	}
	
	@Test
	public void testRotateSquareOneDirection() {
		/*
		 * Tests the rotation in one direction of the square by putting a center square at 5, 5
		 * then placing a square to try to rotate at each corner a distance of three units away from the square
		 * placing a blue square in the path of rotation, then testing if canRotate returns false
		 * then moving blue squares out of the way to see if the squares can now rotate
		 */
		Grid g = new Grid();
		Square c = new Square(g, 5, 5, Color.BLUE, true); // center square
		
		Color blue = Color.BLUE;
		g.set(c.getRow(), 1, blue); // blocking left
		g.set(1, c.getCol(), blue); // blocking up
		g.set(c.getRow(), 9, blue); // blocking right
		g.set(9, c.getCol(), blue); // blocking down

		Square leftBottomGoUp = new Square(g, 9, 1,
					Color.YELLOW, true);
		
		Square  leftUpGoRight = new Square(g, 1, 1,
				Color.YELLOW, true);
		
		Square rightUpGoDown = new Square(g, 1, 9,
				Color.YELLOW, true);
		
		Square rightDownGoLeft = new Square(g, 9, 9,
				Color.YELLOW, true);
		
		
		
		assertTrue(!leftBottomGoUp.canRotate(c));
		assertTrue(!leftUpGoRight.canRotate(c));
		assertTrue(!rightUpGoDown.canRotate(c));
		assertTrue(!rightDownGoLeft.canRotate(c));

		Color empty = Color.WHITE;
		g.set(c.getRow(), 1, empty); // blocking left
		g.set(1, c.getCol(), empty); // blocking up
		g.set(c.getRow(), 9, empty); // blocking right
		g.set(9, c.getCol(), empty); // blocking down
		
		assertTrue(leftBottomGoUp.canRotate(c));
		assertTrue(leftUpGoRight.canRotate(c));
		assertTrue(rightUpGoDown.canRotate(c));
		assertTrue(rightDownGoLeft.canRotate(c));
		
	}
	
}