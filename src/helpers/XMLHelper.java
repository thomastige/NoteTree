package helpers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import objects.CustomTreeNode;

public class XMLHelper {

	private static final String PROLOG = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> ";
	private static final String STRUCTURE_OPEN_TAG = "<structure>";
	private static final String STRUCTURE_CLOSE_TAG = "</structure>";
	private static final String TEXTFILES_OPEN_TAG = "<textFiles>";
	private static final String TEXTFILES_CLOSE_TAG = "</textFiles>";
	private static final String FOLDERS_OPEN_TAG = "<folders>";
	private static final String FOLDERS_CLOSE_TAG = "</folders>";

	public static String createStructureXML(List<CustomTreeNode> list) {
		StringBuilder result = new StringBuilder();
		result.append(PROLOG);
		result.append(STRUCTURE_OPEN_TAG);
		StringBuilder textFiles = new StringBuilder();
		StringBuilder folders = new StringBuilder();
		textFiles.append(TEXTFILES_OPEN_TAG);
		folders.append(FOLDERS_OPEN_TAG);
		Iterator<CustomTreeNode> it = list.iterator();
		while (it.hasNext()) {
			CustomTreeNode node = it.next();
			if (node.isFolderNode()) {
				String newEntry = "<folder id=\"" + node.getId() + "\" parent=\"" + node.getParentId() + "\" tag=\"" + node.getTag() + "\" fontColor=\""
						+ node.getFontColor()+ "\" background=\"" + node.getBackground() + "\"/>";
				folders.append(newEntry);
			} else if (node.isTextNode()) {
				String newEntry = "<textFile id=\"" + node.getId() + "\" parent=\"" + node.getParentId() + "\" tag=\"" + node.getTag() + "\" fontColor=\""
						+ node.getFontColor()+ "\" background=\"" + node.getBackground() + "\"/>";
				textFiles.append(newEntry);
			}
		}
		textFiles.append(TEXTFILES_CLOSE_TAG);
		folders.append(FOLDERS_CLOSE_TAG);
		result.append(folders.toString());
		result.append(textFiles.toString());
		result.append(STRUCTURE_CLOSE_TAG);

		return result.toString();
	}
	
	public static List<CustomTreeNode> readStructureXML(String path) throws ParserConfigurationException, SAXException, IOException{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(new File(path + File.separator + "structure.xml"));
		NodeList nodeList = document.getDocumentElement().getChildNodes();

		List<CustomTreeNode> nodes = new ArrayList<CustomTreeNode>();

		for (int i = 0; i < nodeList.getLength(); ++i) {
			Node parentNode = nodeList.item(i);
			if (parentNode instanceof Element) {
				NodeList childs = parentNode.getChildNodes();
				for (int j = 0; j < childs.getLength(); ++j) {
					Node node = childs.item(j);
					if (node instanceof Element) {
						String nodeName = node.getNodeName();
						if ("folder".equals(nodeName) || "textFile".equals(nodeName)) {
							NamedNodeMap namedNodeMap = node.getAttributes();
							String id = namedNodeMap.getNamedItem("id").getNodeValue();
							String parent = namedNodeMap.getNamedItem("parent").getNodeValue();
							String tag = namedNodeMap.getNamedItem("tag").getNodeValue();
							Node fontColorNode = namedNodeMap.getNamedItem("fontColor");
							String fontColor = "";
							String background = "";
							if (fontColorNode != null) {
								fontColor = fontColorNode.getNodeValue();
							}
							Node backgroundNode = namedNodeMap.getNamedItem("background");
							if (backgroundNode != null) {
								background = backgroundNode.getNodeValue();
							}
							if ("folder".equals(nodeName)) {
								nodes.add(new CustomTreeNode(id, parent, tag, fontColor, background, "folder"));
							} else if ("textFile".equals(nodeName)) {
								nodes.add(new CustomTreeNode(id, parent, tag, fontColor, background, "textFile"));
							}
						}

					}
				}
			}
		}
		return nodes;
	}
	
	public static String createStructure(){
		StringBuilder result = new StringBuilder();
		result.append(PROLOG);
		result.append(STRUCTURE_OPEN_TAG);
		StringBuilder textFiles = new StringBuilder();
		StringBuilder folders = new StringBuilder();
		textFiles.append(TEXTFILES_OPEN_TAG);
		folders.append(FOLDERS_OPEN_TAG);
		textFiles.append(TEXTFILES_CLOSE_TAG);
		folders.append(FOLDERS_CLOSE_TAG);
		result.append(folders.toString());
		result.append(textFiles.toString());
		result.append(STRUCTURE_CLOSE_TAG);

		return result.toString();
		
	}
}
