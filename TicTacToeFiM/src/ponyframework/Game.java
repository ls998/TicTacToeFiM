package ponyframework;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import test.Log;

/**
 * Represents a whole Tic Tac Toe game, handles game logic
 * 
 * @author sunny
 *
 */
public class Game {
	/**
	 * Utility class to help create and configure TicTacToe games
	 * 
	 * @author sunny
	 *
	 */
	public static class GameBuilder {
		/**
		 * Resets a game to all -1
		 * 
		 * @param game
		 *            The game to reset
		 */
		public static void reset(Game game) {
			for (int i = 0; i < game.width; i++) {
				for (int j = 0; j < game.height; j++) {
					game.currentBoard[i][j] = -1;
				}
			}
		}

		/**
		 * Loads a game from a string where a game has been previously stored
		 * 
		 * @param s
		 *            The string to load the game from
		 */
		public static Game loadFromString(String str) {
			// Scan the string
			Scanner s = new Scanner(new ByteArrayInputStream(str.getBytes()));
			int w = s.nextInt();
			int h = s.nextInt();
			Game g = new Game(w, h);
			g.winsize = s.nextInt();
			g.currentPlayer = s.nextInt();
			int players = s.nextInt();
			for (int i = 0; i < players; i++) {
				g.addPlayer(s.nextInt());
			}
			for (int i = 0; i < w; i++) {
				for (int j = 0; j < w; j++) {
					g.currentBoard[i][j] = s.nextInt();
				}
			}
			s.close();
			return g;
		}

		/**
		 * Saves a game into a string
		 * 
		 * @param game
		 *            The game to save
		 * @return The string that contains the game
		 */
		public static String saveToString(Game game) {
			StringBuilder sb = new StringBuilder();
			sb.append(game.width);
			sb.append(" ");
			sb.append(game.height);
			sb.append(" ");
			sb.append(game.winsize);
			sb.append(" ");
			sb.append(game.currentPlayer);
			sb.append(" ");
			sb.append(game.getNumberPlayers());
			sb.append(" ");
			for (int i = 0; i < game.getNumberPlayers(); i++) {
				sb.append(game.playerids.get(i));
				sb.append(" ");
			}
			for (int i = 0; i < game.width; i++) {
				for (int j = 0; j < game.height; j++) {
					sb.append(game.currentBoard[i][j]);
					sb.append(" ");
				}
			}
			return sb.toString();
		}

		/**
		 * Allows a custom board to be loaded into a game
		 * 
		 * @param g
		 *            The game into which to load the custom board
		 * @param board
		 *            The custom board
		 * @throws IllegalArgumentException
		 *             If board sizes do not match
		 */
		public static void customBoard(Game g, int[][] board) throws IllegalArgumentException {
			if (g.width != board.length)
				throw new IllegalArgumentException("Board sizes do not match");
			int cols = 0;
			if (board.length > 0)
				cols = board[0].length;
			if (g.height != cols)
				throw new IllegalArgumentException("Board sizes do not match");

			for (int x = 0; x < g.width; x++) {
				for (int y = 0; y < g.height; y++) {
					g.currentBoard[x][y] = board[x][y];
				}
			}
		}
	}

	/**
	 * The list of rules determining what moves are legal
	 */
	public final List<MoveRule> moveRules;

	/**
	 * The list of rules determining who wins/is eliminated (if any)
	 */
	public final List<WinRules> winRules;

	/**
	 * The current player id
	 */
	public int currentPlayer = -1;

	/**
	 * The width of the game (range of x)
	 */
	public final int width;

	/**
	 * The height of the game (range of y)
	 */
	public final int height;

	/**
	 * The number of marks in a row you need to win
	 */
	public int winsize;

	private final List<Integer> playerids;

	private final int[][] currentBoard;

	private List<IGameObserver> observers;

	/**
	 * Initializes the game with specified size
	 * 
	 * @param w
	 *            Width (range of x)
	 * @param h
	 *            Hieght (range of y)
	 */
	public Game(int w, int h) {
		this.width = w;
		this.height = h;
		playerids = new ArrayList<>();
		currentBoard = new int[w][h];
		observers = new ArrayList<>();
		moveRules = new ArrayList<>();
		winRules = new ArrayList<>();
	}

