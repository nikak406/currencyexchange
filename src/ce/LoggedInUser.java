package ce;

import javax.ejb.Singleton;
import javax.faces.bean.SessionScoped;

//TODO functional is doing wrong
@Singleton
@SessionScoped
public class LoggedInUser {

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
