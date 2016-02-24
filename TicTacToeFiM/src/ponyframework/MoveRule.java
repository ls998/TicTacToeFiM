package ponyframework;

/**
 * Any rule that determines whether a move is legal must be added here
 * 
 * @author sunny
 *
 */
public interface MoveRule {
	/**
	 * Should return true if the move follows rule, otherwise return false
	 * 
	 * @param m
	 *            The move
	 * @param currentState
	 *            The currentstate on which the move is applied
	 * @param player
	 *            The player making the move
	 * @return
	 */
	public boolean isMoveLegal(Move m, GameState currentState, int player);

	/**
	 * Should give a description of the rule
	 * 
	 * @return The rule description
	 */
	public String description();
}