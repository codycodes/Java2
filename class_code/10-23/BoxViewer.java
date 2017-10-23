import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class BoxViewer extends JPanel {

  // The box viewed by this viewer
  private Box box;

  /**
   * Creates a box viewer within a frame
   */
  public BoxViewer() {

    // The box

    // Create a frame for this panel
	  JFrame frame = new JFrame("Box viewer");
	  frame.setSize(1366, 768);
	  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	// Panel to display the box that goes in the frame 
	  frame.add(this);
	  frame.setVisible(true);
	  
	  box = new Box(getWidth() / 2, getHeight() / 2, getHeight(), getWidth(), Color.CYAN);
	  
    // A click on the frame adds an inner box to the box
	  
	  this.addMouseListener(new MouseAdapter() {
		  @Override
		  public void mousePressed(MouseEvent e) {
			  box.addInnerBox();
			  repaint();
		  }
	});
  }

  /**
   * Paints the contents of this viewer
   * @param g the graphics context to use.
   */
  public void paintComponent(Graphics g) {
	  super.paintComponent(g);
	  if (box != null) {
		  box.draw(g);
	  }
  }

  /**
   * Starts the application.
   * @param args the list of the command line parameters.
   */
  public static void main(String[] args) {
	  SwingUtilities.invokeLater(new Runnable() {
		
		@Override
		public void run() {
			new BoxViewer();			
		}
	});
  }
}














