package actions;

import helpers.LocalizationHelper;
import helpers.UIHelper;

import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;

import objects.CustomTreeNode;

import org.xml.sax.SAXException;

import cache.StructureCacheManager;

public class NewFolderNodeAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7254517218629704000L;

	@Override
	public void actionPerformed(ActionEvent event) {
		String parent = "0";
		CustomTreeNode selectedNode = UIHelper.getStructurePanel().getSelectedNode();
		if (selectedNode != null && selectedNode.isFolderNode()) {
			parent = selectedNode.getId();
		}
		String name = JOptionPane.showInputDialog(LocalizationHelper.getLocalizedString("SET_FOLDER_NODE_MESSAGE"));
		if (name != null) {
			CustomTreeNode node = new CustomTreeNode("" + ((int) StructureCacheManager.getHighestId() + 1), parent, name, "", "", CustomTreeNode.FOLDER_NODE);
			StructureCacheManager.put(node);
			try {
				UIHelper.getRootFrame().reload();
			} catch (ParserConfigurationException | SAXException | IOException e1) {
				e1.printStackTrace();
			}
		}
	}

}
