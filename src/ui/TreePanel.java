package ui;

import handler.CustomTreeTransferHandler;
import hotkeys.TreePanelActionMap;
import hotkeys.TreePanelInputMap;

import java.awt.Container;
import java.io.File;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.swing.DropMode;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.TransferHandler;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeSelectionModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import javax.xml.parsers.ParserConfigurationException;

import listeners.NodeSelectionListener;
import objects.CustomTreeNode;
import objects.comparator.NodeComparator;
import objects.renderer.CustomTreeCellRenderer;

import org.xml.sax.SAXException;

import cache.StructureCacheManager;

public class TreePanel extends JScrollPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int VIEWPORT = 0;
	public static final int JTREE = 0;

	public TreePanel(String directory, boolean vertical) throws ParserConfigurationException, SAXException, IOException {
		super();
		JTree tree = buildTree(new File(directory));
		
		this.add(tree);
		tree.setFocusable(true);
		setViewportView(tree);
		
		tree.setInputMap(JComponent.WHEN_FOCUSED, new TreePanelInputMap());
		tree.setActionMap(new TreePanelActionMap());
	}

	
	
	// TODO: clean that shit up
	private JTree buildTree(File file) throws ParserConfigurationException, SAXException, IOException {

		// List<CustomTreeNode> nodes =
		// XMLHelper.readStructureXML(file.getPath());
		List<CustomTreeNode> nodes = StructureCacheManager.getNodesAsList();

		Collections.sort(nodes, new NodeComparator());
		CustomTreeNode root = new CustomTreeNode(0, 0, "root", "", "", "folder");
		JTree tree = new JTree(addNodes(null, nodes, root));
		tree.setCellRenderer(new CustomTreeCellRenderer());
		tree.addTreeSelectionListener(new NodeSelectionListener());
		
		tree.setDragEnabled(true);
		tree.setTransferHandler(new CustomTreeTransferHandler());
		
		TreeSelectionModel selectionModel = new DefaultTreeSelectionModel();
		selectionModel.setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.setSelectionModel(selectionModel);
		return tree;
	}

	private DefaultMutableTreeNode addNodes(CustomTreeNode curTop, List<CustomTreeNode> nodes, CustomTreeNode currentNode) throws ParserConfigurationException,
			InvalidObjectException {
		if (curTop != null) {
			curTop.add(currentNode);
		}
		List<CustomTreeNode> list = new ArrayList<CustomTreeNode>();
		Iterator<CustomTreeNode> it = nodes.iterator();
		while (it.hasNext()) {
			CustomTreeNode curr = it.next();
			String parentId = curr.getParentId();
			if (parentId.equals(currentNode.getId())) {
				list.add(curr);
			}
		}
		List<CustomTreeNode> files = new ArrayList<CustomTreeNode>();
		it = list.iterator();
		while (it.hasNext()) {
			CustomTreeNode node = it.next();
			if ("folder".equals(node.getNodeType())) {
				addNodes(currentNode, nodes, node);
			} else if ("textFile".equals(node.getNodeType())) {
				files.add((CustomTreeNode) node);
			} else {
				throw new InvalidObjectException("Unexpected node type: " + node.getClass().getName());
			}

			Iterator<CustomTreeNode> filesIterator = files.iterator();
			while (filesIterator.hasNext()) {
				CustomTreeNode textNode = filesIterator.next();
				currentNode.add(textNode);
			}
		}

		return currentNode;
	}

	public JTree getTree() {
		JTree result = (JTree) ((Container) this.getComponent(VIEWPORT)).getComponent(JTREE);
		return result;
	}

	public CustomTreeNode getSelectedNode() {
		String path = "";
		JTree jtree = getTree();
		TreePath treePath = jtree.getSelectionPath();
		CustomTreeNode node = null;
		if (treePath != null) {
			node = (CustomTreeNode) treePath.getLastPathComponent();
		}
		return node;

	}

}
