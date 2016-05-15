package listeners.menubuttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import actions.LoadProjectAction;

public class LoadButtonListener implements ActionListener{



	@Override
	public void actionPerformed(ActionEvent arg0) {
		new LoadProjectAction().actionPerformed(arg0);
	}
	
}
