package ponyframework;

import java.util.List;

/**
 * Any rule that determines whether a player wins/is eliminated is determined
 * here
 * 
 * @author sunny
 * 
 *         NOTE - Players who are eliminated cannot win - Players who are tied,
 *         but win by another rule win
 */
public interface WinRules {
	/**
	 * Determines winners of the game (if any)
	 * 
	 * @param currentState
	 *            The current state of the game
	 * @return The list of players who have won (if any)
	 */
	public List<Integer> whoWins(GameState currentState);

	/**
	 * Determines who tied (if any)
	 * 
	 * @param currentState
	 *            The current state of the game
	 * @return The list of players who have tied (if any)
	 */
	public List<Integer> whoTies(GameState currentState);

	/**
	 * Determines who is eliminated (if any)
	 * 
	 * @param currentState
	 *            The current state of the game
	 * @return The is of players who should be eliminated (if any)
	 */
	public List<Integer> whoEliminated(GameState currentState);

	/**
	 * Should give a description of the rule
	 * 
	 * @return The rule description
	 */
	public String description();
}