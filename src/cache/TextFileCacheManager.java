package cache;

import java.util.HashMap;
import java.util.Map;

public class TextFileCacheManager {

	private static Map<String, String> textFileCache = new HashMap<String, String>();

	public static void put(String key, String value) {
		textFileCache.put(key, value);
	}

	public static String getValue(String key) {
		String value = textFileCache.get(key);
		return value;
	}

}
