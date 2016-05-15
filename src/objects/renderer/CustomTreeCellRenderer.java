package objects.renderer;

import helpers.property.GlobalPropertiesHelper;

import java.awt.Color;
import java.awt.Component;

import javax.management.InvalidAttributeValueException;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.tree.DefaultTreeCellRenderer;

import objects.CustomTreeNode;

public class CustomTreeCellRenderer extends DefaultTreeCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

		if (value instanceof CustomTreeNode) {
			CustomTreeNode node = (CustomTreeNode) value;
			if ("folder".equals(((CustomTreeNode)value).getNodeType())) {
				setIcon(UIManager.getIcon("FileView.directoryIcon"));
			} else {
				setIcon(UIManager.getIcon("FileView.fileIcon"));
			}
			String color = node.getFontColor();
			if (!"".equals(color)) {
				try {
					this.setForeground(getColor(color));
				} catch (InvalidAttributeValueException e) {
					e.printStackTrace();
				}
			}

			String background = node.getBackground();
			if (!"".equals(background) && !sel) {
				try {
					this.setBackground(getColor(background));
					this.setOpaque(true);
				} catch (InvalidAttributeValueException e) {
					e.printStackTrace();
				}
			} else {
				setOpaque(false);
			}
			String selectedColor = GlobalPropertiesHelper.getSelectedNodeColor();
			try {
				this.setBackgroundSelectionColor(getColor(selectedColor));
			} catch (InvalidAttributeValueException e) {
				e.printStackTrace();
			}

		}
		return this;
	}

	private Color getColor(String value) throws InvalidAttributeValueException {
		int[] colorValues = getColorAsIntArray(value);
		return new Color(colorValues[0], colorValues[1], colorValues[2]);

	}

	private int[] getColorAsIntArray(String value) throws InvalidAttributeValueException {
		if (value.length() != 9) {
			throw new InvalidAttributeValueException("Invalid value. A color cannot be derived from value " + value);
		}
		int value1 = Integer.parseInt(value.substring(0, 3));
		int value2 = Integer.parseInt(value.substring(3, 6));
		int value3 = Integer.parseInt(value.substring(6, 9));

		return new int[] { value1, value2, value3 };

	}

}
