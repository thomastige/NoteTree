package hotkeys;

import javax.swing.ActionMap;

import actions.TestAction;

class CommonActionMap extends ActionMap {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CommonActionMap() {
		super();

		put("doNothing", new TestAction());
	}

}
