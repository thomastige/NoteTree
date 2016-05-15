package helpers;

import java.awt.Container;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSplitPane;
import javax.swing.JTree;

import ui.Frame;
import ui.TextPanel;
import ui.TreePanel;
import driver.Driver;

public class UIHelper {

	private static final int TEXT_AREA = 2;
	private static final int LIST_PANEL = 1;
	// TODO: find name of these two
	private static final int ROOT_COMPONENT_0 = 0;
	private static final int ROOT_COMPONENT_1 = 1;
	private static final int ROOT_COMPONENT_2 = 1;

	public static Frame getRootFrame() {
		return Driver.getFrame();
	}

	public static JSplitPane getSplitPane() {
		return (JSplitPane) ((Container) ((Container) getRootFrame().getComponent(ROOT_COMPONENT_0)).getComponent(ROOT_COMPONENT_1))
				.getComponent(ROOT_COMPONENT_2);
	}

	public static TextPanel getTextArea() {
		return (TextPanel) getSplitPane().getComponent(TEXT_AREA);
	}

	public static TreePanel getStructurePanel() {
		return (TreePanel) getSplitPane().getComponent(LIST_PANEL);
	}

	public static JTree getTreeObject() {
		return getStructurePanel().getTree();
	}

	public static JMenuBar getMenu() {
		return getRootFrame().getJMenuBar();
	}

	public static void toggleGenerateButton() {
		JMenuBar menuBar = getMenu();
		for (int i = 0; i < menuBar.getMenuCount(); ++i) {
			JMenu menu = menuBar.getMenu(i);
			if (LocalizationHelper.getLocalizedString("SUBMENU_PROCESS").equals(menu.getText())) {
				boolean editMode = getTextArea().isEditable();
				for (int j = 0; j < menu.getItemCount(); ++j) {
					JMenuItem menuItem = menu.getItem(j);
					if (editMode) {
						if (LocalizationHelper.getLocalizedString("PROCESS_EDIT").equals(menuItem.getText())) {
							menuItem.setEnabled(false);
						}
						if (LocalizationHelper.getLocalizedString("PROCESS_GENERATE").equals(menuItem.getText())) {
							menuItem.setEnabled(true);
						}
					} else {
						if (LocalizationHelper.getLocalizedString("PROCESS_EDIT").equals(menuItem.getText())) {
							menuItem.setEnabled(true);
						}
						if (LocalizationHelper.getLocalizedString("PROCESS_GENERATE").equals(menuItem.getText())) {
							menuItem.setEnabled(false);
						}
					}
				}
			}

		}
	}

	public static boolean isEditMode() {
		return getTextArea().isEditable();
	}

}
