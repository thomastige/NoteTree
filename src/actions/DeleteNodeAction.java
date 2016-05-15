package actions;

import helpers.FileHelper;
import helpers.UIHelper;

import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.xml.parsers.ParserConfigurationException;

import objects.CustomTreeNode;

import org.xml.sax.SAXException;

import cache.StructureCacheManager;

public class DeleteNodeAction extends AbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7008554372916561534L;

	@Override
	public void actionPerformed(ActionEvent event) {

		CustomTreeNode selectedNode = UIHelper.getStructurePanel().getSelectedNode();
		selectedNode.removeFromParent();
		StructureCacheManager.remove(selectedNode);
		try {
			FileHelper.deleteNodeFromCache(selectedNode);
			UIHelper.getRootFrame().reload();
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}		
	}
	
	

}
