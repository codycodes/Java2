import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * A class to put together the different parts of the MVC approach for the
 * Polygon application
 * 
 * @author CSC 143
 *
 */
public class MainClass {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				createApplication();
			}
		});
	}

	public static void createApplication() {
		// create the model
		PolygonModel model = new PolygonModel();

		JFrame frame = new JFrame("Polygon Application");
		frame.setSize(600, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// place a label at the top of the frame
		JLabel label = new JLabel("This is a label");
		label.setFont(new Font("Courier", Font.BOLD | Font.ITALIC, 25));
		JPanel northPanel = new JPanel();
		northPanel.setBackground(Color.WHITE);
		northPanel.add(label);
		frame.add(northPanel, BorderLayout.NORTH);

		// place a button at the bottom of the panel
		JButton button = new JButton("Draw");
		JPanel southPanel = new JPanel();
		southPanel.add(button);
		frame.add(southPanel, BorderLayout.SOUTH);

		// A panel that displays a polygon
		PolygonPanel panel = new PolygonPanel();
		frame.add(panel);
		frame.setVisible(true);

		// initial area for the polygon
		model.createPolygon(panel.getWidth(), panel.getHeight());

		// Controller
		PolygonController controller = new PolygonController(model, panel.getWidth(), panel.getHeight());
		button.addActionListener(controller);
		panel.addComponentListener(controller);

		// Connect model and view
		model.addView(panel);
	}
}
