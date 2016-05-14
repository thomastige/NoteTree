package handler;

import helpers.FileHelper;
import helpers.NavigationHelper;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.io.IOException;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JTree;
import javax.swing.TransferHandler;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.xml.parsers.ParserConfigurationException;

import objects.CustomTreeNode;

import org.xml.sax.SAXException;

import cache.StructureCacheManager;

/**
 * Modified version of the classes available at
 * http://www.developpez.net/forums/
 * d74142/java/interfaces-graphiques-java/awt-swing
 * /composants/arbres/jtree-drag-and-drop/
 * 
 * @author Thomas Tige
 *
 */
public class CustomTreeTransferHandler extends TransferHandler {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Last imported tree node.
	 */
	private CustomTreeNode importedNode = null;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Icon getVisualRepresentation(Transferable t) {
		return super.getVisualRepresentation(t);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Transferable createTransferable(JComponent component) {
		String entity = exportEntity(component);
		return new StringSelection(entity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void exportDone(JComponent c, Transferable data, int action) {
		cleanup(c, action == MOVE);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getSourceActions(JComponent component) {
		int result = NONE;
		JTree tree = (JTree) component;
		TreePath selectionPath = tree.getSelectionPath();
		if (selectionPath != null) {
			Object lastPathComponent = selectionPath.getLastPathComponent();
			if (lastPathComponent instanceof CustomTreeNode) {
				result = COPY_OR_MOVE;
			}
		}
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean canImport(JComponent component, DataFlavor[] flavors) {
		for (DataFlavor f : flavors) {
			if (DataFlavor.stringFlavor.equals(f)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean importData(JComponent component, Transferable t) {
		boolean result = false;
		if (canImport(component, t.getTransferDataFlavors())) {
			try {
				result = importEntity((JTree) component, importedNode);
				NavigationHelper.getRootFrame().reload();
			} catch (IOException ioe) {
				System.err.println(ioe);
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * Exports an entity from a component.
	 * 
	 * @param component
	 *            The source component.
	 * @return The entity to be exported.
	 */
	public String exportEntity(JComponent component) {
		JTree tree = (JTree) component;
		Object lastPathComponent = tree.getSelectionPath().getLastPathComponent();
		if (lastPathComponent instanceof CustomTreeNode) {
			CustomTreeNode node = (CustomTreeNode) lastPathComponent;
			importedNode = node;
		}
		return null;
	}

	/**
	 * Imports a string to a component.
	 * 
	 * @param component
	 *            The target component.
	 * @param importedNode
	 *            The node to import.
	 * @return <code>True</code> if import was successfull, <code>false</code>
	 *         otherwise.
	 */
	public boolean importEntity(JComponent component, CustomTreeNode importedNode) {
		JTree tree = (JTree) component;
		TreePath lastPath = tree.getSelectionPath();
		CustomTreeNode destination;
		if (lastPath != null) {
			destination = (CustomTreeNode) lastPath.getLastPathComponent();
		} else {
			destination = (CustomTreeNode) NavigationHelper.getTreeObject().getModel().getRoot();
		}
		if (destination instanceof CustomTreeNode) {
			CustomTreeNode targetNode = (CustomTreeNode) destination;
			((CustomTreeNode) importedNode).setParentId(((CustomTreeNode) targetNode).getId());
			try {
				FileHelper.saveStructure();
				StructureCacheManager.reloadCache();
			} catch (IOException | ParserConfigurationException | SAXException e) {
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}

	/**
	 * Cleanup after export.
	 * 
	 * @param component
	 *            The source component.
	 * @param remove
	 *            <CODE>True</CODE> if the selection should be removed from the
	 *            component; <CODE>false</CODE> otherwise.
	 */
	protected void cleanup(JComponent component, boolean remove) {
		JTree tree = (JTree) component;
		// Remove string node from parent node.
		if (remove) {
			DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
			model.removeNodeFromParent(importedNode);
		}
		// Clean references.
		importedNode = null;
	}

}
