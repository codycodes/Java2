import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PolygonModel {
	private int[] x, y;
	private Color color;
	
	// the views this model is connected to
	private List <View> views = new ArrayList<View>();
	
	// random number generator
	private static Random rand = new Random();
	

	/*
	 * Connects the given view to the model
	 * @param view the views to connect the model
	 */
	public void addView(View view) {
		views.add(view);
		view.update(this);
	}
	/*
	 * Updates all of the views
	 */
	public void updateAll() {
		for (View v : views) {
			v.update(this);
		}
	}
	/**
	 * Returns the x-coordinates of the vertices of the polygon
	 */
	public int[] getX() {
		return x;
	}
	
	
	/**
	 * Returns the x-coordinates of the vertices of the polygon
	 */
	public int[] getY() {
		return y;
	}
	/**
	 * Returns the color of the polygon
	 * @return
	 */
	public Color getColor() {
		return color; // like a deep copy since Color is immutable
	}
	
	public void createPolygon(int width, int height) {
		int n = rand.nextInt(11) + 5;
		// vertices
		x = new int[n];
		y = new int[n];
		for (int i = 0; i < n; i++) {
			x[i] = rand.nextInt(width); // need to get width and height some
			y[i] = rand.nextInt(height);
		}
		color = new Color (rand.nextInt());
		
		// the data has changed -> tell the views about the change
		updateAll();
	}
	
}
