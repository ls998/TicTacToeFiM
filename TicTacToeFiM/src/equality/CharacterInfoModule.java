package equality;

/**
 * Wrapper for character info module id
 * 
 * @author sunny
 *
 */
public abstract class CharacterInfoModule {
	/**
	 * The id of this module (should be the same across all instances of this
	 * class)
	 */
	public final long id;

	/**
	 * Human readable name of this module (should be the same across all
	 * instances of this class)
	 */
	public final String name;

	public CharacterInfoModule(long id, String name) {
		this.id = id;
		this.name = name;
	}
}