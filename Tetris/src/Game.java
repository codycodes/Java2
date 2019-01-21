import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 * Manages the game Tetris. Keeps track of the current piece and the grid.
 * Updates the display whenever the state of the game has changed.
 * 
 * @author CSC 143
 */
public class Game {

	private Grid grid; // the grid that makes up the Tetris board

	private Tetris display; // the visual for the Tetris game

	private Piece piece;
	
	
	
	
	private boolean isOver; // has the game finished?


	// create getRandomPiece()
	// every time I need a random piece I use randInt

	/**
	 * Creates a Tetris game
	 * 
	 * @param Tetris
	 *            the display
	 */
	public Game(Tetris display) {
		grid = new Grid();
		this.display = display;
		piece = createRandomPiece();
		isOver = false;
	}

	/**
	 * Draws the current state of the game
	 * 
	 * @param g
	 *            the Graphics context on which to draw
	 */
	public void draw(Graphics g) {
		grid.draw(g);
		if (piece != null) {
			piece.draw(g);
		}
	}

	/**
	 * Moves the piece in the given direction
	 * 
	 * @param the
	 *            direction to move
	 */
	public void movePiece(Direction direction) {
		if (piece != null) {
			switch(direction) {
				case DROP:
					while (piece.canMove(Direction.DOWN)) {
						piece.move(Direction.DOWN);
					}
					break;
					
				case ROTATE:
					piece.rotatePiece();
					break;
					
				default:
					piece.move(direction);
					break;		
			}
		}

		updatePiece();
		display.update();
		grid.checkRows();
	}

	/**
	 * Moves the piece in the given direction
	 * 
	 * @param the
	 *            direction to move
	 */
	public void rotatePiece() {
		
	}
	
	/**
	 * Returns true if the game is over
	 */
	public boolean isGameOver() {
		// game is over if the piece occupies the same space as some non-empty
		// part of the grid. Usually happens when a new piece is made
		if (piece == null) {
			return false;
		}

		// check if game is already over
		if (isOver) {
			return true;
		}

		// check every part of the piece
		Point[] p = piece.getLocations();
		for (int i = 0; i < p.length; i++) {
			if (grid.isSet((int) p[i].getX(), (int) p[i].getY())) {
				isOver = true;
				return true;
			}
		}
		return false;
	}

	/** Updates the piece */
	private void updatePiece() {
		if (piece == null) {
			// CREATE A NEW PIECE HERE -- COMPLETE
			//piece = new LShape(1, Grid.WIDTH / 2 - 1, grid);
			piece = createRandomPiece();
		}

		// set Grid positions corresponding to frozen piece
		// and then release the piece
		else if (!piece.canMove(Direction.DOWN)) {
			Point[] p = piece.getLocations();
			Color c = piece.getColor();
			for (int i = 0; i < p.length; i++) {
				grid.set((int) p[i].getX(), (int) p[i].getY(), c);
			}
			piece = null;
		}
	}
	/**
	 * Generates a new random piece object 
	 */
	private Piece createRandomPiece() {
		switch((int)(Math.random() * 7) + 1) {
		case 1:
			return new ZShape(1, Grid.WIDTH / 2 - 1, grid);
		case 2:
			return new SquareShape(1, Grid.WIDTH / 2 - 1, grid);
		case 3:
			return new JShape(1, Grid.WIDTH / 2 - 1, grid);
		case 4:
			return new TShape(1, Grid.WIDTH / 2 - 1, grid);
		case 5:
			return new SShape(1, Grid.WIDTH / 2 - 1, grid);
		case 6:
			return new BarShape(1, Grid.WIDTH / 2 - 1, grid);
		case 7:
			return new LShape(1, Grid.WIDTH / 2 - 1, grid);
		}
		return null;
//		return new SShape(1, Grid.WIDTH / 2 - 1, grid);

	}

}
