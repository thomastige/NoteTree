package helpers.property;

public class GlobalPropertiesHelper extends AbstractPropertiesHelper {

	private final static String PROPERTIES_FILE_NAME = PROPERTIES_FILE_LOCATION + "properties";
	
	private static String getProperty(String key){
		return propertyCache.get(key);
	}

	public static String getLanguage() {
		return getProperty("language");
	}

	public static void readAllProperties() throws Exception {
		readAllProperties(PROPERTIES_FILE_NAME);
	}

	public static int getInitialX() {
		String initialXasString = getProperty("defaultX");
		return Integer.parseInt(initialXasString);
	}

	public static int getInitialY() {
		String initialYasString = getProperty("defaultY");
		return Integer.parseInt(initialYasString);
	}

	public static int getInitialDividerLocation() {
		String locationAsString = getProperty("defaultDividerLocation");
		return Integer.parseInt(locationAsString);
	}
	
	public static boolean getgenerateOnFileLoad() {
		String generateOnFileLoad = getProperty("generateOnFileLoad");
		return Boolean.valueOf(generateOnFileLoad);
	}
	
	public static String getSelectedNodeColor(){
		return getProperty("selectedNodeColor");
	}
	
	public static String getWorkingCopyCacheLocation(){
		return getProperty("workingCopyCacheLocation");
	}
	

}
