package ce.view;

import ce.model.PasswordHash;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@RequestScoped
public class Login implements Serializable {

    public Login() {}

    private String login;

	private String password;

	private boolean remember;

	@EJB
	private PasswordHash passwordHash;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
		this.password = passwordHash.create(password);
    }

    public void setHashPassword(String hashPassword){
        this.password = hashPassword;
    }

    public boolean isRemember() {
        return remember;
    }

    public void setRemember(boolean remember) {
        this.remember = remember;
    }
}
