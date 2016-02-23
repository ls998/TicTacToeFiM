package test;

import java.util.List;

import ponyframework.IGameObserver;
import ponyframework.Move;

public class GameEventLogger implements IGameObserver {

	@Override
	public void onPlayersWon(List<Integer> players) {
		Log.event.println("[event] players won:");
		if (players.isEmpty())
			Log.event.println(" [empty]");
		for (int id : players) {
			Log.event.println(" " + id);
		}
	}

	@Override
	public void onPlayersTied(List<Integer> players) {
		Log.event.println("[event] players tied:");
		if (players.isEmpty())
			Log.event.println(" [empty]");
		for (int id : players) {
			Log.event.println(" " + id);
		}
	}

	@Override
	public void onPlayersEliminated(List<Integer> players) {
		Log.event.println("[event] players eliminated:");
		if (players.isEmpty())
			Log.event.println(" [empty]");
		for (int id : players) {
			Log.event.println(" " + id);
		}
	}

	@Override
	public void onMoveMade(Move m, int playerid) {
		Log.event.println("[event] player " + playerid + " made move at " + m.x + ", " + m.y);
	}

	@Override
	public void onGameBegin() {
		Log.event.println("[event] game began");
	}

	@Override
	public void onPlayerJoined(int player) {
		Log.event.println("[event] player joined: " + player);
	}

	@Override
	public void onPlayerLeft(int player) {
		Log.event.println("[event] player left: " + player);
	}
}