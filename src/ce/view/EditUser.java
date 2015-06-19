package ce.view;

import ce.model.User;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class EditUser extends User {
	public EditUser(){}
}
