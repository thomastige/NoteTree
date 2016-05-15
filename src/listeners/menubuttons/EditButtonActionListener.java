package listeners.menubuttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import actions.EditAction;

public class EditButtonActionListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent paramActionEvent) {
		EditAction action = new EditAction();
		action.actionPerformed(paramActionEvent);
	}

	
}
