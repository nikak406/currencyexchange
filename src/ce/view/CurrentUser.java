package ce.view;

import ce.model.User;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
//TODO refactor name
public class CurrentUser extends User {
	public CurrentUser(){}
}
