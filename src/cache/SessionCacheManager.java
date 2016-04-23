package cache;

import helpers.FileHelper;
import helpers.property.GlobalPropertiesHelper;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SessionCacheManager {

	private static final String PREVIOUS_SESSION_LOCATION = FileHelper.getRootDirectory() + File.separator + "properties" + File.separator + "previousSession";
	private static Map<String, String> sessionCache = new HashMap<String, String>();

	public static String getProjectName() {
		return sessionCache.get("activeProject");
	}

	public static void setProjectName(String name) {
		sessionCache.put("activeProject", name);
	}

	public static String getSaveFileLocation() {
		return sessionCache.get("saveFileLocation");
	}

	public static void setSaveFileLocation(String name) {
		sessionCache.put("saveFileLocation", name);
	}

	public static String getCacheFolderLocation() {
		return sessionCache.get("cacheFolderLocation");
	}

	public static void setCacheFolderLocation(String name) {
		sessionCache.put("cacheFolderLocation", name);
	}

	public static void clearSession() {
		sessionCache.clear();
	}

	public static boolean isReady() {
		return !sessionCache.isEmpty();
	}

	public static void restoreSessionCache() throws Exception {
		File previousSession = new File(PREVIOUS_SESSION_LOCATION);
		if (previousSession.exists()) {

			List<String> list = Files.readAllLines(Paths.get(PREVIOUS_SESSION_LOCATION));
			Iterator<String> it = list.iterator();
			while (it.hasNext()) {
				String currLine = it.next();
				if (!"".equals(currLine.trim())) {
					String[] property = currLine.split("=");
					if (property.length == 2) {
						sessionCache.put(property[0], property[1]);
					} else {
						throw new Exception("Malformed property: " + property[0]);
					}
				}
			}
		} else {
			previousSession.createNewFile();
		}

	}

	public static void dumpSessionCache(String location) throws FileNotFoundException {
		StringBuilder dump = new StringBuilder();
		Iterator<String> keys = sessionCache.keySet().iterator();
		while (keys.hasNext()) {
			String key = keys.next();
			String currLine = key + "=" + sessionCache.get(key) + "\n";
			dump.append(currLine);
		}
		FileHelper.saveFile(location, dump.toString());
	}

}
