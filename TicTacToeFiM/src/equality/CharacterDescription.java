package equality;

/**
 * For testing only
 * 
 * @author sunny
 *
 */

public class CharacterDescription extends CharacterInfoModule {

	private static final long id = 634976384263248l;
	private static final String name = "character description";

	public final String characterDescription;

	public CharacterDescription(String characterDesription) {
		super(id, name);
		this.characterDescription = characterDesription;
	}
}
