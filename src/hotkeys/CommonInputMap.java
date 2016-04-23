package hotkeys;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.InputMap;
import javax.swing.KeyStroke;

class CommonInputMap extends InputMap{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CommonInputMap() {
		KeyStroke keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, InputEvent.CTRL_DOWN_MASK);
		put(keyStroke, "doNothing");
	}
		
	
}
