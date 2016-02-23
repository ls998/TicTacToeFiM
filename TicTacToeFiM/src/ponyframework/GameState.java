package ponyframework;

/**
 * Represents a Tic Tac Toe game at a certain moment
 * 
 * @author sunny
 *
 */
public class GameState {
	/**
	 * The game that this GameState is from
	 */
	public final Game rootGame;

	private final int[][] currentBoard;

	/**
	 * The id of the player who is about to play
	 */
	public final int currentPlayer;

	public GameState(Game rootGame, int[][] currentBoard, int currentPlayer) {
		this.rootGame = rootGame;
		this.currentBoard = currentBoard;
		this.currentPlayer = currentPlayer;
	}

	/**
	 * Returns a value of a location on the board
	 * @param x The x of the location
	 * @param y The y of the location
	 * @return
	 */
	public int getBoardValue(int x, int y) {
		return currentBoard[x][y];
	}
}