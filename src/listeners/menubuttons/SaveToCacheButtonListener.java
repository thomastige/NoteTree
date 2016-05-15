package listeners.menubuttons;

import helpers.FileHelper;
import helpers.UIHelper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.tree.TreePath;

import objects.CustomTreeNode;

public class SaveToCacheButtonListener implements ActionListener{



	@Override
	public void actionPerformed(ActionEvent e) {
		TreePath[] treePaths = UIHelper.getTreeObject().getSelectionPaths();
		for (int i = 0; i < treePaths.length; ++i) {
			TreePath treePath = treePaths[i];
			CustomTreeNode node = (CustomTreeNode) treePath.getLastPathComponent();
			try {
				FileHelper.saveTextFile(node.getId(), (UIHelper.getTextArea().getText()));
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			try {
				FileHelper.saveStructure();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		}
	}
	
}
