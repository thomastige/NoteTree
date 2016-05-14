package ui;

import helpers.FileHelper;
import helpers.LocalizationHelper;
import helpers.NavigationHelper;
import helpers.ZipHelper;
import helpers.property.GlobalPropertiesHelper;
import interpreter.Generator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.text.BadLocationException;
import javax.swing.tree.TreePath;
import javax.xml.parsers.ParserConfigurationException;

import objects.CustomTreeNode;

import org.xml.sax.SAXException;

import cache.SessionCacheManager;
import cache.StructureCacheManager;
import cache.TextFileCacheManager;

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

	private JMenuItem getNewButton() {
		JMenuItem newMenuItem = new JMenuItem(NEW);
		
		newMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String location = FileHelper.createNewCache();
					SessionCacheManager.setCacheFolderLocation(location);
					NavigationHelper.getRootFrame().reload();
					
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (ParserConfigurationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SAXException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		return newMenuItem;
	}

	private JMenuItem getLoadButton() {
		JMenuItem loadMenuItem = new JMenuItem(LOAD);
		loadMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fc = new JFileChooser();
				int selection = fc.showOpenDialog(GlobalMenu.this);
				if (selection == JFileChooser.OPEN_DIALOG) {
					File file = fc.getSelectedFile();
					String fileName = file.getName();
					fileName = fileName.substring(0, fileName.lastIndexOf('.'));
					ZipHelper.unZip(file.getAbsolutePath(), GlobalPropertiesHelper.getWorkingCopyCacheLocation() + File.separator + fileName);
					SessionCacheManager.setProjectName(fileName);
					SessionCacheManager.setSaveFileLocation(file.getPath());
					SessionCacheManager.setCacheFolderLocation(FileHelper.getCacheFolderLocation() + File.separator + fileName);
					try {
						StructureCacheManager.reloadCache();
					} catch (ParserConfigurationException | SAXException | IOException e1) {
						e1.printStackTrace();
					}
					try {
						NavigationHelper.getRootFrame().reload();
					} catch (ParserConfigurationException | SAXException | IOException e) {
						e.printStackTrace();
					}
				}
			}

		});
		return loadMenuItem;
	}

	private JMenuItem getCloseButton() {
		JMenuItem closeMenuItem = new JMenuItem(LocalizationHelper.getLocalizedString("CLOSE_PROJECT"));
		closeMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FileHelper.deleteOpenCache();
				SessionCacheManager.clearSession();
				try {
					NavigationHelper.getRootFrame().reload();
				} catch (ParserConfigurationException | SAXException | IOException e1) {
					e1.printStackTrace();
				}

			}
		});
		return closeMenuItem;
	}

	private JMenuItem getSaveButton() {
		JMenuItem saveMenuItem = new JMenuItem(SAVE);
		saveMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String location = SessionCacheManager.getSaveFileLocation();
				if (location == null) {
					JFileChooser fc = new JFileChooser();
					int selection = fc.showSaveDialog(GlobalMenu.this);
					if (selection == JFileChooser.APPROVE_OPTION) {
						File selectedFile = fc.getSelectedFile();
						FileHelper.saveToZip(FileHelper.getFileName(selectedFile.getPath()));
					}
				} else {
					FileHelper.saveToZip(location);
				}
			}
		});

		return saveMenuItem;
	}

	private JMenuItem getSaveToCacheButton() {
		JMenuItem saveToCacheMenuItem = new JMenuItem(SAVE_TO_CACHE);
		saveToCacheMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				TreePath[] treePaths = NavigationHelper.getTreeObject().getSelectionPaths();
				for (int i = 0; i < treePaths.length; ++i) {
					TreePath treePath = treePaths[i];
					CustomTreeNode node = (CustomTreeNode) treePath.getLastPathComponent();
					try {
						FileHelper.saveTextFile(node.getId(), (NavigationHelper.getTextArea().getText()));
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
		});
		return saveToCacheMenuItem;
	}

	private JMenuItem getSaveAsButton() {
		JMenuItem saveAsMenuItem = new JMenuItem(SAVE_AS);
		saveAsMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				int selection = fc.showSaveDialog(GlobalMenu.this);
				if (selection == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fc.getSelectedFile();
					String fileName = FileHelper.getFileName(selectedFile.getPath());
					FileHelper.saveToZip(fileName);
					SessionCacheManager.setSaveFileLocation(fileName);
				}
			}
		});
		return saveAsMenuItem;
	}

	private JMenuItem getSaveStructureButton() {
		JMenuItem saveStructureMenuItem = new JMenuItem(LocalizationHelper.getLocalizedString("SAVE_STRUCTURE"));
		saveStructureMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					FileHelper.saveStructure();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}

			}
		});
		return saveStructureMenuItem;

	}

	private JMenu getProcessSubMenu() {
		JMenu processMenu = new JMenu(LocalizationHelper.getLocalizedString("SUBMENU_PROCESS"));

		processMenu.add(getGenerateButton());
		processMenu.add(getEditButton());

		return processMenu;
	}

	private JMenuItem getGenerateButton() {
		JMenuItem generateMenuItem = new JMenuItem(LocalizationHelper.getLocalizedString("PROCESS_GENERATE"));

		generateMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				CustomTreeNode node = NavigationHelper.getTextArea().getNodeOnDisplay();
				String text = NavigationHelper.getTextArea().getText();
				TextFileCacheManager.put(node.getId(), text);

				Generator generator = new Generator(NavigationHelper.getTextArea().getText());
				try {
					NavigationHelper.getRootFrame().setNewTextArea(generator.generate());
				} catch (BadLocationException e) {
					e.printStackTrace();
				}
			}

		});

		return generateMenuItem;
	}

	private JMenuItem getEditButton() {
		JMenuItem editMenuItem = new JMenuItem(LocalizationHelper.getLocalizedString("PROCESS_EDIT"));

		editMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent paramActionEvent) {
				CustomTreeNode node = NavigationHelper.getStructurePanel().getSelectedNode();
				String text = TextFileCacheManager.getValue(node.getId());
				if (text == null) {
					String path = FileHelper.getNodePath(node);
					text = FileHelper.getFileContentAsString(path);
				}
				NavigationHelper.getRootFrame().setNewTextArea(new TextPanel(text, node, true));
			}

		});

		return editMenuItem;

	}
	
	private JMenu getNodeSubMenu(){
		JMenu nodeMenu = new JMenu(LocalizationHelper.getLocalizedString("NODE_MENU"));
		nodeMenu.add(addNewTextNodeButton());
		nodeMenu.add(addNewFolderNodeButton());
		nodeMenu.add(addEditNodeButton());
		nodeMenu.add(addDeleteNodeButton());
		return nodeMenu;
	}
	
	private JMenuItem addEditNodeButton(){
		JMenuItem editNodeMenuItem = new JMenuItem(LocalizationHelper.getLocalizedString("EDIT_NODE"));
		
		editNodeMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String newValue= JOptionPane.showInputDialog(LocalizationHelper.getLocalizedString("EDIT_NODE_MESSAGE"));
				TreePath path = NavigationHelper.getTreeObject().getSelectionPath();
				try {
					CustomTreeNode node =((CustomTreeNode)path.getLastPathComponent()); 
					node.setTag(newValue);
					StructureCacheManager.put(node);
					NavigationHelper.getRootFrame().reload();
				} catch (ParserConfigurationException | SAXException | IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		return editNodeMenuItem;
	}

	private JMenuItem addNewTextNodeButton(){
		JMenuItem addNewTextNodeMenuItem = new JMenuItem(LocalizationHelper.getLocalizedString("ADD_TEXT_NODE"));

		addNewTextNodeMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String parent = "0";
				CustomTreeNode selectedNode = NavigationHelper.getStructurePanel().getSelectedNode();
				if (selectedNode != null && selectedNode.isFolderNode()){
					parent = selectedNode.getId();
				}
				String name = JOptionPane.showInputDialog(LocalizationHelper.getLocalizedString("SET_NODE_MESSAGE"));
				CustomTreeNode node = new CustomTreeNode(""+((int)StructureCacheManager.getHighestId()+1), parent, name, "", "", "textFile");
				StructureCacheManager.put(node);
				try {
					NavigationHelper.getRootFrame().reload();
				} catch (ParserConfigurationException | SAXException | IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		return addNewTextNodeMenuItem;
	}
	
	private JMenuItem addNewFolderNodeButton(){
		JMenuItem addNewFolderNodeMenuItem = new JMenuItem(LocalizationHelper.getLocalizedString("ADD_FOLDER_NODE"));
		
		addNewFolderNodeMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String parent = "0";
				CustomTreeNode selectedNode = NavigationHelper.getStructurePanel().getSelectedNode();
				if (selectedNode != null && selectedNode.isFolderNode()){
					parent = selectedNode.getId();
				}
				String name = JOptionPane.showInputDialog(LocalizationHelper.getLocalizedString("SET_NODE_MESSAGE"));
				CustomTreeNode node = new CustomTreeNode(""+((int)StructureCacheManager.getHighestId()+1), parent, name, "", "", "folder");
				StructureCacheManager.put(node);
				try {
					NavigationHelper.getRootFrame().reload();
				} catch (ParserConfigurationException | SAXException | IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		return addNewFolderNodeMenuItem;
	}
	
	private JMenuItem addDeleteNodeButton(){
		JMenuItem deleteNodeMenuItem = new JMenuItem(LocalizationHelper.getLocalizedString("DELETE_NOTE"));
		deleteNodeMenuItem.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent paramActionEvent) {
				CustomTreeNode selectedNode = NavigationHelper.getStructurePanel().getSelectedNode();
				selectedNode.removeFromParent();
				StructureCacheManager.remove(selectedNode);
				try {
					FileHelper.deleteNodeFromCache(selectedNode);
					NavigationHelper.getRootFrame().reload();
				} catch (ParserConfigurationException | SAXException | IOException e) {
					e.printStackTrace();
				}
			}
			
		});
		
		return deleteNodeMenuItem;
	}
	
}
