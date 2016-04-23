package objects.comparator;

import java.util.Comparator;

import objects.CustomTreeNode;

public class NodeComparator implements Comparator<CustomTreeNode> {

	@Override
	public int compare(CustomTreeNode o1, CustomTreeNode o2) {
		int result = 0;
		String o1Parent = o1.getParentId();
		String o2Parent = o2.getParentId();
		if ("".equals(o2Parent)){
			result = 1;
		} else if ("".equals(o1Parent)) {
			result = 0;
		} else {
		 result = Integer.parseInt(o1Parent) - Integer.parseInt(o2Parent);
		}
		return result;
	}

}
