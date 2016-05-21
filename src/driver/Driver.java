package driver;

import helpers.LocalizationHelper;
import helpers.property.FormatterTagsPropertiesHelper;
import helpers.property.GlobalPropertiesHelper;
import helpers.property.HotkeyPropertiesHelper;

import javax.swing.UIManager;

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

		if (SessionCacheManager.isReady()) {
			StructureCacheManager.reloadCache();
		}
	}

	
}
