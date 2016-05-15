package listeners.menubuttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import actions.SaveProjectAction;

public class SaveButtonListener implements ActionListener{


	@Override
	public void actionPerformed(ActionEvent e) {
		new SaveProjectAction().actionPerformed(e);
	}

}
