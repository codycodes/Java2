import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Window;

import javax.swing.JOptionPane;

/**
 * Add instance variables, additional methods and default implementations here
 * once it becomes clear that all Shapes need to have such fields and methods.
 * 
 * @author codes
 *
 */
public abstract class AbstractShape implements Shape {
	// location of the shape
	protected int x, y;
	// color of the shape
	protected Color color;
	// size of the shape
	protected int size;
	// array of childShapes to be accessed by HShape and FibonacciSquare
	protected Shape[] childShapes;
	// bounding box to if and which shape is clicked
	protected Rectangle boundingBox;
	// level of the shape
	protected int level = 1;

	public AbstractShape(int x, int y, int size, Color color) {
		this.x = x;
		this.y = y;
		this.size = size;
		this.color = color;
	}

	/**
	 * Checks and returns if a level can be added
	 * 
	 * @return
	 */
	public boolean addLevel() {
		boolean canAddLevel = true;
		if (childShapes[0] == null) {
			// if we can't create any more children, we're at the last level
			if (!createChildren()) {
				canAddLevel = false;
			}
		} else {
			for (Shape s : childShapes) {
				// add level to childShapes
				canAddLevel &= s.addLevel();
			}
		}
		if (canAddLevel) {
			level++;
		}
		return canAddLevel;

	}

	/**
	 * Returns if the points lie within the bounding box for the shape
	 * 
	 * @return
	 */
	public boolean contains(int x, int y) {
		return boundingBox.contains(x, y);
	}

	/**
	 * Checks and returns if a level can be removed
	 * 
	 * @return
	 */
	public boolean removeLevel() {
		boolean canRemoveLevel = true;
		// base case: this is the grand parent of the last level
		if (childShapes[0] != null && childShapes[0].getChildShapes()[0] == null || level <= 1) {
			for (int i = 0; i < childShapes.length; i++) {
				childShapes[i] = null;
			}
			if (level <= 1) {
				canRemoveLevel = false;
			}
		} else {
			for (Shape s : childShapes) {
				canRemoveLevel &= s.removeLevel();
			}
		}
		// remove a level when you can remove a level
		if (canRemoveLevel) {
			level--;
		}
		return canRemoveLevel;
	}

	/**
	 * give the type of the shape (FibonacciSquare or HShape available by calling
	 * getClass()), the coordinates of the shape, its color and its current level.
	 * 
	 * @return
	 */
	public String toString() {
		return "shape type: " + this.getClass() + "\ncoordinates: (" + this.x + ", " + this.y + ")" + "\ncolor: "
				+ this.color + "\nlevel: " + level;

	}

	/**
	 * Used for returning the grand-children of the current shape
	 */
	@Override
	public Shape[] getChildShapes() {
		return childShapes;
	}
}
