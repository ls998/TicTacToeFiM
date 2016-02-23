package ponyframework.defaultRules;

import ponyframework.GameState;
import ponyframework.Move;
import ponyframework.MoveRule;

public class TurnRule implements MoveRule {

	@Override
	public boolean isMoveLegal(Move m, GameState currentState, int player) {
		return currentState.currentPlayer == player;
	}

	@Override
	public String description() {
		return "Players can only play on their turn";
	}

}