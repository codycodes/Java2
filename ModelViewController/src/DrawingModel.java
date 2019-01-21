import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Maintains a collection of shapes When requested by a view, the model should
 * make a deep copy of the collection of its shapes
 * 
 * It should notify all viewers when something in the model changes. (For now,
 * the only changes will be when a new shape is added to the drawing.
 *
 * @author codes
 *
 */

public class DrawingModel {
	private List<Shape> shapes = new ArrayList<Shape>();
	private List<View> views = new ArrayList<View>();
	private TextViewer view = new TextViewer();

	/**
	 * Adds a view to the list of views in the model
	 */
	public void addView(View v) {
		views.add(v);
		v.update(this);
	}

	/**
	 * Updates all of the views following a change in the model
	 */
	public void updateAll() {
		for (View v : views) {
			v.update(this);
			view.update(this);
			view.display();
		}
	}

	/**
	 * Adds a square to the list of squares
	 */
	public void addShape(Shape s) {
		shapes.add(s);
		updateAll();
	}

	/**
	 * Returns the list of the shapes
	 */
	public List<Shape> getShapes() {
		return shapes;
	}

	/**
	 * Takes in a set of coordinates and returns whether or not a level can be added
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean addLevel(int x, int y) {
		boolean canAddLevel = true;
		for (Shape s : shapes) {
			if (s.contains(x, y)) {
				canAddLevel &= s.addLevel();
			}
		}
		updateAll();
		return canAddLevel;
	}

	/**
	 * Takes in a set of coordinates and returns whether or not a level can be
	 * removed
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean removeLevel(int x, int y) {
		boolean canRemoveLevel = true;
		for (Shape s : shapes) {
			if (s.contains(x, y)) {
				canRemoveLevel &= s.removeLevel();
			}
		}
		updateAll();
		return canRemoveLevel;
	}

	/**
	 * Resets the shape. Called when the reset button is pressed
	 */
	public void reset() {
		try {
			shapes.get(0).resetLevel();
			shapes = null;
			shapes = new ArrayList<Shape>();
			addShape(new HShape(400, 150, Color.GREEN, 400));
			addShape(new FibonacciSquare(100, 450, 1, 0));
		} catch (Exception e) {
			System.out.println("Cannot create new shapes!" + e.getMessage());
		}
	}

}
