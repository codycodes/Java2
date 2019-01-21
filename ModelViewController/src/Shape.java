import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Describes what a shape is required to have
 * @author codes
 *
 */

public interface Shape {
	void draw(Graphics g);

	boolean createChildren();

	boolean addLevel();

	boolean removeLevel();

	boolean contains(int x, int y);

	Shape[] getChildShapes();

	void resetLevel();
}
