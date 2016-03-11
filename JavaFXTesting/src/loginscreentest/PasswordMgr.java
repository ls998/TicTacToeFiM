package loginscreentest;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import static loginscreentest.CharacterMgr.*;

public class PasswordMgr {
	private static long a;
	private static long b;
	private static long c = 1;

	private static final Map<Integer, String> table;

	private static final Map<Integer, Long> authtokens;

	static {
		table = new HashMap<>();
		authtokens = new HashMap<>();
		table.put(getUser("DErpy"), "ksdj");
		table.put(getUser("Rainbow Dash The Awesome"), "imsoawesome");
		table.put(getUser("Twilight Sparkle"), "dk34f6");
		table.put(getUser("pie pinkie"), "letspartyljsjhasklh43897rhwqlefsasdkfjahsdkfjashlfkjahslfkjashdflkjshi");
		reset();
	}

	public static void reset() {
		Random rng = new Random();
		a = rng.nextLong();
		b = a;
	}

	public static long getToken(int user, String pass) {
		if (table.get(user) != null && table.get(user).equals(pass))
			return nextToken();
		return -1;
	}

	public static boolean isAuthed(int user, long token) {
		return authtokens.get(user) != null && authtokens.get(user) == token;
	}

	public static void deauth(int user) {
		authtokens.remove(user);
	}

	private static long nextToken() {
		a = f(a);
		b = f(f(b));
		if (b == a) {
			reset();
			return nextToken();
		}
		return a;
	}

	private static long f(long x) {
		return (x * x + c) % Long.MAX_VALUE;
	}
}
