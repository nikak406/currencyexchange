package ce.auth;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.validation.constraints.NotNull;

@ManagedBean
public class Register {

    public Register() {
    }

    @NotNull
    private String login;

	private String password;

	private String name;

	private String email;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
