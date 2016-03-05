package friendshipframework.ui;

import java.util.List;

import equality.CharacterID;
import equality.CharacterInfo;

/**
 * Abstraction for character select screen
 * 
 * @author sunny
 *
 */
public interface ICharacterSelect extends IScreen {
	public interface CharacterSelectedObserver {
		public void onCharacterSelected(CharacterID selected, ICharacterSelect source);
	}

	public void setCharacterSet(List<CharacterInfo> characters);

	public void setCharacterSelectedListener(CharacterSelectedObserver listener);
}
