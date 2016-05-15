package listeners.menubuttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import actions.SaveAsProjectAction;

public class SaveAsButtonListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		new SaveAsProjectAction().actionPerformed(e);
	}
	
}
