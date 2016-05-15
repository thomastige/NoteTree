package actions;

import helpers.FileHelper;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;

import cache.SessionCacheManager;

public class SaveProjectAction extends AbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6489970197914475249L;

	@Override
	public void actionPerformed(ActionEvent e) {
		String location = SessionCacheManager.getSaveFileLocation();
		if (location == null) {
			JFileChooser fc = new JFileChooser();
			int selection = fc.showSaveDialog(null);
			if (selection == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fc.getSelectedFile();
				FileHelper.saveToZip(FileHelper.getFileName(selectedFile.getPath()));
			}
		} else {
			FileHelper.saveToZip(location);
		}		
	}

}
