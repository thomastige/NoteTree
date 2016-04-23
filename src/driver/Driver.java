package driver;

import helpers.FileHelper;
import helpers.LocalizationHelper;
import helpers.property.FormatterTagsPropertiesHelper;
import helpers.property.GlobalPropertiesHelper;
import helpers.property.HotkeyPropertiesHelper;

import java.io.File;

import cache.SessionCacheManager;
import cache.StructureCacheManager;
import ui.Frame;

public class Driver {

	public static final String TEST_NAME = "Test Title";
	private static Frame frame;

	public static void main(String[] args) throws Exception {
		initialStartup();
		frame = new Frame(TEST_NAME);
	}

	public static Frame getFrame() {
		return frame;
	}

	private static void initialStartup() throws Exception {
		GlobalPropertiesHelper.readAllProperties();
		FormatterTagsPropertiesHelper.readAllProperties();
		HotkeyPropertiesHelper.readAllProperties();

		LocalizationHelper.readAllNodesToCache();

		// SessionCacheManager.setProjectName("TEST");
		// SessionCacheManager.setSaveFileLocation(FileHelper.getCurrentFileRootLocation()
		// + File.separator +
		// FileHelper.getFileName(SessionCacheManager.getProjectName()));
		// SessionCacheManager.setCacheFolderLocation(FileHelper.getRootDirectory()
		// + File.separator + "Cache"+ File.separator +
		// SessionCacheManager.getProjectName());

		if (SessionCacheManager.isReady()) {
			StructureCacheManager.reloadCache();
		}
	}

}
