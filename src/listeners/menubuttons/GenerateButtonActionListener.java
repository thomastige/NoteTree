package listeners.menubuttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import actions.GenerateAction;

public class GenerateButtonActionListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		GenerateAction action = new GenerateAction();
		action.actionPerformed(arg0);
	}

}
