package ce.auth;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class User {

	@NotNull
    @Id
    private String login;

	private String password;

	private String name;

	private String email;

	public User(){}

	public User(String login, String password, String name, String email){
        this.login = login;
        this.password = password;
        this.email = email;
        this.name = name;
    }

	public boolean isPasswordCorrect(String password){
        if (this.password.equals(password)) return true;
        else{
            try {
                Thread.sleep(1000); //incorrect password - broadforce defence
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return false;
        }
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
