package listeners.menubuttons;

import helpers.FileHelper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class SaveStructureButtonListener implements ActionListener{



	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			FileHelper.saveStructure();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

	}
	
}
