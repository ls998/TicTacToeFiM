package ponyframework;

import ponyframework.defaultRules.RowWinRule;
import ponyframework.defaultRules.TakenRule;
import ponyframework.defaultRules.TurnRule;
import test.GameEventLogger;
import test.Log;

//stuff for testing the game framework
public class TestGameFrameWork {

	public static void main(String[] args) {
		// init
		Game g = new Game(3, 3);

		// gamebuilder.reset test
		Game.GameBuilder.reset(g);
		Log.debug.println("reset test");
		printgamedat(g, " ");
		Log.debug.println();

		// gamebuilder.custom test
		Game.GameBuilder.customBoard(g, new int[][] { { -1, 0, 2 }, { 1, 0, -1 }, { 1, 2, -1 } });
		Log.debug.println("custom test");
		printgamedat(g, " ");
		Log.debug.println();

		// playerlist test
		Log.debug.println("playerlist test");
		g.addPlayer(0);
		g.addPlayer(1);
		g.addPlayer(2);
		g.winsize = 3;
		g.selectNextPlayer();
		printgamedat(g, " ");
		Log.debug.println();

		// serialization test
		Log.debug.println("serialization test");
		String derp = Game.GameBuilder.saveToString(g);
		Log.debug.println(" serialized: " + derp);
		Game g2 = Game.GameBuilder.loadFromString(derp);
		Log.debug.println(" deserialized");
		printgamedat(g2, " ");
		Log.debug.println();

		// islegalmove test
		Log.debug.println("moverules test");
		g.moveRules.add(new TurnRule());
		g.moveRules.add(new TakenRule());
		Log.debug.println(" player 0 -> 0,0");
		printgamerule(g.isLegalMove(new Move(0, 0), 0), "  ");
		Log.debug.println(" player 1 -> 0,0");
		printgamerule(g.isLegalMove(new Move(0, 0), 1), "  ");
		Log.debug.println("player 0 -> 0,1");
		printgamerule(g.isLegalMove(new Move(0, 1), 0), "  ");
		Log.debug.println();

		// winrule and event handler test
		Game.GameBuilder.customBoard(g, new int[][] { { -1, -1, 0 }, { -1, 0, -1 }, { 0, -1, -1 } });
		g.addEventObserver(new GameEventLogger());
		Log.debug.println("win test");
		printgamedat(g, " ");
		g.winRules.add(new RowWinRule());
		g.checkWinners();
		g.performMove(new Move(0, 0), g.currentPlayer);
		g.selectNextPlayer();
		g.performMove(new Move(0, 1), g.currentPlayer);
		g.selectNextPlayer();
		g.performMove(new Move(0, 2), g.currentPlayer);
		g.selectNextPlayer();
		g.performMove(new Move(1, 0), g.currentPlayer);
		printgamedat(g, " ");
		Log.debug.println();

		
	}

	public static void printgamerule(MoveRule rule, String indent) {
		if (rule == null) {
			Log.debug.println(indent + "[none]");
		} else {
			Log.debug.println(indent + rule.description());
		}
	}

	public static void printgamerule(WinRules rule, String indent) {
		if (rule == null) {
			Log.debug.println(indent + "[none]");
		} else {
			Log.debug.println(indent + rule.description());
		}
	}

	public static void printgamedat(Game g, String indent) {
		Log.debug.println(indent + "players:");
		if (g.getPlayerids().isEmpty())
			Log.debug.println(indent + " [empty]");
		for (int id : g.getPlayerids()) {
			Log.debug.println(indent + " " + id);
		}
		Log.debug.println(indent + "currentplayer=" + g.currentPlayer);
		Log.debug.println(indent + "gameboard:");
		for (int i = 0; i < g.width; i++) {
			Log.debug.print(indent);
			for (int j = 0; j < g.width; j++) {
				Log.debug.printf("%3d", g.getCurrentGameState().getBoardValue(i, j));
			}
			Log.debug.println();
		}
	}

}