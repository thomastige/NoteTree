package driver;

import javax.swing.UIManager;

import helpers.LocalizationHelper;
import helpers.property.FormatterTagsPropertiesHelper;
import helpers.property.GlobalPropertiesHelper;
import helpers.property.HotkeyPropertiesHelper;
import ui.Frame;
import cache.SessionCacheManager;
import cache.StructureCacheManager;

public class Driver {

	private static Frame frame;

	public static void main(String[] args) throws Exception {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		initialStartup();
		frame = new Frame(LocalizationHelper.getLocalizedString("PROGRAM_NAME"));
	}

	public static Frame getFrame() {
		return frame;
	}

	private static void initialStartup() throws Exception {
		GlobalPropertiesHelper.readAllProperties();
		FormatterTagsPropertiesHelper.readAllProperties();
		HotkeyPropertiesHelper.readAllProperties();
		LocalizationHelper.readAllNodesToCache();
		SessionCacheManager.restoreSessionCache();
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
