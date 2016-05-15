package actions;

import helpers.FileHelper;
import helpers.UIHelper;
import helpers.ZipHelper;
import helpers.property.GlobalPropertiesHelper;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import cache.SessionCacheManager;
import cache.StructureCacheManager;

public class LoadProjectAction extends AbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1187180132508697556L;

	@Override
	public void actionPerformed(ActionEvent event) {
		JFileChooser fc = new JFileChooser();
		int selection = fc.showOpenDialog(null);
		if (selection == JFileChooser.OPEN_DIALOG) {
			File file = fc.getSelectedFile();
			String fileName = file.getName();
			fileName = fileName.substring(0, fileName.lastIndexOf('.'));
			ZipHelper.unZip(file.getAbsolutePath(), GlobalPropertiesHelper.getWorkingCopyCacheLocation() + File.separator + fileName);
			SessionCacheManager.setProjectName(fileName);
			SessionCacheManager.setSaveFileLocation(file.getPath());
			SessionCacheManager.setCacheFolderLocation(FileHelper.getCacheFolderLocation() + File.separator + fileName);
			try {
				StructureCacheManager.reloadCache();
				UIHelper.getRootFrame().reload();
			} catch (ParserConfigurationException | SAXException | IOException e) {
				e.printStackTrace();
			}
		}		
	}
	

}
