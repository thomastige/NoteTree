package hotkeys;

import actions.ActionStringConstants;
import actions.DeleteNodeAction;


public class TreePanelActionMap extends CommonActionMap {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TreePanelActionMap(){
		super();
		put(ActionStringConstants.TREE_DELETE_NOTE, new DeleteNodeAction());
	}

}
