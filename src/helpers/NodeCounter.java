package helpers;

public class NodeCounter {

	private static int folderCount = 0;
	private static int textCount = 0;
	
	public static int addFolder(){
		return ++folderCount;
	}

	public static int addText(){
		return ++textCount;
	}
	
	public static void resetCounters(){
		folderCount = 0;
		textCount = 0;
	}
	
}
