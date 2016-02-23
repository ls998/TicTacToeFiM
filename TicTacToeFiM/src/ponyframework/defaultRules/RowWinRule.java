package ponyframework.defaultRules;

import java.util.ArrayList;
import java.util.List;

import ponyframework.GameState;
import ponyframework.WinRules;

/**
 * Players win when they get n stuff in a row Players may be included in the
 * list multiple times, if they make more than one row
 * 
 * @author sunny
 *
 */
public class RowWinRule implements WinRules {

	private static final List<Integer> empty = new ArrayList<>();

	@Override
	public List<Integer> whoWins(GameState currentState) {
		List<Integer> winners = new ArrayList<>();
		// boolean[][] visited = new
		// boolean[currentState.rootGame.width][currentState.rootGame.height];
		for (int x = 0; x < currentState.rootGame.width; x++) {
			for (int y = 0; y < currentState.rootGame.height; y++) {
				int boardValue = currentState.getBoardValue(x, y);
				if (boardValue != -1) {
					boolean flag;
					// horizontal
					flag = true;
					for (int i = 0; i < currentState.rootGame.winsize; i++) {
						if (i + x >= currentState.rootGame.width
								|| currentState.getBoardValue(x + i, y) != boardValue) {
							flag = false;
							break;
						}
					}
					if (flag)
						winners.add(boardValue);

					// vertical
					flag = true;
					for (int i = 0; i < currentState.rootGame.winsize; i++) {
						if (y + i >= currentState.rootGame.height
								|| currentState.getBoardValue(x, y + i) != boardValue) {
							flag = false;
							break;
						}
					}
					if (flag)
						winners.add(boardValue);

					// diagnal1
					flag = true;
					for (int i = 0; i < currentState.rootGame.winsize; i++) {
						if (Math.max(i + x, i + y) >= Math.min(currentState.rootGame.height,
								currentState.rootGame.width)
								|| currentState.getBoardValue(x + i, y + i) != boardValue) {
							flag = false;
							break;
						}
					}
					if (flag)
						winners.add(boardValue);

					// diagnal2
					flag = true;
					for (int i = 0; i < currentState.rootGame.winsize; i++) {
						if (x - i < 0 || y + i >= currentState.rootGame.height
								|| currentState.getBoardValue(x - i, y + i) != boardValue) {
							flag = false;
							break;
						}
					}
					if (flag)
						winners.add(boardValue);
				}
			}
		}
		return winners;
	}

	@Override
	public List<Integer> whoTies(GameState currentState) {
		return empty;
	}

	@Override
	public List<Integer> whoEliminated(GameState currentState) {
		return empty;
	}

	@Override
	public String description() {
		return "Whoever gets n in a row, horizonatally, vertically, or diagnally, wins";
	}

}