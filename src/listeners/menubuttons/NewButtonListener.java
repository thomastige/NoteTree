package listeners.menubuttons;

import helpers.FileHelper;
import helpers.UIHelper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import cache.SessionCacheManager;

public class NewButtonListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			String location = FileHelper.createNewCache();
			SessionCacheManager.setCacheFolderLocation(location);
			UIHelper.getRootFrame().reload();

		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (ParserConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SAXException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
