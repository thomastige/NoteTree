package objects;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import javax.swing.tree.DefaultMutableTreeNode;

public class CustomTreeNode extends DefaultMutableTreeNode implements Transferable{

	/**
	 * 
	 */
	
	public static final String TEXT_NODE = "textFile";
	public static final String FOLDER_NODE = "folder";
	
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String parentId;
	private String tag;
	private String fontColor;
	private String background;
	private String nodeType;


	public String getNodeType() {
		return nodeType;
	}

	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public CustomTreeNode(int id, int parent, String tag, String fontColor, String background, String nodeType) {
		this.id = id + "";
		this.parentId = parent + "";
		this.tag = tag;
		this.fontColor = fontColor;
		this.background = background;
		this.nodeType = nodeType;
	}

	public CustomTreeNode(String id, String parent, String tag, String fontColor, String background, String nodeType) {
		this.id = id;
		this.parentId = parent;
		this.tag = tag;
		this.fontColor = fontColor;
		this.background = background;
		this.nodeType = nodeType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parent) {
		this.parentId = parent;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getFontColor() {
		return fontColor;
	}

	public void setFontColor(String fontColor) {
		this.fontColor = fontColor;
	}

	public String getBackground() {
		return background;
	}

	public void setBackground(String background) {
		this.background = background;
	}

	public String toString(){
		return tag;
	}
	
	public boolean isFolderNode(){
		return "folder".equals(nodeType);
	}
	
	public boolean isTextNode(){
		return "textFile".equals(nodeType);
	}

	@Override
	public DataFlavor[] getTransferDataFlavors() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isDataFlavorSupported(DataFlavor paramDataFlavor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object getTransferData(DataFlavor paramDataFlavor) throws UnsupportedFlavorException, IOException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
