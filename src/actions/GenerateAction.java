package actions;

import helpers.UIHelper;
import interpreter.Generator;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.text.BadLocationException;

import objects.CustomTreeNode;
import cache.TextFileCacheManager;

public class GenerateAction extends AbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void actionPerformed(ActionEvent event) {
		CustomTreeNode node = UIHelper.getTextArea().getNodeOnDisplay();
		String text = UIHelper.getTextArea().getText();
		TextFileCacheManager.put(node.getId(), text);

		Generator generator = new Generator(UIHelper.getTextArea().getText());
		try {
			UIHelper.getRootFrame().setNewTextArea(generator.generate());
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		UIHelper.toggleGenerateButton();
	}

}
