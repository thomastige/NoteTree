package hotkeys;

import javax.swing.ActionMap;

import actions.ActionStringConstants;
import actions.EditGenerateAction;
import actions.LoadProjectAction;
import actions.NewFolderNodeAction;
import actions.NewTextNodeAction;
import actions.SaveAsProjectAction;
import actions.SaveProjectAction;
import actions.TestAction;

class CommonActionMap extends ActionMap {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CommonActionMap() {
		super();

		put("doNothing", new TestAction());
		put(ActionStringConstants.GLOBAL_EDIT_GENERATE, new EditGenerateAction());
		put(ActionStringConstants.GLOBAL_NEW_TEXT_NODE, new NewTextNodeAction());
		put(ActionStringConstants.GLOBAL_NEW_FOLDER_NODE, new NewFolderNodeAction());
		put(ActionStringConstants.GLOBAL_SAVE_PROJECT, new SaveProjectAction());
		put(ActionStringConstants.GLOBAL_SAVE_AS_PROJECT, new SaveAsProjectAction());
		put(ActionStringConstants.GLOBAL_LOAD_PROJECT, new LoadProjectAction());
	}

}
