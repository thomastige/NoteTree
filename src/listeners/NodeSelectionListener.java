package listeners;

import helpers.FileHelper;
import helpers.NavigationHelper;
import helpers.property.GlobalPropertiesHelper;
import interpreter.Generator;

import java.io.File;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.text.BadLocationException;

import cache.SessionCacheManager;
import objects.CustomTreeNode;
import ui.TextPanel;

public class NodeSelectionListener implements TreeSelectionListener {

	public void valueChanged(TreeSelectionEvent event) {
		JTree jtree = (JTree) (event.getSource());
		CustomTreeNode node = (CustomTreeNode) jtree.getSelectionPath().getLastPathComponent();
		if ("textFile".equals(node.getNodeType())) {
			String path = SessionCacheManager.getCacheFolderLocation() + File.separator + node.getId() + ".txt";

			if (GlobalPropertiesHelper.getgenerateOnFileLoad()) {
				Generator generator = new Generator(FileHelper.getFileContentAsString(path));
				try {
					NavigationHelper.getRootFrame().setNewTextArea(generator.generate());
				} catch (BadLocationException e) {
					e.printStackTrace();
				}
			} else {
				NavigationHelper.getRootFrame().setNewTextArea(new TextPanel(FileHelper.getFileContentAsString(path)));
			}
		}
	}

}
