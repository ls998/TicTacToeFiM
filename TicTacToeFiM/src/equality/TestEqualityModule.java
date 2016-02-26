package equality;

import java.util.ArrayList;
import java.util.List;

public class TestEqualityModule {
	public static void main(String[] args) {
		//test
		//test 2
		CharacterManager mgr = new CharacterManager();
		CharacterInfo twi = new CharacterInfo(new CharacterID(0));
		twi.addModule(new BasicCharacterInfo("twilight", new ArrayList<>()));
		CharacterInfo rd = new CharacterInfo(new CharacterID(1));
		rd.addModule(new BasicCharacterInfo("rainbow", new ArrayList<>()));

		mgr.addCharacter(rd);
		mgr.addCharacter(twi);

		List<CharacterID> gotty = mgr.getMatchingCharacters(new CharacterID(1));
		CharacterInfo c=mgr.getCharacter(gotty.get(0));
		BasicCharacterInfo stuff = (BasicCharacterInfo) c.getModule(BasicCharacterInfo.id);

		System.out.println(stuff.characterName);
	}
}
