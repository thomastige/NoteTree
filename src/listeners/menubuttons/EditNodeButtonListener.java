package listeners.menubuttons;

import helpers.LocalizationHelper;
import helpers.UIHelper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.tree.TreePath;
import javax.xml.parsers.ParserConfigurationException;

import objects.CustomTreeNode;

import org.xml.sax.SAXException;

import cache.StructureCacheManager;

public class EditNodeButtonListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		String newValue = JOptionPane.showInputDialog(LocalizationHelper.getLocalizedString("EDIT_NODE_MESSAGE"));
		if (newValue != null) {
			TreePath path = UIHelper.getTreeObject().getSelectionPath();
			try {
				CustomTreeNode node = ((CustomTreeNode) path.getLastPathComponent());
				node.setTag(newValue);
				StructureCacheManager.put(node);
				UIHelper.getRootFrame().reload();
			} catch (ParserConfigurationException | SAXException | IOException e1) {
				e1.printStackTrace();
			}
		}
	}

}
