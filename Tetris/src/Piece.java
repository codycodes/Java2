import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public interface Piece {
	void rotatePiece(); // any class that extends AbstractPiece must implement the inherited abstract method
	// TODO: check this later
	void draw(Graphics g);
	boolean canMove(Direction direction);
	public void move(Direction direction);
	public Point[] getLocations();
	public Color getColor();
	
	
}
