import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public abstract class AbstractPiece {
	
	protected boolean ableToMove; // can this piece move

	protected Square[] square; // the squares that make up this piece

	// Made up of PIECE_COUNT squares
	protected Grid grid; // the board this piece is on

	// number of squares in one Tetris game piece
	protected static final int PIECE_COUNT = 4;
	
	public AbstractPiece(Grid g) {
		this.grid = g;
		square = new Square[PIECE_COUNT];
		ableToMove = true;
	}
	
	
	public void draw(Graphics g) {
		for (int i = 0; i < PIECE_COUNT; i++) {
			square[i].draw(g);
		}
	}
	
	public void move(Direction direction) {
		//System.out.println("move(direction) called from the LShape class! You pressed " + direction);
		if (canMove(direction)) {
			for (int i = 0; i < PIECE_COUNT; i++)
				square[i].move(direction);
		}
		// if we couldn't move, see if because we're at the bottom
		else if (direction == Direction.DOWN) {
			ableToMove = false;
		}
	}
	
	public Point[] getLocations() {
		Point[] points = new Point[PIECE_COUNT];
		for (int i = 0; i < PIECE_COUNT; i++) {
			points[i] = new Point(square[i].getRow(), square[i].getCol());
		}
		return points;
	}

	/**
	 * Return the color of this piece
	 */
	public Color getColor() {
		// all squares of this piece have the same color
		return square[0].getColor();
	}
	
	/**
	 * Returns if this piece can move in the given direction
	 * 
	 */
	public boolean canMove(Direction direction) {
		if (!ableToMove)
			return false;

		// Each square must be able to move in that direction
		boolean answer = true;
		for (int i = 0; i < PIECE_COUNT; i++) {
			answer = answer && square[i].canMove(direction);
		}

		return answer;
	}
	
	/**
	 * Rotates the piece a quarter-turn in the clockwise direction
	 */
	public void rotatePiece() {
		if (canRotate()) {
			// write a loop that calls square class and always send square[1] as parameter
			for (Square s : square) {
				s.rotate(square[1]);
			}
		}
	}
	public boolean canRotate() {
		for (Square s : square) {
			if (!s.canRotate(square[1])) {
				return false;
			}
		}
		return true;
	}
	
}
