package loginscreentest;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class CharacterMgr {

	private static final Map<Integer, String> table;
	private static final Map<String, Integer> reverse;
	private static final Map<Integer, URL> images;

	private static int user = 0;

	static {
		table = new HashMap<>();
		reverse = new HashMap<>();
		images = new HashMap<>();
		Class<CharacterMgr> c = CharacterMgr.class;
		add("DErpy", c.getResource("derpy.png"));
		add("Rainbow Dash The Awesome", c.getResource("dash.png"));
		add("Twilight Sparkle", c.getResource("twilight.png"));
		add("pie pinkie", c.getResource("pinkie.png"));
	}

	private static void add(String name, URL image) {
		table.put(user, name);
		reverse.put(name, user);
		images.put(user, image);
		user++;
	}

	public static String getName(int user) {
		return table.get(user);
	}

	public static int getUser(String name) {
		if (reverse.containsKey(name))
			return reverse.get(name);
		return -1;
	}

	public static URL getImage(int user) {
		return images.get(user);
	}
}
