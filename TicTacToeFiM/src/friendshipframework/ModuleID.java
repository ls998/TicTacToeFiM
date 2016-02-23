package friendshipframework;

/**
 * Used to identify different modules
 * 
 * @author sunny
 *
 */
public abstract class ModuleID {
	/**
	 * The unique serial id of the module
	 */
	public final long serialid;

	/**
	 * The human readable name of the module
	 */
	public final String name;

	public ModuleID(String name, long serialid) {
		this.name = name;
		this.serialid = serialid;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof ModuleID) {
			assert o instanceof ModuleID;
			if (((ModuleID) o).serialid == serialid)
				return true;
		}
		return false;
	}
}