	/**
	 * Sets the currentplayer to the next player
	 */
	public void selectNextPlayer() {
		if (playerids.contains(currentPlayer)) {
			int currentPlayerIdx = playerids.indexOf(currentPlayer);
			currentPlayer = playerids.get((currentPlayerIdx + 1) % playerids.size());
		} else {
			if (playerids.size() > 0)
				currentPlayer = playerids.get(0);
			else
				Log.err.println("[WARN] No players in game, next player not selected");
		}
	}

	/**
	 * Determines if a move is legal for a certain player
	 * 
	 * @param m
	 *            The move
	 * @param player
	 *            The player making the move
	 * @return The violated rule, if the move is illegal, otherwise null
	 */
	public MoveRule isLegalMove(Move m, int player) {
		if (!playerids.contains(player))
			throw new IllegalArgumentException("Player does not exist");
		for (MoveRule rule : moveRules) {
			if (!rule.isMoveLegal(m, getCurrentGameState(), player))
				return rule;
		}
		return null;
	}

	/**
	 * Checks if players have won, been eliminated, or tied
	 */
	public void checkWinners() {
		GameState currentState = getCurrentGameState();

		List<Integer> eliminated = new ArrayList<>();
		for (WinRules rule : winRules) {
			List<Integer> tmp = rule.whoEliminated(currentState);
			for (int player : tmp) {
				// make sure list of winners does not contain duplicate
				if (!eliminated.contains(player))
					eliminated.add(player);
			}
		}
		playerids.removeAll(eliminated);
		if (!eliminated.isEmpty())
			for (IGameObserver o : observers) {
				o.onPlayersEliminated(eliminated);
			}

		List<Integer> winners = new ArrayList<>();
		for (WinRules rule : winRules) {
			List<Integer> tmp;
			tmp = rule.whoWins(currentState);
			for (int player : tmp) {
				// make sure list of winners does not contain duplicate
				if (!winners.contains(player))
					winners.add(player);
			}
		}
		if (!winners.isEmpty())
			for (IGameObserver o : observers) {
				o.onPlayersWon(winners);
			}

		List<Integer> ties = new ArrayList<>();
		for (WinRules rule : winRules) {
			List<Integer> tmp = rule.whoTies(currentState);
			for (int player : tmp) {
				// make sure list of winners does not contain duplicate
				if (!ties.contains(player))
					// tie only if player did not already win
					if (!winners.contains(player))
						ties.add(player);
			}
		}
		if (!ties.isEmpty())
			for (IGameObserver o : observers) {
				o.onPlayersTied(ties);
			}
	}

	/**
	 * Performs a move as a certain player
	 * 
	 * @param m
	 *            The move to perform
	 * @param player
	 *            The player to perform the move as
	 */
	public void performMove(Move m, int playerid) {
		if (!playerids.contains(playerid))
			throw new IllegalArgumentException("Invalid player id");

		if (playerid != currentPlayer)
			Log.err.println("[WARN] playerid does not match currentPlayer");

		currentBoard[m.x][m.y] = playerid;

		checkWinners();

		for (IGameObserver o : observers) {
			o.onMoveMade(m, playerid);
		}
	}

	/**
	 * Gets the current state of this game
	 * 
	 * @return A GameState object containing the current state of the game
	 */
	public GameState getCurrentGameState() {
		return new GameState(this, currentBoard, currentPlayer);
	}

	/**
	 * Add an event observer to the subscriber list
	 * 
	 * @param observer
	 *            The observer to add
	 */
	public void addEventObserver(IGameObserver observer) {
		observers.add(observer);
	}

	/**
	 * Removes an event observer from the subscriber list
	 * 
	 * @param observer
	 *            The observer to remove
	 */
	public void removeEventObserver(IGameObserver observer) {
		observers.remove(observer);
	}

	/**
	 * Wrapper method to get number of players
	 * 
	 * @return The number of players in this game
	 */
	public int getNumberPlayers() {
		return playerids.size();
	}

	/**
	 * Getter method for playerids
	 * 
	 * @return
	 */
	public List<Integer> getPlayerids() {
		return Collections.unmodifiableList(playerids);
	}

	public void addPlayer(int playerid) {
		if (playerids.contains(playerid))
			throw new IllegalArgumentException("ID is already taken");
		playerids.add(playerid);

	}

	public void removePlayer(int playerid) {
		if (!playerids.contains(playerid))
			throw new IllegalArgumentException("Player does not exist");
		playerids.remove(playerid);
	}
}