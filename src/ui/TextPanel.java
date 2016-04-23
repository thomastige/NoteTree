package ui;

import javax.swing.JTextPane;

import objects.CustomTreeNode;

public class TextPanel extends JTextPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean editMode = true;
	private CustomTreeNode nodeOnDisplay;
	
	
	public TextPanel(String text, CustomTreeNode node, boolean editMode) {
		super();
		this.setVisible(true);
		this.setText(text);
		this.editMode = editMode;
		this.nodeOnDisplay = node;
		
		setEditable(editMode);
	}

	public TextPanel() {
		this("");
	}

	public TextPanel(String content) {
		this(content, null, true);
	}

	public boolean getEditMode() {
		return editMode;
	}
	
	public CustomTreeNode getNodeOnDisplay(){
		return nodeOnDisplay;
	}
	
	public void setNodeOnDisplay(CustomTreeNode node){
		this.nodeOnDisplay = node;
	}

}
