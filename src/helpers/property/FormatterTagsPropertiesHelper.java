package helpers.property;

public class FormatterTagsPropertiesHelper extends AbstractPropertiesHelper{

	private final static String PROPERTIES_FILE_NAME = PROPERTIES_FILE_LOCATION + "FormatterTagsProperties";

	
	public static void readAllProperties() throws Exception {
		readAllProperties(PROPERTIES_FILE_NAME);
	}

	public static String getClosingTagDelimiter(){
		return propertyCache.get("closingTagDelimiter");
	}

	public static String getPropertyFromCache(String propName){
		return propertyCache.get(propName);
	}
	
	public static boolean hasParameters(String code){
		return code.contains("=");
	}
	
}
