package actions;

import helpers.UIHelper;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class EditGenerateAction extends AbstractAction{

	@Override
	public void actionPerformed(ActionEvent arg0) {
		boolean editMode = UIHelper.isEditMode();
		if (editMode){
			new GenerateAction().actionPerformed(arg0);
		} else {
			new EditAction().actionPerformed(arg0);
		}
	}

}
