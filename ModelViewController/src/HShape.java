import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * The constructor of the H shape should take 4 parameters: int x, int y, Color
 * c, and int size. (x, y) are the coordinates of the location of the shape (as
 * for the fibonacci square, the precise location of the point with coordinates
 * (x,y) is your choice). c is the color the H shape size is the length of the
 * side of the square that contains the H shape.
 * 
 * @author codes
 *
 */

public class HShape extends AbstractShape {
	// used for drawing the HShape
	private int squareCellLength = size / 3;

	/**
	 * Creates an HShape using the AbstractShape superconstructor
	 * @param x
	 * @param y
	 * @param c
	 * @param size
	 */
	public HShape(int x, int y, Color c, int size) {
		super(x, y, size, c);
		childShapes = new Shape[7];
		// create a bounding box for determining if click is inside shape
		boundingBox = new Rectangle(x, y, size, size);
	}

	/**
	 * Paints the HShape, else loop through children array and paint the
	 * childShapes
	 */
	@Override
	public void draw(Graphics g) {
		if (childShapes[0] == null) {
			drawHShape(g);
		} else {
			for (Shape s : childShapes) {
				s.draw(g);
			}
		}

	}

	/**
	 * Creates seven children in this class, depending on the last shape's, each
	 * one- third the size of the previous level
	 */
	@Override
	public boolean createChildren() {
		int childLength = squareCellLength;
		if (childLength > 25) {
			int col = x;
			int row = y;
			for (int i = 0; i <= 2; i++) {
				childShapes[i] = new HShape(col, row + (childLength * i), color, childLength);
				childShapes[i + 3] = new HShape(col + (childLength * 2), row + (childLength * i), color, childLength);
			}
			childShapes[6] = new HShape(col + childLength, row + childLength, color, childLength);
			return true;
		} else {
			// cannot draw another HShape
			return false;
		}
	}

	/**
	 * Draws the HShape by starting with the columns and ending with the middle
	 * 
	 * @param g
	 */
	public void drawHShape(Graphics g) {
		// draw the columns of squares
		g.setColor(color);
		for (int i = 0; i < 3; i++) {
			g.fillRect(x, y + (i * squareCellLength), squareCellLength, squareCellLength);
			g.fillRect(x + (squareCellLength * 2), y + (i * squareCellLength), squareCellLength, squareCellLength);
		}
		// draw the final square
		g.fillRect(x + squareCellLength, y + squareCellLength, squareCellLength, squareCellLength);
	}

	/**
	 * Used to reset the level when the reset button is pressed
	 */
	@Override
	public void resetLevel() {
		level = 1;
	}
}
