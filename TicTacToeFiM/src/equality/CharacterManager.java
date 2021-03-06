package equality;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CharacterManager {
	private final Map<CharacterID, CharacterInfo> characters;

	public CharacterManager() {
		characters = new HashMap<>();
	}

	public CharacterInfo getCharacter(CharacterID id) {
		return characters.get(id);
	}

	public <T extends CharacterInfoModule> List<CharacterID> getMatchingCharacters(T modulesearch) {
		ArrayList<CharacterID> result = new ArrayList<>();
		for (CharacterID id : characters.keySet()) {
			CharacterInfo info = getCharacter(id);
			for (Long moduleid : info.getLoadedModules()) {
				CharacterInfoModule module = info.getModule(moduleid);
				if (module != null)
					if (modulesearch.equals(module))
						result.add(id);
			}
		}
		return result;
	}

	public void addCharacter(CharacterInfo info) {
		if (characters.containsKey(info.id))
			throw new IllegalArgumentException("Character already exists");
		characters.put(info.id, info);
	}

	public void removeCharacter(CharacterID id) {
		characters.remove(id);
	}
}
