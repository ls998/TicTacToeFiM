package equality;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CharacterInfo {
	private final Map<Long, CharacterInfoModule> modules;
	public final CharacterID id;

	public CharacterInfo(CharacterID id) {
		modules = new HashMap<>();
		this.id = id;
		addModule(id);
	}

	public CharacterInfoModule getModule(long id) {
		return modules.get(id);
	}

	public void addModule(CharacterInfoModule info) {
		modules.put(info.id, info);
	}

	public List<Long> getLoadedModules() {
		return Collections.unmodifiableList(new ArrayList<>(modules.keySet()));
	}
}
