package listeners.menubuttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import actions.DeleteNodeAction;

public class DeleteNodeButtonActionListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent paramActionEvent) {
		new DeleteNodeAction().actionPerformed(paramActionEvent);
	}
}
