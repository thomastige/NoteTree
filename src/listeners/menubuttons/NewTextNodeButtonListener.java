package listeners.menubuttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import actions.NewTextNodeAction;

public class NewTextNodeButtonListener implements ActionListener{
	
	@Override
	public void actionPerformed(ActionEvent e) {
		new NewTextNodeAction().actionPerformed(e);
	}
	
}
