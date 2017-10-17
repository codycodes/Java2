import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/*
 * A simple graphics application using the java graphics libraries
 * @author CSC 143
 */

public class FirstSwingApplication {
	private static Random rand = new Random();
	
	public static void main(String[] args) {
		// not implementing the interface; just doing a runnable 
		SwingUtilities.invokeLater(new Runnable() {
		public void run() {
			createGUI();
			}
		});
	}

	public static void createGUI() {
		JFrame frame = new JFrame("First graphics application!");
		frame.setSize(1366, 768);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Add a label at the top of the frame
		JLabel label = new JLabel("WELCOME TO MY STORY");
		label.setFont(new Font("Encode Sans Normal", Font.BOLD, 100));
		label.setForeground(Color.PINK);
		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(Color.WHITE);
		centerPanel.add(label);
		centerPanel.add(label);
		frame.add(centerPanel, BorderLayout.CENTER);
		
		JButton button = new JButton("Draw!");
		JPanel southPanel = new JPanel();
		southPanel.setBackground(Color.WHITE);
		southPanel.add(button);
		frame.add(southPanel, BorderLayout.SOUTH);
		
		// Add a drawing panel in the middle of the frame
		JPanel drawingPanel = new JPanel() {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				int n = rand.nextInt(11) + 5;
				// vertices
				int[] x = new int[n];
				int[] y = new int[n];
				for (int i = 0; i < n; i++) {
					x[i] = rand.nextInt(getWidth());
					y[i] = rand.nextInt(getHeight());
				}
				Color color = new Color (rand.nextInt());
				g.setColor(color);
				g.fillPolygon(x, y, n);
			}
		};
		frame.setBackground(Color.WHITE);
	//	frame.add(drawingPanel);
		
		button.addActionListener(new ActionListener () {
			@Override
			public void actionPerformed(ActionEvent e) {
				drawingPanel.repaint();
			}

		});
		
		frame.setVisible(true); // recommended to do this last
	}

}
