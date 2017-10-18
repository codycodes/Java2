import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PolygonController implements ActionListener{

	// the model the controller talks to
	private PolygonModel model;
	// to get the area where the polygon is drawn
	private PolygonPanel panel;
	
	public PolygonController(PolygonModel model, PolygonPanel panel) {
		this.model = model;
		this.panel = panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO ask the model to create a new polygon
		model.createPolygon(panel.getWidth(), panel.getHeight());
	}
	

}
