package ponyframework;

/**
 * Represents any move in a tic tac toe game (does not have to be legal)
 * 
 * @author sunny
 *
 */
public class Move {
	/**
	 * The x location of the move
	 */
	public final int x;

	/**
	 * The y location of the move
	 */
	public final int y;

	/**
	 * Create a new move
	 * 
	 * @param x
	 *            The x location of the move
	 * @param y
	 *            The y location of the move
	 */
	public Move(int x, int y) {
		this.x = x;
		this.y = y;
	}
}