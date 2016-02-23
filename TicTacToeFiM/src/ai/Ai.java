
package ai;

import ponyframework.Game;
import ponyframework.GameState;
import ponyframework.IPlayerController;
import ponyframework.Move;

/**
 * @author fungucide
 */
public class Ai implements IPlayerController
{

	//private long	key;

	private int		id;

	public Move GetMove(GameState currentGameState)
	{
		Game game = currentGameState.rootGame;

		int[] count = new int[game.getNumberPlayers()];
		boolean[] row = new boolean[game.getNumberPlayers()];

		boolean[][] aiCheck = new boolean[game.width][game.height];
		boolean aiRow = false;
		int aiRowLength=0;

		boolean[][] visited = new boolean[game.width][game.height];
		int player;

		// Check to see if Ai can Win ==> Most important
		for (int i = 0; i < game.width; i++)
		{
			for (int j = 0; j < game.height; j++)
			{
				if (currentGameState.getBoardValue(i, j) == id)
				{
					if (!aiRow){
						aiRowLength=1;
					}else{
						 
					}
				}
			}
		}

		// Check for empty spaces
		for (int i = 0; i < game.width; i++)
		{
			for (int j = 0; j < game.height; j++)
			{
				int boardValue = currentGameState.getBoardValue(i, j);
				if (boardValue != -1 && boardValue != id)
				{
					visited[i][j] = true;
					// if ()
					// check for almost in a row
				}
			}
		}

		// move
		int movex = 0;
		int movey = 0;

		return new Move(movex, movey);
	}

	@Override
	public void playAs(int id)
	{
		this.id = id;
	}
}