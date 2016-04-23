package helpers;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import objects.LocalizedString;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class LocalizationHelper {

	private static final String LOCALIZATION_FILE_LOCATION = FileHelper.getRootDirectory() + File.separator + "resources" + File.separator + "Localization.xml";
	private static final Map<String, LocalizedString> cache = new HashMap<String, LocalizedString>();

	// TODO: Clean up magic values
	public static void readAllNodesToCache() {
		Node node = null;
		try {
			File inputFile = new File(LOCALIZATION_FILE_LOCATION);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("String");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				node = nList.item(temp);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					String name = ((Element)node).getAttribute("id");
					addNodeToCache(name, node);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getLocalizedString(String id) {
		String result = id;
		LocalizedString localizedString = cache.get(id);
		if (localizedString != null) {
			result = localizedString.getLocalizedString();
		}
		return result;
	}

	private static LocalizedString addNodeToCache(String id, Node node) {
		LocalizedString result = null;
		Node translations = node.getFirstChild();
		Map<String, String> translationMap = new HashMap<String, String>();
		while (translations != null) {
			String language = translations.getNodeName();
			String value = translations.getTextContent();
			if (!"".equals(value.trim())) {
				translationMap.put(language, value);
			}
			translations = translations.getNextSibling();
		}
		result = new LocalizedString(id, translationMap);
		cache.put(id, result);
		return result;
	}

}
