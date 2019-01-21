import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

public class Controller extends ComponentAdapter implements ActionListener, MouseListener {

	// the model that contains the Drawing data
	private DrawingModel model;

	// used in actionPerformed to carry out commands for add and remove level
	private boolean addLevel = false;

	public Controller(DrawingModel model) {
		this.model = model;
	}

	/**
	 * Receives an event
	 * 
	 * @param e
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if (command.equals("add level")) {
			addLevel = true;
		} else if (command.equals("remove level")) {
			addLevel = false;
		} else {
			// reset
			model.reset();
		}
	}

	/**
	 * Used exclusively for radio buttons addLevel and removeLevel
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		if (addLevel) {
			// if this is false, cannot add another level
			if (!model.addLevel(e.getX(), e.getY())) {

				JOptionPane.showMessageDialog(null,
						"Oh boy. I bet you want to add another level of recursion to the shape you just clicked on.\n"
								+ "Well I'm sorry, but you can't! I don't make the rules.\n"
								+ "(∩ º ‿‿ º )⊃━☆ﾟ.* You do!",
						"Inane warning", JOptionPane.WARNING_MESSAGE);
			}
		} else {
			if (!model.removeLevel(e.getX(), e.getY())) {
				JOptionPane.showMessageDialog(null,
						"Your level is too low!\n"
								+ "Hey! What did you really think was going to happen, huh?! ¯\\_(ツ)_/¯",
						"Inane warning", JOptionPane.WARNING_MESSAGE);
			}
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}
}
