package ce.auth;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class Register extends User{

    public Register() {}

    @EJB
    private PasswordHash passwordHash;

    public void setPassword(String password) {
		this.password = passwordHash.create(password);
	}
}
