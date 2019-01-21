import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * When notified of changes in the DrawingModel, the Viewer will draw all shapes
 * from the model within the panel.
 * 
 * @author codes
 *
 */
public class Viewer extends JPanel implements View {

	private DrawingModel model;

	public Viewer() {
		setBackground(Color.WHITE);
	}

	@Override
	public void update(DrawingModel m) {
		this.model = m;
		this.repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		// draw the shapes
		if (model != null) {
			for (Shape s : model.getShapes()) {
				s.draw(g);
			}
		}
	}

}
