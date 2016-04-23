package helpers;

import java.awt.Container;

import javax.swing.JSplitPane;
import javax.swing.JTree;

import ui.Frame;
import ui.TextPanel;
import ui.TreePanel;
import driver.Driver;

public class NavigationHelper {

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
	
	public static JTree getTreeObject(){
		return getStructurePanel().getTree();
	}

}
