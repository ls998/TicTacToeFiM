package friendshipframework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import equality.CharacterID;
import equality.CharacterManager;
import ponyframework.Game;
import ponyframework.IPlayerController;

public class TicTacToe {
	private final Map<Integer, IPlayerController> playerControllers;
	private final Map<Integer, CharacterID> playerCharacters;
	private final List<IGameObserver> observers;
	public final CharacterManager characterMgr;

	public final Game game;

	public TicTacToe(Game game) {
		playerControllers = new HashMap<>();
		observers = new ArrayList<>();
		this.game = game;
		characterMgr = new CharacterManager();
		playerCharacters = new HashMap<>();
	}

	public void addObservers(IGameObserver observer) {
		observers.add(observer);
	}

	public void removeObservers(IGameObserver observer) {
		observers.remove(observer);
	}

	public void addPlayer(int player, IPlayerController playerController, CharacterID character) {
		playerControllers.put(player, playerController);
		playerCharacters.put(player, character);
	}

	public void removePlayer(int player) {
		playerControllers.remove(player);
	}
	
	public void nextTurn(){
		
	}
}
