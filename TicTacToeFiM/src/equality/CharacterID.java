package equality;

public class CharacterID extends CharacterInfoModule {

	private static final long id = 9435628736528735l;

	private static final String name = "character id";

	private final long characterid;

	public CharacterID(long characterid) {
		super(id, name);
		this.characterid = characterid;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof CharacterID) {
			assert o instanceof CharacterID;
			if (((CharacterID) o).characterid == characterid)
				return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return (int) characterid;
	}
}
