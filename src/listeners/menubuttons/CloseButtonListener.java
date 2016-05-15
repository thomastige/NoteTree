package listeners.menubuttons;

import helpers.FileHelper;
import helpers.UIHelper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import cache.SessionCacheManager;

public class CloseButtonListener implements ActionListener{



	@Override
	public void actionPerformed(ActionEvent e) {
		FileHelper.deleteOpenCache();
		SessionCacheManager.clearSession();
		try {
			UIHelper.getRootFrame().reload();
		} catch (ParserConfigurationException | SAXException | IOException e1) {
			e1.printStackTrace();
		}

	}
	
}
