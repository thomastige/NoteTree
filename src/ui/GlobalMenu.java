package ui;

import helpers.LocalizationHelper;
import helpers.property.HotkeyPropertiesHelper;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import actions.ActionStringConstants;
import listeners.menubuttons.CloseButtonListener;
import listeners.menubuttons.DeleteNodeButtonActionListener;
import listeners.menubuttons.EditButtonActionListener;
import listeners.menubuttons.EditNodeButtonListener;
import listeners.menubuttons.GenerateButtonActionListener;
import listeners.menubuttons.LoadButtonListener;
import listeners.menubuttons.NewButtonListener;
import listeners.menubuttons.NewFolderActionListener;
import listeners.menubuttons.NewTextNodeButtonListener;
import listeners.menubuttons.SaveAsButtonListener;
import listeners.menubuttons.SaveButtonListener;
import listeners.menubuttons.SaveStructureButtonListener;
import listeners.menubuttons.SaveToCacheButtonListener;

public class GlobalMenu extends JMenuBar {

	private static final long serialVersionUID = 1L;
	private static final String FILE = LocalizationHelper.getLocalizedString("MENU_FILE");
	private static final String NEW = LocalizationHelper.getLocalizedString("MENU_NEW");
	private static final String SAVE = LocalizationHelper.getLocalizedString("MENU_SAVE");
	private static final String SAVE_AS = LocalizationHelper.getLocalizedString("MENU_SAVE_AS");
	private static final String SAVE_TO_CACHE = LocalizationHelper.getLocalizedString("MENU_SAVE_TO_CACHE");
	private static final String LOAD = LocalizationHelper.getLocalizedString("MENU_LOAD");

	public GlobalMenu() {
		addMenuItems();
	}

	private void addMenuItems() {
		this.add(getMenuItemSubMenu());
		this.add(getProcessSubMenu());
		this.add(getNodeSubMenu());
	}

	private JMenu getMenuItemSubMenu() {
		JMenu fileMenu = new JMenu(FILE);
		fileMenu.add(getNewButton());
		fileMenu.add(getSaveButton());
//		fileMenu.add(getSaveToCacheButton());
//		fileMenu.add(getSaveStructureButton());
		fileMenu.add(getSaveAsButton());
		fileMenu.add(getLoadButton());
		fileMenu.add(getCloseButton());
		return fileMenu;
	}
	
	private JMenu getProcessSubMenu() {
		JMenu processMenu = new JMenu(LocalizationHelper.getLocalizedString("SUBMENU_PROCESS"));
		processMenu.add(getGenerateButton());
		processMenu.add(getEditButton());
		return processMenu;
	}
	
	private JMenu getNodeSubMenu(){
		JMenu nodeMenu = new JMenu(LocalizationHelper.getLocalizedString("NODE_MENU"));
		nodeMenu.add(addNewTextNodeButton());
		nodeMenu.add(addNewFolderNodeButton());
		nodeMenu.add(addEditNodeButton());
		nodeMenu.add(addDeleteNodeButton());
		return nodeMenu;
	}


	private JMenuItem getNewButton() {
		JMenuItem newMenuItem = new JMenuItem(NEW);
		newMenuItem.setAccelerator(KeyStroke.getKeyStroke(HotkeyPropertiesHelper.getHotkey(ActionStringConstants.GLOBAL_NEW_PROJECT)));
		newMenuItem.addActionListener(new NewButtonListener());
		return newMenuItem;
	}

	private JMenuItem getLoadButton() {
		JMenuItem loadMenuItem = new JMenuItem(LOAD);
		loadMenuItem.setAccelerator(KeyStroke.getKeyStroke(HotkeyPropertiesHelper.getHotkey(ActionStringConstants.GLOBAL_LOAD_PROJECT)));
		loadMenuItem.addActionListener(new LoadButtonListener());
		return loadMenuItem;
	}

	private JMenuItem getCloseButton() {
		JMenuItem closeMenuItem = new JMenuItem(LocalizationHelper.getLocalizedString("CLOSE_PROJECT"));
		closeMenuItem.addActionListener(new CloseButtonListener());
		return closeMenuItem;
	}

	private JMenuItem getSaveButton() {
		JMenuItem saveMenuItem = new JMenuItem(SAVE);
		saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(HotkeyPropertiesHelper.getHotkey(ActionStringConstants.GLOBAL_SAVE_PROJECT)));
		saveMenuItem.addActionListener(new SaveButtonListener());

