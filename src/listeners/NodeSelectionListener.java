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
import cache.TextFileCacheManager;
import objects.CustomTreeNode;
import ui.TextPanel;

public class NodeSelectionListener implements TreeSelectionListener {

	public void valueChanged(TreeSelectionEvent event) {
		JTree jtree = (JTree) (event.getSource());
		CustomTreeNode node = (CustomTreeNode) jtree.getSelectionPath().getLastPathComponent();
		if (node.isTextNode()) {
			String path = SessionCacheManager.getCacheFolderLocation() + File.separator + node.getId() + ".txt";
			CustomTreeNode previous = NavigationHelper.getTextArea().getNodeOnDisplay();
			if (previous != null) {
				TextFileCacheManager.put(previous.getId(), NavigationHelper.getTextArea().getText());
			}
			String fileContent = TextFileCacheManager.getValue(node.getId());
			if (fileContent == null) {
				fileContent = FileHelper.getFileContentAsString(path);
			}
			if (GlobalPropertiesHelper.getgenerateOnFileLoad()) {
				Generator generator = new Generator(fileContent);
				try {
					NavigationHelper.getRootFrame().setNewTextArea(generator.generate());
				} catch (BadLocationException e) {
					e.printStackTrace();
				}
			} else {
				NavigationHelper.getRootFrame().setNewTextArea(new TextPanel(fileContent));
			}
			NavigationHelper.getTextArea().setNodeOnDisplay(node);
		}
	}

}
