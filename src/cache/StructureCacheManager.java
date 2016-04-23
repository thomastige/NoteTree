package cache;

import helpers.XMLHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import objects.CustomTreeNode;

import org.xml.sax.SAXException;

public class StructureCacheManager {

	private static Map<String, CustomTreeNode> structureCache = new HashMap<String, CustomTreeNode>();

	public static void put(CustomTreeNode node) {
		structureCache.put(node.getId(), node);
	}

	public static CustomTreeNode getNode(String id) {
		return structureCache.get(id);
	}

	public static List<CustomTreeNode> getNodesAsList() {
		Collection<CustomTreeNode> values = structureCache.values();
		List<CustomTreeNode> list = new ArrayList<CustomTreeNode>(values);
		return list;
	}

	public static void reloadCache() throws ParserConfigurationException, SAXException, IOException {
		structureCache.clear();

		List<CustomTreeNode> list = XMLHelper.readStructureXML(SessionCacheManager.getCacheFolderLocation());
		Iterator<CustomTreeNode> it = list.iterator();
		while (it.hasNext()) {
			put(it.next());
		}
		System.out.println(list);
		System.out.println(structureCache.values());

	}

//	private static List<CustomTreeNode> getNodeList(List<CustomTreeNode> list, CustomTreeNode curTop) {
//		int childCount = curTop.getChildCount();
//		for (int i = 0; i < childCount; ++i) {
//			CustomTreeNode node = (CustomTreeNode) curTop.getChildAt(i);
//			list.add(node);
//			if (node.isFolderNode()) {
//				getNodeList(list, node);
//			}
//		}
//		return list;
//	}
	
	public static int getNodeCount(){
		return structureCache.values().size();
	}
}
