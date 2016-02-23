package ponyframework.defaultRules;

import ponyframework.GameState;
import ponyframework.Move;
import ponyframework.MoveRule;

public class TakenRule implements MoveRule {

	@Override
	public boolean isMoveLegal(Move m, GameState currentState, int player) {
		return currentState.getBoardValue(m.x, m.y) == -1;
	}

	@Override
	public String description() {
		return "Players may not take spots that are already taken";
	}

}