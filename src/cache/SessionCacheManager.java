package cache;

import helpers.FileHelper;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SessionCacheManager {

	private static Map<String, String> sessionCache = new HashMap<String, String>();
	
	public static String getProjectName(){
		return sessionCache.get("activeProject");
	}
	
	public static void setProjectName(String name){
		sessionCache.put("activeProject", name);
	}
	public static String getSaveFileLocation(){
		return sessionCache.get("saveFileLocation");
	}
	
	public static void setSaveFileLocation(String name){
		sessionCache.put("saveFileLocation", name);
	}
	public static String getCacheFolderLocation(){
		return sessionCache.get("cacheFolderLocation");
	}
	
	public static void setCacheFolderLocation(String name){
		sessionCache.put("cacheFolderLocation", name);
	}

	public static void clearSession(){
		sessionCache.clear();
	}
	
	public static boolean isReady(){
		return !sessionCache.isEmpty();
	}

	public static void dumpSessionCache(String location) throws FileNotFoundException{
		StringBuilder dump = new StringBuilder();
		Iterator<String> keys = sessionCache.keySet().iterator();
		while (keys.hasNext()){
			String key = keys.next();
			String currLine = key + "=" + sessionCache.get(key) + "\n";
			dump.append(currLine);
		}
		FileHelper.saveFile(location, dump.toString());
	}
	
}
