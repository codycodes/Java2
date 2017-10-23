import javax.swing.JPanel;
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

    // A click on the frame adds an inner box to the box
  }

  /**
   * Paints the contents of this viewer
   * @param g the graphics context to use.
   */
  public void paintComponent(Graphics g) {
  }

  /**
   * Starts the application.
   * @param args the list of the command line parameters.
   */
  public static void main(String[] args) {
    new BoxViewer();
  }
}














