import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class PolygonPanel extends JPanel implements View{
	private PolygonModel model;
	
	@Override
	public void update(PolygonModel model) {
		this.model = model;
		repaint(); // eventually paintComponent is called by the OS
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int n = rand.nextInt(11) + 5;
		// vertices
		int[] x = model.getX();
		int[] y = model.getY();
		Color color = model.getColor();
		g.setColor(color);
		g.fillPolygon(x, y, x.length);
	}
};

}
