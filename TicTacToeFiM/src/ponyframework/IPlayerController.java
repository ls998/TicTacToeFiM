package ponyframework;

/**
 * The interface that all player controllers must follow
 * 
 * @author sunny
 *
 */
public interface IPlayerController {
	/**
	 * Receive your id
	 * 
	 * @param id
	 *            Your player id
	 */
	public void playAs(int id);

	/**
	 * Make a move
	 * 
	 * @param currentGameState
	 *            The current game state
	 * @return Your move
	 */
	public Move GetMove(GameState currentGameState);
}