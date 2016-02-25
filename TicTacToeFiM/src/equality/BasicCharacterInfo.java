package equality;

import java.util.List;
import java.util.Random;

public class BasicCharacterInfo extends CharacterInfoModule {

	public static final long id = 3175613242l;

	private static final String name = "basic info";

	public final String characterName;

	private final List<String> winSayings;

	private final Random rng;

	public BasicCharacterInfo(String characterName, List<String> winSayings) {
		super(id, name);
		this.characterName = characterName;
		this.winSayings = winSayings;
		rng = new Random();
	}

	public String getWinSaying() {
		return winSayings.get(rng.nextInt(winSayings.size()));
	}
}