		return saveMenuItem;
	}

	private JMenuItem getSaveToCacheButton() {
		JMenuItem saveToCacheMenuItem = new JMenuItem(SAVE_TO_CACHE);
		saveToCacheMenuItem.addActionListener(new SaveToCacheButtonListener());
		return saveToCacheMenuItem;
	}

	private JMenuItem getSaveAsButton() {
		JMenuItem saveAsMenuItem = new JMenuItem(SAVE_AS);
		saveAsMenuItem.setAccelerator(KeyStroke.getKeyStroke(HotkeyPropertiesHelper.getHotkey(ActionStringConstants.GLOBAL_SAVE_AS_PROJECT)));
		saveAsMenuItem.addActionListener(new SaveAsButtonListener());
		return saveAsMenuItem;
	}

	private JMenuItem getSaveStructureButton() {
		JMenuItem saveStructureMenuItem = new JMenuItem(LocalizationHelper.getLocalizedString("SAVE_STRUCTURE"));
		saveStructureMenuItem.addActionListener(new SaveStructureButtonListener());
		return saveStructureMenuItem;
	}

	private JMenuItem getGenerateButton() {
		JMenuItem generateMenuItem = new JMenuItem(LocalizationHelper.getLocalizedString("PROCESS_GENERATE"));
		generateMenuItem.setAccelerator(KeyStroke.getKeyStroke(HotkeyPropertiesHelper.getHotkey(ActionStringConstants.GLOBAL_EDIT_GENERATE)));
		generateMenuItem.addActionListener(new GenerateButtonActionListener());
		generateMenuItem.setEnabled(false);
		return generateMenuItem;
	}

	private JMenuItem getEditButton() {
		JMenuItem editMenuItem = new JMenuItem(LocalizationHelper.getLocalizedString("PROCESS_EDIT"));
		editMenuItem.setAccelerator(KeyStroke.getKeyStroke(HotkeyPropertiesHelper.getHotkey(ActionStringConstants.GLOBAL_EDIT_GENERATE)));
		editMenuItem.addActionListener(new EditButtonActionListener());
		editMenuItem.setEnabled(false);
		return editMenuItem;

	}
	
	private JMenuItem addEditNodeButton(){
		JMenuItem editNodeMenuItem = new JMenuItem(LocalizationHelper.getLocalizedString("EDIT_NODE"));
		editNodeMenuItem.addActionListener(new EditNodeButtonListener());
		return editNodeMenuItem;
	}

	private JMenuItem addNewTextNodeButton(){
		JMenuItem addNewTextNodeMenuItem = new JMenuItem(LocalizationHelper.getLocalizedString("ADD_TEXT_NODE"));
		addNewTextNodeMenuItem.setAccelerator(KeyStroke.getKeyStroke(HotkeyPropertiesHelper.getHotkey(ActionStringConstants.GLOBAL_NEW_TEXT_NODE)));
		addNewTextNodeMenuItem.addActionListener(new NewTextNodeButtonListener());
		return addNewTextNodeMenuItem;
	}
	
	private JMenuItem addNewFolderNodeButton(){
		JMenuItem addNewFolderNodeMenuItem = new JMenuItem(LocalizationHelper.getLocalizedString("ADD_FOLDER_NODE"));
		addNewFolderNodeMenuItem.setAccelerator(KeyStroke.getKeyStroke(HotkeyPropertiesHelper.getHotkey(ActionStringConstants.GLOBAL_NEW_FOLDER_NODE)));
		addNewFolderNodeMenuItem.addActionListener(new NewFolderActionListener());
		return addNewFolderNodeMenuItem;
	}
	
	private JMenuItem addDeleteNodeButton(){
		JMenuItem deleteNodeMenuItem = new JMenuItem(LocalizationHelper.getLocalizedString("DELETE_NOTE"));
		deleteNodeMenuItem.setAccelerator(KeyStroke.getKeyStroke(HotkeyPropertiesHelper.getHotkey(ActionStringConstants.TREE_DELETE_NOTE)));
		deleteNodeMenuItem.addActionListener(new DeleteNodeButtonActionListener());
		return deleteNodeMenuItem;
	}
	
}
