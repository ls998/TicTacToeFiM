package ponyframework.defaultRules;

import java.util.ArrayList;
import java.util.List;

import ponyframework.GameState;
import ponyframework.WinRules;

public class TieRule implements WinRules {

	private static final List<Integer> empty = new ArrayList<>();

	@Override
	public List<Integer> whoWins(GameState currentState) {
		return empty;
	}

	@Override
	public List<Integer> whoTies(GameState currentState) {
		for (int i = 0; i < currentState.rootGame.width; i++) {
			for (int j = 0; j < currentState.rootGame.height; j++) {
				if (currentState.getBoardValue(i, j) == -1)
					return empty;
			}
		}
		return currentState.rootGame.getPlayerids();
	}

	@Override
	public List<Integer> whoEliminated(GameState currentState) {
		// TODO Auto-generated method stub
		return empty;
	}

	@Override
	public String description() {
		return "If there are no more spaces on the board, all players tie";
	}

}
