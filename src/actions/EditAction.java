package actions;

import helpers.FileHelper;
import helpers.UIHelper;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import objects.CustomTreeNode;
import ui.TextPanel;
import cache.TextFileCacheManager;

public class EditAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3269083819046665139L;

	@Override
	public void actionPerformed(ActionEvent e) {
		CustomTreeNode node = UIHelper.getStructurePanel().getSelectedNode();
		String id = node.getId();
		if (id != null) {
			String text = TextFileCacheManager.getValue(id);
			if (text == null) {
				String path = FileHelper.getNodePath(node);
				text = FileHelper.getFileContentAsString(path);
			}
			UIHelper.getRootFrame().setNewTextArea(new TextPanel(text, node, true));
		}
		UIHelper.toggleGenerateButton();
	}

}
