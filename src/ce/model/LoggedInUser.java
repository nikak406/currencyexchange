package ce.model;

import javax.ejb.Singleton;
import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;

//TODO functional is doing wrong
@Singleton
@ApplicationScoped
public class LoggedInUser implements Serializable{

    private User user;

    public User getUser() {
        return user;
    }

    public void setLoggedInUser(User user) {
        this.user = user;
    }

    public String getLogin(){
        if (user == null ) return null;
        return user.getLogin();
    }

	public void setUser(User user){
		this.user = user;
	}
}
