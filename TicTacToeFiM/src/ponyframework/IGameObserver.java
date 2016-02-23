package ponyframework;

import java.util.List;

/**
 * Objects must implement this to be able to recieve game events
 * 
 * @author sunny
 *
 */
public interface IGameObserver{
	/**
	 * Gets called when the game begins
	 */
	public void onGameBegin();

	/**
	 * Gets called when any player makes a move
	 * 
	 * @param m
	 *            The move made
	 * @param playerid
	 *            The id of the player who made the move
	 */
	public void onMoveMade(Move m, int playerid);

	/**
	 * Gets called when players are eliminated
	 * 
	 * @param players
	 *            The player(s) that were eliminated
	 */
	public void onPlayersEliminated(List<Integer> players);

	/**
	 * Called when somepony/someponies win(s). This does not nessicarily mean
	 * the game ends
	 * 
	 * @param players
	 *            The players who won
	 */
	public void onPlayersWon(List<Integer> players);

	/**
	 * Called when two or more players tied. This does not nessacarily mean the
	 * game ends
	 * 
	 * @param players
	 *            The players who tied
	 */
	public void onPlayersTied(List<Integer> players);

	 /**
	 * Called when a new player joins the game
	 * @param player The id of the new player
	 */
	 public void onPlayerJoined(int player);
	
	 /**
	 * Called when a player leaves the game
	 * @param player The id of the player
	 */
	 public void onPlayerLeft(int player);
}