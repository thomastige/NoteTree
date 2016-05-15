package helpers.property;

public class HotkeyPropertiesHelper extends AbstractPropertiesHelper{
	
	private final static String PROPERTIES_FILE_NAME = PROPERTIES_FILE_LOCATION + "hotkeys";

	public static void readAllProperties() throws Exception {
		readAllProperties(PROPERTIES_FILE_NAME);
	}

	public static String getHotkey(String key){
		return propertyCache.get(key);
	}
	
}
