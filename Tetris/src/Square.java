import java.awt.Color;
import java.awt.Graphics;

/**
 * One Square on our Tetris Grid or one square in our Tetris game piece
 * 
 * @author CSC 143
 */
public class Square {
	private Grid grid; // the environment where this Square is

	private int row, col; // the grid location of this Square

	private boolean ableToMove; // true if this Square can move

	private Color color; // the color of this Square

	// possible move directions are defined by the Game class

	// dimensions of a Square
	public static final int WIDTH = 20;

	public static final int HEIGHT = 20;

	/**
	 * Creates a square
	 * 
	 * @param g
	 *            the Grid for this Square
	 * @param row
	 *            the row of this Square in the Grid
	 * @param col
	 *            the column of this Square in the Grid
	 * @param c
	 *            the Color of this Square
	 * @param mobile
	 *            true if this Square can move
	 * 
	 * @throws IllegalArgumentException
	 *             if row and col not within the Grid
	 */
	public Square(Grid g, int row, int col, Color c, boolean mobile) {
		if (row < 0 || row > Grid.HEIGHT - 1)
			throw new IllegalArgumentException("Invalid row =" + row);
		if (col < 0 || col > Grid.WIDTH - 1)
			throw new IllegalArgumentException("Invalid column  = " + col);

		// initialize instance variables
		grid = g;
		this.row = row;
		this.col = col;
		color = c;
		ableToMove = mobile;
	}

	/**
	 * Returns the row for this Square
	 */
	public int getRow() {
		return row;
	}

	/**
	 * Returns the column for this Square
	 */
	public int getCol() {
		return col;
	}

	/**
	 * Returns true if this Square can move 1 spot in direction d
	 * 
	 * @param direction
	 *            the direction to test for possible move
	 */
	@SuppressWarnings("incomplete-switch")
	public boolean canMove(Direction direction) {
		if (!ableToMove)
			return false;
		boolean move = true;
		// if the given direction is blocked, we can't move
		// remember to check the edges of the grid
		switch (direction) {
		case DOWN:
			// if at the last row, or the next row is set
			// only moving vertically downward
			if (row == (Grid.HEIGHT - 1) || grid.isSet(row + 1, col))
				move = false;
			break;
		case LEFT:
			// if at the leftmost column, or the left column is set
			// only moving horizontally leftward
			if (col == (0) || grid.isSet(row, col - 1))
				move = false;
			break;
		case RIGHT:
			// if at the rightmost column, or the right column is set
			// only moving horizontally rightward
			if (col == (Grid.WIDTH - 1) || grid.isSet(row, col + 1))
				move = false;
			break;
		}
		return move;
	}

	/**
	 * moves this square in the given direction if possible.
	 * 
	 * The square will not move if the direction is blocked, or if the square is
	 * unable to move.
	 * 
	 * If it attempts to move DOWN and it can't, the square is frozen and cannot
	 * move anymore
	 * 
	 * @param direction
	 *            the direction to move
	 */
	@SuppressWarnings("incomplete-switch")
	public void move(Direction direction) {
		if (canMove(direction)) {
			switch (direction) {
			case DOWN:
				row = row + 1;
				break;
			case ROTATE:
				break;
			case LEFT:
				col = col - 1;
				break;
			case RIGHT:
				col = col + 1;
				break;
			}
		}
	}

	/**
	 * Changes the color of this square
	 * 
	 * @param c
	 *            the new color
	 */
	public void setColor(Color c) {
		color = c;
	}

	/**
	 * Gets the color of this square
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Draws this square on the given graphics context
	 */
	public void draw(Graphics g) {

		// calculate the upper left (x,y) coordinate of this square
		int actualX = Grid.LEFT + col * WIDTH;
		int actualY = Grid.TOP + row * HEIGHT;
		g.setColor(color);
		g.fillRect(actualX, actualY, WIDTH, HEIGHT);
		// black border (if not empty)
		if (!color.equals(Grid.EMPTY)) {
			g.setColor(Color.BLACK);
			g.drawRect(actualX, actualY, WIDTH, HEIGHT);
		}
	}

	public String toString() {
		return "row = " + row + " col = " + col;
	}

	public void rotate(Square center) {
		int prevRow = row;
		row = center.row + (col - center.col);
		col = center.col + (center.row - prevRow);
	}

	/**
	 * Checks if the rotation is within the legal boundary of the grid
	 * 
	 */

	public boolean canRotateWithinGrid(Square center, int newRow, int newCol) {
		// if (center.col - 1 < 0 || center.row - 1 < 0 || center.col + 1 > Grid.WIDTH -
		// 1|| center.row + 1 > Grid.HEIGHT - 1) {
		// System.out.println("Cannot rotate due to boundary!");
		// return false;
		// }

		if (newRow > (Grid.HEIGHT - 1) || newRow < 0 || newCol > (Grid.WIDTH - 1) || newCol < 0) {
			// System.out.println("newRow = " + newRow + " newCol = " + newCol);
			// System.out.println(this);
			// System.out.println("not within grid boundary");
			return false;
		}
		return true;
	}

	/**
	 * Loop through each square, use relative position to determine if canRotate
	 * 
	 * @param center
	 * @return
	 */
	public boolean canRotate(Square center) {
		int prevRow = row;
		int prevCol = col;
		int newCol = center.col + (center.row - prevRow);
		int newRow = center.row + (col - center.col);

		if (canRotateWithinGrid(center, newRow, newCol)) {
			if (row - center.row > 0) {
				for (int i = prevCol; i >= newCol; i--) {
					if (grid.isSet(row, i)) {
						System.out.println("Cannot rotate bottom-side square(s) COL");
						return false;
					}
				}
				for (int j = prevRow; j >= newRow; j--) {
					if (grid.isSet(j, newCol)) {
						System.out.println("Cannot rotate bottom-side square(s) ROW");
						return false;
					}
				}
				return true;
			} else if (col - center.col > 0) {
				for (int j = prevRow; j <= newRow; j++) {
					if (grid.isSet(j, col)) {
						System.out.println("Cannot rotate right-side square(s) ROW");
						return false;
					}
				}

				for (int i = prevCol; i >= newCol; i--) {
					if (grid.isSet(newRow, i)) {
						System.out.println("Cannot rotate right-side square(s) COL");
						return false;
					}
				}
				return true;
			} else if (col - center.col < 0) {
				for (int j = prevRow; j >= newRow; j--) {
					if (grid.isSet(j, col)) {
						System.out.println("Cannot rotate left-side square(s) ROW");
						return false;
					}
				}

				for (int i = prevCol; i <= newCol; i++) {
					if (grid.isSet(newRow, i)) {
						System.out.println("Cannot rotate left-side square(s) COL");
						return false;
					}
				}
				return true;
			} else {
				// row - center.row
				for (int i = prevCol; i <= newCol; i++) {
					if (grid.isSet(row, i)) {
						System.out.println("Cannot rotate top-side square(s) COL");
						// System.out.println(this);
						return false;
					}
				}
				for (int j = prevRow; j <= newRow; j++) {
					if (grid.isSet(j, newCol)) {
						System.out.println("Cannot rotate top-side square(s) ROW");
						return false;
					}
				}
				return true;
			}
		} else {
			// test case to put something in the way and see if it works!
			return false;
		}
	}
}
