package helpers.property;

import helpers.FileHelper;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

abstract class AbstractPropertiesHelper {
	protected static String PROPERTIES_FILE_LOCATION = FileHelper.getPropertyFilesLocation();
	protected static Map<String, String> propertyCache = new HashMap<String, String>();
	protected static final String PROPERTY_SEPARATOR = "=";
	protected static final String PROPERTY_COMMENT = "--";

	// TODO: clean up exception and magic numbers
	protected static void readAllProperties(String location) throws Exception {
		FileHelper.createNewFile(location);
		List<String> list = Files.readAllLines(Paths.get(location));
		Iterator<String> it = list.iterator();
		while (it.hasNext()) {
			String[] property = it.next().split(PROPERTY_SEPARATOR);
			if (!property[0].startsWith(PROPERTY_COMMENT) && !isWhiteSpace(property)) {
				if (property.length == 2) {
					propertyCache.put(property[0], property[1]);
				} else {
					throw new Exception("Malformed property: " + property[0]);
				}
			}
		}

	}

	// TODO: clean up exception and magic numbers
	private static boolean isWhiteSpace(String[] splitProperty){
		boolean result = false;
		if (splitProperty.length == 1){
			if ("".equals(splitProperty[0].trim())){
				result = true;
			}
		}
		return result;
	}
	
}
