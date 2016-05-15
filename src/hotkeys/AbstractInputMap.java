package hotkeys;

import helpers.property.HotkeyPropertiesHelper;

import javax.swing.InputMap;
import javax.swing.KeyStroke;

abstract class AbstractInputMap extends InputMap{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4848398783436232599L;

	protected void putActionMapping(String action){
		KeyStroke keyStroke = KeyStroke.getKeyStroke(HotkeyPropertiesHelper.getHotkey(action));
		put(keyStroke, action);
	}
	
}
