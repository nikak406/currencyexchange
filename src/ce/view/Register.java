package ce.view;

import ce.model.PasswordHash;
import ce.model.User;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

@ManagedBean
//TODO override messages regex not matched in xhtml
//TODO add select instead of inputText for location
//TODO add regex pattern for phone
public class Register extends User {

    public Register() {}

    @EJB
    private PasswordHash passwordHash;

    public void setPassword(String password) {
		this.password = passwordHash.create(password);
	}
}
