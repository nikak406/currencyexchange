package ce.view;

import ce.model.PasswordHash;
import ce.model.User;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
//TODO move location and currencies to external file
//TODO add regex pattern for phone and email and room
public class NewUser extends User {

    public NewUser() {}

    @EJB
    private PasswordHash passwordHash;

    public void setPassword(String password) {
		this.password = passwordHash.create(password);
	}
}
