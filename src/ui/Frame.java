package ui;

import helpers.NavigationHelper;
import helpers.property.GlobalPropertiesHelper;
import helpers.property.SessionRestoreHelper;
import hotkeys.MainActionMap;
import hotkeys.MainInputMap;

import java.awt.Component;
import java.awt.Container;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import cache.SessionCacheManager;

public class Frame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final int TEXT_AREA = 2;
	public static final int LIST_PANEL = 1;

	public Frame() throws ParserConfigurationException, SAXException, IOException {
		this("");
	}

	public Frame(String label) throws ParserConfigurationException, SAXException, IOException {
		super(label);
		genericSetUp();
	}

	private void genericSetUp() throws ParserConfigurationException, SAXException, IOException {
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	try {
					SessionRestoreHelper.dumpSession();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
		    	System.exit(0);
		    }
		});
		GlobalMenu gm = new GlobalMenu();
		this.setJMenuBar(gm);
		String location = SessionCacheManager.getCacheFolderLocation();

		if (location != null && new File(location).exists()) {
			Component c1 = new TreePanel(SessionCacheManager.getCacheFolderLocation(), true);
			Component c2 = new TextPanel();

			JSplitPane gui = new JSplitPane();
			gui.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
			gui.setDividerLocation(GlobalPropertiesHelper.getInitialDividerLocation());
			gui.setLeftComponent(c1);
			gui.setRightComponent(c2);

			gui.setInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT, new MainInputMap());
			gui.setActionMap(new MainActionMap());
			this.setContentPane(gui);
		}
		this.pack();
		this.setVisible(true);
		this.setSize(GlobalPropertiesHelper.getInitialX(), GlobalPropertiesHelper.getInitialY());
	}

	public void reload() throws ParserConfigurationException, SAXException, IOException{
		this.setContentPane(new Container());
		String location = SessionCacheManager.getCacheFolderLocation();
		if (location != null && new File(location).exists()) {
			Component c1 = new TreePanel(SessionCacheManager.getCacheFolderLocation(), true);
			Component c2 = new TextPanel();

			JSplitPane gui = new JSplitPane();
			gui.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
			gui.setDividerLocation(GlobalPropertiesHelper.getInitialDividerLocation());
			gui.setLeftComponent(c1);
			gui.setRightComponent(c2);

			gui.setInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT, new MainInputMap());
			gui.setActionMap(new MainActionMap());
			this.setContentPane(gui);
		}
		revalidate();
		repaint();
	}
	
	public void setNewTextArea(TextPanel textPanel) {
		((JSplitPane) getContentPane()).setRightComponent(textPanel);
		NavigationHelper.getSplitPane().setDividerLocation(GlobalPropertiesHelper.getInitialDividerLocation());

	}

}
