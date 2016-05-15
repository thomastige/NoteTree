package actions;

import helpers.FileHelper;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;

import cache.SessionCacheManager;

public class SaveAsProjectAction extends AbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5275933078428670296L;

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser fc = new JFileChooser();
		int selection = fc.showSaveDialog(null);
		if (selection == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fc.getSelectedFile();
			String fileName = FileHelper.getFileName(selectedFile.getPath());
			FileHelper.saveToZip(fileName);
			SessionCacheManager.setSaveFileLocation(fileName);
		}		
	}

	
	
}
