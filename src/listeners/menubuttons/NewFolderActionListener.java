package listeners.menubuttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import actions.NewFolderNodeAction;

public class NewFolderActionListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		new NewFolderNodeAction().actionPerformed(e);
	}

}
