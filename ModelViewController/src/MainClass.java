import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;

public class MainClass {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			createApplication();
		});
	}

	/**
	 * Creates the application by creating the the model, view, and controller to
	 * add shapes to, display shapes from, and to interface between the two
	 */
	private static void createApplication() {
		// initial setup
		JFrame frame = new JFrame("Homework 4");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 800);
		JPanel southPanel = new JPanel();

		DrawingModel model = new DrawingModel();
		Viewer panel = new Viewer();
		Controller controller = new Controller(model);

		// place radio selectors and reset button at the bottom of the panel
		// add a group for the selectors to have their states become mutually exclusive
		ButtonGroup group = new ButtonGroup();
		JRadioButton addLevelButton = new JRadioButton("add level");
		JRadioButton removeLevelButton = new JRadioButton("remove level");
		group.add(addLevelButton);
		group.add(removeLevelButton);
		southPanel.add(addLevelButton);
		southPanel.add(removeLevelButton);

		// start with addlevel when program executes
		addLevelButton.setSelected(true);
		addLevelButton.addActionListener(controller);
		addLevelButton.doClick();

		// get finishing touches ready for show-time
		JButton reset = new JButton("reset");
		reset.addActionListener(controller);
		southPanel.add(reset);
		removeLevelButton.addActionListener(controller);
		frame.add(southPanel, BorderLayout.SOUTH);
		panel.addMouseListener(controller);
		frame.add(panel);
		model.addView(panel);
		frame.setVisible(true);

		// create initial shapes
		model.addShape(new HShape(400, 150, Color.GREEN, 400));
		model.addShape(new FibonacciSquare(100, 450, 1, 0));
	}
}
