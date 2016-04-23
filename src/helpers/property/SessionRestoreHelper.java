package helpers.property;

import java.io.FileNotFoundException;

import cache.SessionCacheManager;

public class SessionRestoreHelper extends AbstractPropertiesHelper{
	
	private final static String PROPERTIES_FILE_NAME = PROPERTIES_FILE_LOCATION + "previousSession";

	public static void readAllProperties() throws Exception {
		readAllProperties(PROPERTIES_FILE_NAME);
	}
	
	public static void dumpSession() throws FileNotFoundException{
		SessionCacheManager.dumpSessionCache(PROPERTIES_FILE_NAME);
	}
	
	private static void restoreSession(){
		
	}
}
