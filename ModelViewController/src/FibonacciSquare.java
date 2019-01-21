import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Constructs a Fibonacci spiral in one of four quadrants (bound within a
 * square)
 * 
 * @author codes
 *
 */

public class FibonacciSquare extends AbstractShape {

	private int quadrant;
	private int fibLevel;
	private static final int FIBMAX = 9;
	private static final int SCALE = 10;

	/**
	 * Creates a FibonacciSquare using the AbstractShape super constructor
	 * 
	 * @param x
	 * @param y
	 * @param fibLevel
	 * @param quadrant
	 */
	public FibonacciSquare(int x, int y, int fibLevel, int quadrant) {

		super(x, y, fib(fibLevel) * SCALE, Color.RED); // Added a constant multiplier to scale up size
		this.quadrant = quadrant;
		this.fibLevel = fibLevel;
		childShapes = new Shape[1];
		boundingBox = new Rectangle(0, 0, 400, 800);

	}

	/**
	 * Returns the value of the nth Fibonacci number
	 * 
	 * @param n
	 * @return
	 */
	private static int fib(int n) {
		int prevNum;
		int prevPrevNum;
		int fibNum;
		prevPrevNum = prevNum = fibNum = 1;

		if (n == 1 || n == 2) {
			return 1;
		}
		for (int fibIndex = 2; fibIndex < n; fibIndex++) {
			fibNum = prevNum + prevPrevNum;
			prevPrevNum = prevNum;
			prevNum = fibNum;
		}
		return fibNum;
	}

	/**
	 * Paints the FibonacciSquare, else loop through children array and paint the
	 * childShape
	 */
	@Override
	public void draw(Graphics g) {
		if (childShapes[0] == null) {
			drawFibonacciSquare(g);
		} else {
			drawFibonacciSquare(g);
			childShapes[0].draw(g);
		}
	}

	/**
	 * Draws the enclosing rectangle and arc depending on the quadrant of the
	 * FibonacciSquare
	 * 
	 * @param g
	 */
	public void drawFibonacciSquare(Graphics g) {
		g.setColor(color);
		g.drawRect(x, y, size, size);
		// Draw an arc
		switch ((quadrant + 1) % 4) {
		case 1:
			g.drawArc(x - size, y, 2 * size, 2 * size, 0, 90);
			break;
		case 2:
			g.drawArc(x, y, 2 * size, 2 * size, 90, 90);
			break;
		case 3:
			g.drawArc(x, y - size, 2 * size, 2 * size, 180, 90);
			break;
		default:
			g.drawArc(x - size, y - size, 2 * size, 2 * size, 270, 90);
			break;
		}
	}

	/**
	 * Creates one child in the FibonacciSquare class, depending on the last shape's
	 * quadrant and coordinates
	 */
	@Override
	public boolean createChildren() {
		if (fibLevel < FIBMAX) {
			int newSize = fib(fibLevel + 1) * SCALE;
			int prevSize = fib(fibLevel - 1) * SCALE;
			switch ((quadrant + 1) % 4) {
			case 1:
				childShapes[0] = new FibonacciSquare(x - newSize, y, fibLevel + 1, 1);
				break;
			case 2:
				childShapes[0] = new FibonacciSquare(x, y + size, fibLevel + 1, 2);
				break;
			case 3:
				childShapes[0] = new FibonacciSquare(x + size, y - prevSize, fibLevel + 1, 3);
				break;
			default:
				childShapes[0] = new FibonacciSquare(x - newSize + size, y - newSize, fibLevel + 1, 4);
				break;
			}
			return true;
		} else {
			// cannot draw another FibonacciSquare
			return false;
		}
	}

	/**
	 * Used to reset the level when the reset button is pressed
	 */
	@Override
	public void resetLevel() {
		level = 1;
	}
}
