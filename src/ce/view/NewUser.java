package ce.view;

import ce.model.PasswordHash;
import ce.model.User;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
//TODO override messages regex not matched in xhtml
//TODO add select instead of inputText for location
//TODO add regex pattern for phone
public class NewUser extends User {

    public NewUser() {}

    @EJB
    private PasswordHash passwordHash;

    public void setPassword(String password) {
		this.password = passwordHash.create(password);
	}
}
